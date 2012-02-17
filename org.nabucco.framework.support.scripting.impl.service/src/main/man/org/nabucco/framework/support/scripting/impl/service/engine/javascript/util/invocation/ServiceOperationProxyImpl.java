/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.framework.support.scripting.impl.service.engine.javascript.util.invocation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.base.facade.service.Service;

/**
 * ServiceOperationProxyImpl
 * 
 * Uses reflection to evaluate the facaded service's request message and response message. On demand
 * creates an instance of the requestmessage and takes that instances to invoke the service.
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ServiceOperationProxyImpl implements ServiceOperationProxy {

    private Service service;

    private ServiceMessageContext context;

    private Method serviceOperation;

    private Class<? extends ServiceMessage> requestMsgType;

    private Class<? extends ServiceMessage> responseMsgType;

    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance()
            .getLogger(ServiceOperationProxyImpl.class);

    /**
     * Creates a new {@link ServiceOperationProxyImpl} instance.
     * 
     * @param context
     *            the service context
     * @param service
     *            the service interface
     * @param serviceName
     *            the name of the service
     */
    public ServiceOperationProxyImpl(ServiceMessageContext context, Service service, String serviceName) {
        this.context = context;
        this.service = service;

        this.init(serviceName);
    }

    /**
     * Initialize the service operation proxy.
     * 
     * @param serviceName
     *            the service name
     */
    private void init(String serviceName) {

        for (Method current : this.service.getClass().getDeclaredMethods()) {
            if (current.getName().compareTo(serviceName) == 0) {

                // this represents the method on the proxy instance
                this.serviceOperation = current;

                // getting the method on the interface to determinate parameter types
                for (Class<?> currentIntf : this.service.getClass().getInterfaces()) {

                    for (Method currentIntfMethod : currentIntf.getMethods()) {
                        if (currentIntfMethod.getName().compareTo(serviceName) == 0) {
                            this.findParameterTypes(currentIntfMethod);
                        }
                        return;
                    }
                }
            }
        }

        throw new IllegalArgumentException("Unable to find service with name: "
                + serviceName + ", on " + service.getClass().getName());
    }

    /**
     * Resolve the names of the arguments of a service operation.
     * 
     * @param serviceMethod
     *            the method to resolve
     */
    @SuppressWarnings("unchecked")
    private void findParameterTypes(Method serviceMethod) {

        // Response Message
        Type returnType = serviceMethod.getGenericReturnType();
        if (returnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) returnType).getActualTypeArguments();
            if (actualTypeArguments.length == 1) {
                if (actualTypeArguments[0] instanceof Class<?>) {
                    this.responseMsgType = (Class<? extends ServiceMessage>) actualTypeArguments[0];
                }
            }
        }

        // Request Message
        Type[] parameterTypes = serviceMethod.getGenericParameterTypes();
        if (parameterTypes.length == 1) {
            Type requestType = parameterTypes[0];
            if (requestType instanceof ParameterizedType) {
                ParameterizedType requestTypeParameterized = (ParameterizedType) requestType;
                Type[] actualTypeArguments = requestTypeParameterized.getActualTypeArguments();
                if (actualTypeArguments.length == 1) {
                    this.requestMsgType = (Class<? extends ServiceMessage>) actualTypeArguments[0];
                    return;
                }
            }
        }

        throw new IllegalArgumentException("Unable to find parameter types for Service Operation: "
                + this.serviceOperation.getName() + " on Service " + this.service.getClass().getName() + ".");

    }

    @Override
    public ServiceMessage createRequestMessage() {
        try {
            return requestMsgType.newInstance();
        } catch (Exception e) {
            logger.error(e, "Error instantiating request message for service invocation from script.");
        }

        return null;
    }

    @Override
    public ServiceMessage invoke(ServiceMessage requestMessage) {

        ServiceMessage result = null;

        try {
            ServiceRequest<ServiceMessage> request = new ServiceRequest<ServiceMessage>(context);
            request.setRequestMessage(requestMessage);
            Object invocationResult = this.serviceOperation.invoke(this.service, request);

            if (invocationResult instanceof ServiceResponse<?>) {

                ServiceResponse<?> response = (ServiceResponse<?>) invocationResult;
                Object responseMessage = response.getResponseMessage();

                if (this.responseMsgType.isInstance(responseMessage)) {
                    result = this.responseMsgType.cast(responseMessage);
                }
            }

        } catch (IllegalArgumentException iae) {
            logger.error(iae, "Error during service invocation during script execution. Service cannot be invoked.");
        } catch (IllegalAccessException iae) {
            logger.error(iae, "Error during service invocation during script execution. Service cannot be accessed");
        } catch (InvocationTargetException ite) {
            logger.error(ite, "Error in invoked service operation.");
        } catch (Exception e) {
            logger.error(e, "Error during service invocation during script execution.");
        }

        return result;
    }
}
