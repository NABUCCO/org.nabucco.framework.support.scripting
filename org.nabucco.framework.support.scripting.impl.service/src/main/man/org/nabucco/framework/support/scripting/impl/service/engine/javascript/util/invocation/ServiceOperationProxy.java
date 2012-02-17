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

import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;

/**
 * ServiceOperationProxy
 * <p/>
 * Represents a proxy for a Service Call from inside a JavaScript. Should be used in 3 Steps.
 * <p/>
 * 
 * <li>
 * 1.) obtain a {@link ServiceRequest} via {@link ServiceOperationProxy#createRequestMessage()}.</li>
 * <li>
 * 2.) set values on created {@link ServiceRequest}</li>
 * <li>
 * 3.) use the {@link ServiceOperationProxy#invoke(ServiceMessage)} method to get a
 * {@link ServiceResponse}</li>
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
interface ServiceOperationProxy {

    /**
     * Creates an instance of the Message that is needed as Request Message and returns it.
     * 
     * @return an instances of the services RequestMessage.
     */
    ServiceMessage createRequestMessage();

    /**
     * Invokes the service that is facaded by this instance of {@link ServiceOperationProxy}
     * 
     * @param rq
     *            the request message for the service invocation
     * 
     * @return the response message from the service invocation
     */
    ServiceMessage invoke(ServiceMessage rq);

}
