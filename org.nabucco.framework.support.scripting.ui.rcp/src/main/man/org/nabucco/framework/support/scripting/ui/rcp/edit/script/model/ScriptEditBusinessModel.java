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
package org.nabucco.framework.support.scripting.ui.rcp.edit.script.model;

import java.util.List;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.model.BusinessModel;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.message.ScriptListMsg;
import org.nabucco.framework.support.scripting.facade.message.ScriptMsg;
import org.nabucco.framework.support.scripting.ui.rcp.communication.ScriptingComponentServiceDelegateFactory;
import org.nabucco.framework.support.scripting.ui.rcp.communication.compiler.ScriptCompilationServiceDelegate;
import org.nabucco.framework.support.scripting.ui.rcp.communication.maintain.ScriptMaintainServiceDelegate;

/**
 * ScriptEditBusinessModel
 * 
 * @author Silas Schwarz PRODYNA AG
 */
public class ScriptEditBusinessModel implements BusinessModel {

    public static String ID = "org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditBusinessModel";

    /**
     * Saves a single instance of a Script.
     * 
     * @param script
     *            script instance
     * @return the service response message.
     */
    public ScriptMsg compile(Script script) {
        ScriptMsg rq = this.createScriptMsg(script);
        return compile(rq);
    }

    /**
     * Saves a single instance of a Script.
     * 
     * @param script
     *            script instance
     * @return the service response message.
     */
    public ScriptListMsg save(Script script) {
        ScriptListMsg rq = this.createScriptListMsg(script);
        return maintain(rq);
    }

    /**
     * Saves an list of Script
     * 
     * @param scripts
     *            a list of scripts
     * @return the service response message.
     */
    public ScriptListMsg save(List<Script> scripts) {
        ScriptListMsg rq = this.createScriptListMsg(scripts.toArray(new Script[0]));
        return maintain(rq);

    }

    /**
     * Maintain the script list.
     * 
     * @param rq
     *            the script list message
     * 
     * @return the maintained list message
     */
    private ScriptListMsg maintain(ScriptListMsg rq) {

        try {
            ScriptMaintainServiceDelegate maintainService = ScriptingComponentServiceDelegateFactory.getInstance()
                    .getScriptMaintainService();

            return maintainService.maintainScripts(rq);
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }

        return new ScriptListMsg();
    }

    /**
     * Compile a single script.
     * 
     * @param rq
     *            the script to compile
     * 
     * @return the compiled script
     */
    private ScriptMsg compile(ScriptMsg rq) {

        try {
            ScriptCompilationServiceDelegate compilationService = ScriptingComponentServiceDelegateFactory
                    .getInstance().getScriptCompilationService();

            return compilationService.compileScript(rq);
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }

        return null;
    }

    /**
     * Creates new instance of ScriptMsg containing a given script
     * 
     * @param script
     *            the script
     * 
     * @return new ScriptMsg
     */
    private ScriptMsg createScriptMsg(Script script) {
        ScriptMsg result = new ScriptMsg();
        result.setScript(script);
        return result;
    }

    /**
     * Creates new instance of ScriptListMsg containing a given script
     * 
     * @param scripts
     *            the list of scripts
     * 
     * @return new ScriptListMsg
     */
    private ScriptListMsg createScriptListMsg(Script... scripts) {
        ScriptListMsg result = new ScriptListMsg();
        for (Script current : scripts) {
            result.getScriptList().add(current);
        }
        return result;
    }

}
