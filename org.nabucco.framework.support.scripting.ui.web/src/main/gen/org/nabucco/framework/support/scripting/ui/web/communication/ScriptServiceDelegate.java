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
package org.nabucco.framework.support.scripting.ui.web.communication;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.support.scripting.facade.exception.ScriptExecutionException;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionMsg;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionResultMsg;
import org.nabucco.framework.support.scripting.facade.service.ScriptService;

/**
 * ScriptServiceDelegate<p/>Service for executing Scripts<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2011-01-30
 */
public class ScriptServiceDelegate extends ServiceDelegateSupport {

    private ScriptService service;

    /**
     * Constructs a new ScriptServiceDelegate instance.
     *
     * @param service the ScriptService.
     */
    public ScriptServiceDelegate(ScriptService service) {
        super();
        this.service = service;
    }

    /**
     * ExecuteScript.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ScriptExecutionMsg.
     * @return the ScriptExecutionResultMsg.
     * @throws ScriptExecutionException
     */
    public ScriptExecutionResultMsg executeScript(ScriptExecutionMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws ScriptExecutionException {
        ServiceRequest<ScriptExecutionMsg> request = new ServiceRequest<ScriptExecutionMsg>(super.createServiceContext(
                session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ScriptExecutionResultMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.executeScript(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ScriptService.class, "executeScript", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ScriptExecutionException("Cannot execute service operation: ScriptService.executeScript");
    }
}
