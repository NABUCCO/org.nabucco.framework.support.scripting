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
import org.nabucco.framework.plugin.base.command.AbstractAddDatatypeHandlerImpl;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptType;
import org.nabucco.framework.support.scripting.facade.message.ScriptMsg;
import org.nabucco.framework.support.scripting.facade.message.produce.ProduceScriptRq;
import org.nabucco.framework.support.scripting.ui.rcp.communication.ScriptingComponentServiceDelegateFactory;
import org.nabucco.framework.support.scripting.ui.rcp.communication.produce.ScriptProduceServiceDelegate;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditViewModel;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.view.ScriptEditView;

/**
 * AddScriptCommandHandlerImpl
 * 
 * @author Silas Schwarz PRODYNA AG
 */
public class AddScriptCommandHandlerImpl extends AbstractAddDatatypeHandlerImpl<ScriptEditViewModel> implements
        AddScriptHandler {

    @Override
    protected String getEditViewId() {
        return ScriptEditView.ID;
    }

    @Override
    protected void updateModel(ScriptEditViewModel viewModel) {
        viewModel.setScript(createScriptInstance());
    }

    private Script createScriptInstance() {
        Script result = null;
        try {
            ScriptProduceServiceDelegate scriptProduceService = ScriptingComponentServiceDelegateFactory.getInstance()
                    .getScriptProduceService();
            ProduceScriptRq produceScriptRq = new ProduceScriptRq();
            produceScriptRq.setScriptType(ScriptType.JAVASCRIPT);
            ScriptMsg produceScript = scriptProduceService.produceScript(produceScriptRq);
            result = produceScript.getScript();

        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.nabucco.framework.support.scripting.ui.rcp.command.script.AddScriptHandler#addScript()
     */
    @Override
    public void addScript() {
        run();

    }

}
