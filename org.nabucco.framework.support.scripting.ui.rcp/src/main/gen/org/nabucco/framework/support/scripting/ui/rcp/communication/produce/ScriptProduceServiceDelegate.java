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
package org.nabucco.framework.support.scripting.ui.rcp.communication.produce;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.support.scripting.facade.message.ScriptMsg;
import org.nabucco.framework.support.scripting.facade.message.produce.ProduceScriptRq;
import org.nabucco.framework.support.scripting.facade.service.produce.ScriptProduceService;

/**
 * ScriptProduceServiceDelegate<p/>Service for producing new scripts.<p/>
 *
 * @version 1.0
 * @author Jens Wurm, PRODYNA AG, 2010-04-30
 */
public class ScriptProduceServiceDelegate extends ServiceDelegateSupport {

    private ScriptProduceService service;

    /**
     * Constructs a new ScriptProduceServiceDelegate instance.
     *
     * @param service the ScriptProduceService.
     */
    public ScriptProduceServiceDelegate(ScriptProduceService service) {
        super();
        this.service = service;
    }

    /**
     * ProduceScript.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ProduceScriptRq.
     * @return the ScriptMsg.
     * @throws ClientException
     */
    public ScriptMsg produceScript(ProduceScriptRq message, ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<ProduceScriptRq> request = new ServiceRequest<ProduceScriptRq>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ScriptMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceScript(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ScriptProduceService.class, "produceScript", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ScriptProduceService.produceScript");
    }
}
