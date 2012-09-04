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
package org.nabucco.framework.support.scripting.facade.service;

import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.support.scripting.facade.exception.ScriptExecutionException;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionMsg;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionResultMsg;

/**
 * ScriptService<p/>Service for executing Scripts<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2011-01-30
 */
public interface ScriptService extends Service {

    /**
     * Executes a given Script in its related engine.
     *
     * @param rq the ServiceRequest<ScriptExecutionMsg>.
     * @return the ServiceResponse<ScriptExecutionResultMsg>.
     * @throws ScriptExecutionException
     */
    ServiceResponse<ScriptExecutionResultMsg> executeScript(ServiceRequest<ScriptExecutionMsg> rq)
            throws ScriptExecutionException;
}
