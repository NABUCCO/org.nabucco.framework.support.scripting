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
package org.nabucco.framework.support.scripting.impl.service.engine;

import java.util.List;

import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptData;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptType;
import org.nabucco.framework.support.scripting.facade.exception.ScriptCompilationException;
import org.nabucco.framework.support.scripting.facade.exception.ScriptExecutionException;
import org.nabucco.framework.support.scripting.impl.service.engine.javascript.RhinoScriptRunner;

/**
 * ScriptEngine
 * 
 * @author Jens Wurm, PRODYNA AG
 */
public class ScriptEngine {

    private ServiceMessageContext context;

    /**
     * Creates a new {@link ScriptEngine} instance.
     * 
     * @param context
     *            the service context
     */
    public ScriptEngine(ServiceMessageContext context) {
        if (context == null) {
            throw new IllegalArgumentException("Cannot create ScriptEngine for service context [null].");
        }
        this.context = context;
    }

    /**
     * Execute the the given script.
     * 
     * @param script
     *            the script to execute
     * 
     * @return the list of executed scripts
     * 
     * @throws ScriptCompilationException
     *             if the script cannot be compiled
     * @throws ScriptExecutionException
     *             if the script cannot be executed
     */
    public List<ScriptData> executeScript(Script script) throws ScriptCompilationException, ScriptExecutionException {
        if (script == null) {
            throw new IllegalArgumentException("Cannot execute script [null].");
        }

        ScriptRunner engine = this.getRunner(script.getType(), this.context);
        return engine.run(script);
    }

    /**
     * Retrieves the script runner for the appropriate script type.
     * 
     * @param type
     *            the script type
     * @param context
     *            the service context for service invocation
     * 
     * @return the script runner
     * 
     * @throws ScriptExecutionException
     *             if the script runner cannot be retrieved
     */
    private ScriptRunner getRunner(ScriptType type, ServiceMessageContext context) throws ScriptExecutionException {

        if (type == null) {
            throw new ScriptExecutionException("Script Type is not supported [null].");
        }

        switch (type) {

        case JAVASCRIPT:
            return new RhinoScriptRunner(context);

            // Other Script Runners

        default:
            throw new ScriptExecutionException("Script Type is not supported [" + type + "].");
        }
    }

}
