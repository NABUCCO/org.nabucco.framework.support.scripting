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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.Reference;

import org.nabucco.framework.base.facade.component.Component;
import org.nabucco.framework.base.facade.component.locator.Locatable;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.support.scripting.facade.exception.ScriptExecutionException;

/**
 * ServiceOperationProxy.
 * 
 * Factory to create instances of {@link ServiceOperationProxy}'s. Attempts to find the appropriate
 * {@link Component} and {@link Service} by name and creates a proxy for an Operation.
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ServiceOperationProxyFactory {

    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(
            ServiceOperationProxyFactory.class);

    private ServiceMessageContext serviceContext = null;

    /**
     * Creates a new {@link ServiceOperationProxyFactory} instance.
     * 
     * @param context
     *            the service context for service invocations
     */
    public ServiceOperationProxyFactory(ServiceMessageContext context) {
        this.serviceContext = context;
    }

    /**
     * Attempts to create a {@link ServiceOperationProxy} for a given names.
     * 
     * @param componentName
     *            Name of the {@link Component} to wrap
     * @param serviceName
     *            Name of the {@link Service} to wrap
     * @param operationName
     *            Name of the ServiceOperation this proxy should be for.
     * 
     * @return an instance of {@link ServiceOperationProxy} that enables to access a
     *         ServiceOperation from JavaScript.
     * 
     * @throws ScriptExecutionException
     *             when the proxy cannot be created
     */
    public ServiceOperationProxy createServiceMessageProxy(String componentName, String serviceName,
            String operationName) throws ScriptExecutionException {
        Component component = resolveComponent(componentName);
        Service service = resolveService(component, serviceName);

        return new ServiceOperationProxyImpl(this.serviceContext, service, operationName);
    }

    /**
     * Get hold of the actual {@link Service} via Bean Convention. Attempts to get hold of the
     * Service getter and invoking it on the given Component.
     * 
     * @param component
     *            {@link Component} to get {@link Service} instance from.
     * @param service
     *            name of the needed {@link Service}.
     * 
     * @return the {@link Service} needed.
     * 
     * @throws ScriptExecutionException
     *             when the service cannot be resolved
     */
    private Service resolveService(Component component, String service) throws ScriptExecutionException {
        Service result = null;
        try {
            Method method = component.getClass().getMethod("get" + service, new Class<?>[0]);
            result = (Service) method.invoke(component, new Object[0]);
        } catch (SecurityException e) {
            String msg = "Unable to get hold of getter for service: " + service + ", on component: " + component + ".";
            logger.error(e, msg);
            throw new ScriptExecutionException(msg, e);
        } catch (NoSuchMethodException e) {
            String msg = "Unable to get hold of getter for service: " + service + ", on component: " + component + ".";
            logger.error(e, msg);
            throw new ScriptExecutionException(msg, e);
        } catch (IllegalArgumentException e) {
            String msg = "Unable to get hold of getter for service: " + service + ", on component: " + component + ".";
            logger.error(e, msg);
            throw new ScriptExecutionException(msg, e);
        } catch (IllegalAccessException e) {
            String msg = "Unable to get hold of getter for service: " + service + ", on component: " + component + ".";
            logger.error(e, msg);
            throw new ScriptExecutionException(msg, e);
        } catch (InvocationTargetException e) {
            String msg = "Unable to get hold of getter for service: " + service + ", on component: " + component + ".";
            logger.error(e, msg);
            throw new ScriptExecutionException(msg, e);
        }
        return result;
    }

    /**
     * Locate the component ejb by resolving it via its name from the JNDI tree.
     * 
     * @param componentName
     *            the component name
     * 
     * @return the component
     * 
     * @throws ScriptExecutionException
     *             when the component cannot be located
     */
    private Component resolveComponent(String componentName) throws ScriptExecutionException {
        Component result = null;

        try {
            InitialContext initialContext = new InitialContext();
            NamingEnumeration<Binding> list = initialContext.listBindings(Locatable.JNDI_PREFIX);
            result = componentSearch(componentName, initialContext, list);
        } catch (NamingException e) {
            String msg = "Cannot find component: " + componentName + " via jndi search";
            logger.error(msg);
            throw new ScriptExecutionException(msg, e);

        }
        return result;
    }

    /**
     * Traversing JNDI tree for component
     * 
     * @param componentName
     *            the component name
     * @param context
     *            the invocation context
     * @param level
     *            the naming list for components
     * 
     * @return the component from JNDI
     * 
     * @throws NamingException
     *             when the component cannot be located
     */
    private Component componentSearch(String componentName, Context context, NamingEnumeration<Binding> level)
            throws NamingException {

        Component result = null;

        while (result == null && level.hasMoreElements()) {

            Binding nextElement = level.nextElement();
            Object object = nextElement.getObject();

            if (object instanceof Context) {
                Context subContext = (Context) object;
                result = componentSearch(componentName, subContext, subContext.listBindings(""));
            } else if (object instanceof Reference) {
                Matcher matcher = Pattern.compile("(?i:" + componentName + ")").matcher(nextElement.getName());
                if (matcher.matches() || matcher.find()) {
                    Component possibleResult = (Component) context.lookup(nextElement.getName());
                    return possibleResult;
                }
            }
        }

        return result;
    }
}
