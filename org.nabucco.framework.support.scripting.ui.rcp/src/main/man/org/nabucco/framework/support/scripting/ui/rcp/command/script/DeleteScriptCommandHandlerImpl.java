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

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.close.AbstractDeleteDatatypeHandler;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditBusinessModel;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditViewModel;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.view.ScriptEditView;

/**
 * SaveScriptCommandHandleImpl
 * 
 * @author Silas Schwarz PRODYNA AG
 */
public class DeleteScriptCommandHandlerImpl extends AbstractDeleteDatatypeHandler<ScriptEditView> implements
        DeleteScriptHandler {

    @Override
    public String getId() {
        return ScriptEditView.ID;
    }

    @Override
    public void deleteScript() {
        super.run();
    }

    @Override
    protected boolean preClose(ScriptEditView view) throws ClientException {

        ScriptEditViewModel viewModel = view.getModel();
        Script script = viewModel.getScript();
        script.setDatatypeState(DatatypeState.DELETED);

        ScriptEditBusinessModel businessModel = (ScriptEditBusinessModel) Activator.getDefault().getModel()
                .getBusinessModel(ScriptEditBusinessModel.ID);

        businessModel.save(script);

        return super.preClose(view);
    }
}
