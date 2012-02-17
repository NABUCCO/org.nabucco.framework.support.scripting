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
package org.nabucco.framework.support.scripting.impl.service.compiler;

import org.nabucco.common.scripting.ScriptContainer;
import org.nabucco.common.scripting.compiler.ScriptCompilerException;
import org.nabucco.common.scripting.compiler.java.error.JavaCompilerException;
import org.nabucco.common.scripting.compiler.java.error.JavaCompilerMessage;
import org.nabucco.common.scripting.engine.ScriptingEngine;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.exception.ScriptCompilationException;
import org.nabucco.framework.support.scripting.facade.message.ScriptMsg;
import org.nabucco.framework.support.scripting.impl.service.util.ScriptingMapper;

/**
 * CompileScriptServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class CompileScriptServiceHandlerImpl extends CompileScriptServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected ScriptMsg compileScript(ScriptMsg msg) throws ScriptCompilationException {

        Script script = msg.getScript();

        ScriptContainer container = ScriptingMapper.getInstance().mapToContainer(script);

        try {
            ScriptingEngine engine = new ScriptingEngine(container.getType());
            engine.compile(container);

            script.setCode(container.getByteCode());

            return msg;

        } catch (JavaCompilerException jce) {

            try {
                for (JavaCompilerMessage compileError : jce.getCompilerMessages()) {

                    StringBuilder errorMessage = new StringBuilder().append(compileError.getSeverity());
                    if (compileError.getClassName() != null) {
                        errorMessage.append(" in ").append(compileError.getClassName());
                    }
                    if (compileError.getLine() > 0) {
                        errorMessage.append(" at line ").append(compileError.getLine());
                    }
                    errorMessage.append(" : ").append(compileError.getMessage());

                    super.getLogger().warning(errorMessage.toString());
                }

            } catch (Exception e) {
                super.getLogger().error("Error resolving compilation errors.", e);
            }

            super.getLogger().error(jce, "Error compiling java class [", script.getName(), "].");
            throw new ScriptCompilationException("Error compiling java script [" + script.getName() + "].", jce);
        } catch (ScriptCompilerException sce) {
            super.getLogger().error(sce, "Error compiling script [", script.getName(), "].");
            throw new ScriptCompilationException("Error compiling script [" + script.getName() + "].", sce);
        } catch (Exception e) {
            super.getLogger().error(e, "Error compiling script [", script.getName(), "].");
            throw new ScriptCompilationException("Error compiling script [" + script.getName() + "].", e);
        }
    }
}
