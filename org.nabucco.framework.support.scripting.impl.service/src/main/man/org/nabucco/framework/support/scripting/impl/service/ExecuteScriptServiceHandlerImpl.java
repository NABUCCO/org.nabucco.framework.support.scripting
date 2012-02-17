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
package org.nabucco.framework.support.scripting.impl.service;

import java.util.List;

import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptData;
import org.nabucco.framework.support.scripting.facade.exception.ScriptExecutionException;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionMsg;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionResultMsg;
import org.nabucco.framework.support.scripting.impl.service.engine.ScriptEngine;

/**
 * ExecuteScriptServiceHandlerImpl
 * 
 * @author Jens Wurm, PRODYNA AG
 */
public class ExecuteScriptServiceHandlerImpl extends ExecuteScriptServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    public ScriptExecutionResultMsg executeScript(ScriptExecutionMsg msg) throws ScriptExecutionException {

        Script script = msg.getScript();

        try {
            ScriptEngine engine = new ScriptEngine(super.getContext());
            List<ScriptData> resultList = engine.executeScript(script);

            ScriptExecutionResultMsg rsMsg = new ScriptExecutionResultMsg();
            rsMsg.getScriptDataList().addAll(resultList);
            return rsMsg;

        } catch (ScriptExecutionException se) {
            throw se;
        } catch (Exception e) {
            throw new ScriptExecutionException("Error executing script [" + script.getName() + "].", e);
        }
    }
}
