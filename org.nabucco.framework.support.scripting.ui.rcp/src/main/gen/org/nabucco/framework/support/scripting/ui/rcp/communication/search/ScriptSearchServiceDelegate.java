/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.framework.support.scripting.ui.rcp.communication.search;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.support.scripting.facade.message.ScriptListMsg;
import org.nabucco.framework.support.scripting.facade.message.search.ScriptSearchRq;
import org.nabucco.framework.support.scripting.facade.service.search.ScriptSearchService;

/**
 * ScriptSearchServiceDelegate<p/>Service for searching scripts.<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public class ScriptSearchServiceDelegate extends ServiceDelegateSupport {

    private ScriptSearchService service;

    /**
     * Constructs a new ScriptSearchServiceDelegate instance.
     *
     * @param service the ScriptSearchService.
     */
    public ScriptSearchServiceDelegate(ScriptSearchService service) {
        super();
        this.service = service;
    }

    /**
     * SearchScripts.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ScriptSearchRq.
     * @return the ScriptListMsg.
     * @throws ClientException
     */
    public ScriptListMsg searchScripts(ScriptSearchRq message, ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<ScriptSearchRq> request = new ServiceRequest<ScriptSearchRq>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ScriptListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchScripts(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ScriptSearchService.class, "searchScripts", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ScriptSearchService.searchScripts");
    }
}
