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
package org.nabucco.framework.support.scripting.ui.web.communication.compiler;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.support.scripting.facade.exception.ScriptCompilationException;
import org.nabucco.framework.support.scripting.facade.message.ScriptMsg;
import org.nabucco.framework.support.scripting.facade.service.compiler.ScriptCompilationService;

/**
 * ScriptCompilationServiceDelegate<p/>Service for compiling scripts<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public class ScriptCompilationServiceDelegate extends ServiceDelegateSupport {

    private ScriptCompilationService service;

    /**
     * Constructs a new ScriptCompilationServiceDelegate instance.
     *
     * @param service the ScriptCompilationService.
     */
    public ScriptCompilationServiceDelegate(ScriptCompilationService service) {
        super();
        this.service = service;
    }

    /**
     * CompileScript.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ScriptMsg.
     * @return the ScriptMsg.
     * @throws ScriptCompilationException
     */
    public ScriptMsg compileScript(ScriptMsg message, NabuccoSession session, ServiceSubContext... subContexts)
            throws ScriptCompilationException {
        ServiceRequest<ScriptMsg> request = new ServiceRequest<ScriptMsg>(super.createServiceContext(session,
                subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ScriptMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.compileScript(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ScriptCompilationService.class, "compileScript", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ScriptCompilationException("Cannot execute service operation: ScriptCompilationService.compileScript");
    }
}
