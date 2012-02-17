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
package org.nabucco.framework.support.scripting.facade.service.compiler;

import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.support.scripting.facade.exception.ScriptCompilationException;
import org.nabucco.framework.support.scripting.facade.message.ScriptMsg;

/**
 * ScriptCompilationService<p/>Service for compiling scripts<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public interface ScriptCompilationService extends Service {

    /**
     * Compiles a compilable script to bytecode.
     *
     * @param rq the ServiceRequest<ScriptMsg>.
     * @return the ServiceResponse<ScriptMsg>.
     * @throws ScriptCompilationException
     */
    ServiceResponse<ScriptMsg> compileScript(ServiceRequest<ScriptMsg> rq) throws ScriptCompilationException;
}
