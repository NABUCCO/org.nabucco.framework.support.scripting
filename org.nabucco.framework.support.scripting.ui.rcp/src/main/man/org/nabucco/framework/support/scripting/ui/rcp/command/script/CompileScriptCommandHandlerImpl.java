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
package org.nabucco.framework.support.scripting.ui.rcp.command.script;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.message.ScriptMsg;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditBusinessModel;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditViewModel;

/**
 * CompileScriptCommandHandleImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class CompileScriptCommandHandlerImpl implements CompileScriptHandler {

    @Override
    public void compileScript() {

        ScriptEditViewModel viewModel = (ScriptEditViewModel) Activator.getDefault().getModel().getCurrentViewModel();

        ScriptEditBusinessModel businessModel = (ScriptEditBusinessModel) Activator.getDefault().getModel()
                .getBusinessModel(ScriptEditBusinessModel.ID);

        Script script = viewModel.getScript();
        ScriptMsg rs;
        try {
            rs = businessModel.compile(script);
            
            if (rs != null && rs.getScript() != null) {
                viewModel.setScript(rs.getScript());
            } else {
                Activator.getDefault().logError("Compiling script failed");
            }
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
    }

}
