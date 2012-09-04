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
package org.nabucco.framework.support.scripting.ui.web.communication.resolve;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.support.scripting.facade.message.ScriptMsg;
import org.nabucco.framework.support.scripting.facade.service.resolve.ScriptResolveService;

/**
 * ScriptResolveServiceDelegate<p/>Service for searching scripts.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-09-16
 */
public class ScriptResolveServiceDelegate extends ServiceDelegateSupport {

    private ScriptResolveService service;

    /**
     * Constructs a new ScriptResolveServiceDelegate instance.
     *
     * @param service the ScriptResolveService.
     */
    public ScriptResolveServiceDelegate(ScriptResolveService service) {
        super();
        this.service = service;
    }

    /**
     * ResolveScript.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ScriptMsg.
     * @return the ScriptMsg.
     * @throws ResolveException
     */
    public ScriptMsg resolveScript(ScriptMsg message, NabuccoSession session, ServiceSubContext... subContexts)
            throws ResolveException {
        ServiceRequest<ScriptMsg> request = new ServiceRequest<ScriptMsg>(super.createServiceContext(session,
                subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ScriptMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveScript(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ScriptResolveService.class, "resolveScript", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ResolveException("Cannot execute service operation: ScriptResolveService.resolveScript");
    }
}
