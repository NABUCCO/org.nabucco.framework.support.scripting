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

import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.AbstractSaveCommandHandlerImpl;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.message.ScriptListMsg;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditBusinessModel;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditViewModel;

/**
 * SaveScriptCommandHandleImpl
 * 
 * @author Silas Schwarz PRODYNA AG
 */
public class SaveScriptCommandHandlerImpl extends
        AbstractSaveCommandHandlerImpl<ScriptEditBusinessModel, ScriptEditViewModel> implements SaveScriptHandler {

    @Override
    public String getBusinessModelId() {
        return ScriptEditBusinessModel.ID;
    }

    @Override
    protected void saveModel(ScriptEditViewModel viewModel, ScriptEditBusinessModel businessModel) throws ClientException {
        Script script = viewModel.getScript();
        ScriptListMsg save = businessModel.save(script);

        NabuccoList<Script> scriptList = save.getScriptList();
        if (scriptList.isEmpty()) {
            Activator.getDefault().logError("Saving script failed!");
        } else {
            viewModel.setScript(scriptList.first());
        }
    }

    @Override
    public void saveScript() {
        run();
    }

}
