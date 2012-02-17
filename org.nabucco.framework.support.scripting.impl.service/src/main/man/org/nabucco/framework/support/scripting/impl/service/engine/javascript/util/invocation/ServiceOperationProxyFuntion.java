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

import org.mozilla.javascript.ScriptableObject;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.support.scripting.facade.exception.ScriptExecutionException;

/**
 * ServiceOperationProxyFuntion.
 * 
 * Encapsulation of the globally accessible JavaScript function 'createServiceProxy(String
 * componentName,String serviceName, String operationName)' which will ad the need for calling
 * NABUCCO ServiceOperations from within a JavaScript.
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ServiceOperationProxyFuntion {

    private static final long serialVersionUID = -1L;

    private static ServiceOperationProxyFactory FACTORY = null;

    /**
     * Creates a new {@link ServiceOperationProxyFuntion} instance.
     * 
     * @param context
     *            the service context for service invocation
     */
    public ServiceOperationProxyFuntion(ServiceMessageContext context) {
        synchronized (ServiceOperationProxyFuntion.class) {
            FACTORY = new ServiceOperationProxyFactory(context);
        }
    }

    /**
     * Add's the createServiceProxy(String,String,String) function to the JavaScript Runtime.
     * 
     * @param scriptable
     *            Root element of a Rhino Scripting engine
     */
    public void addFunctions(ScriptableObject scriptable) {
        scriptable.defineFunctionProperties(ServiceOperationProxyFuntion.CreateServiceProxyFunction.FUNCTION_NAMES,
                ServiceOperationProxyFuntion.CreateServiceProxyFunction.class, ScriptableObject.DONTENUM);
    }

    /**
     * The class is instantiated for script executions. Its static methods are given into the script
     * and may be called from within.
     * <p/>
     * This class should not be instantiated manually outside of a script.
     */
    private static class CreateServiceProxyFunction {

        /** Name of the functions to call. */
        private static final String[] FUNCTION_NAMES = { "createServiceProxy" };

        /** The logger */
        private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(
                ServiceOperationProxyFuntion.CreateServiceProxyFunction.class);

        /**
         * Creates a new {@link CreateServiceProxyFunction} instance.
         */
        private CreateServiceProxyFunction() {
        }

        /**
         * Creates a proxy instance for service operation invocations within scripts. This operation
         * may be called explicitly from within a script for service operation invocations.
         * 
         * @param componentName
         *            name of the component
         * @param serviceName
         *            name of the service
         * @param operationName
         *            name of the service operation
         * 
         * @return the proxy for service operation invocations from scripts
         */
        @SuppressWarnings("unused")
        public static ServiceOperationProxy createServiceProxy(String componentName, String serviceName,
                String operationName) {

            ServiceOperationProxy result = null;

            try {

                result = FACTORY.createServiceMessageProxy(componentName, serviceName, operationName);

            } catch (ScriptExecutionException see) {
                logger.error(see, "Error creating script service proxy.");
            } catch (Exception e) {
                logger.error(e, "Error creating script service proxy.");
            }

            return result;
        }
    }

}
