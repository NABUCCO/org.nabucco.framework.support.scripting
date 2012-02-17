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
import org.nabucco.framework.plugin.base.command.AbstractNabuccoOpenEditViewHandlerImpl;
import org.nabucco.framework.support.scripting.facade.message.ScriptMsg;
import org.nabucco.framework.support.scripting.ui.rcp.browser.script.ScriptEditViewBrowserElement;
import org.nabucco.framework.support.scripting.ui.rcp.command.script.OpenScriptEditViewFromBrowserHandler;
import org.nabucco.framework.support.scripting.ui.rcp.communication.ScriptingComponentServiceDelegateFactory;
import org.nabucco.framework.support.scripting.ui.rcp.communication.resolve.ScriptResolveServiceDelegate;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditViewModel;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.view.ScriptEditView;

/**
 * OpenScriptEditViewFromBrowserHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class OpenScriptEditViewFromBrowserHandlerImpl extends
        AbstractNabuccoOpenEditViewHandlerImpl<ScriptEditViewBrowserElement, ScriptEditViewModel> implements
        OpenScriptEditViewFromBrowserHandler {

    @Override
    public void openScriptEditViewFromBrowser() {
        super.run();
    }

    @Override
    protected String getEditViewId() {
        return ScriptEditView.ID;
    }

    @Override
    protected void updateModel(ScriptEditViewBrowserElement browserElement, ScriptEditViewModel model) {

        try {
            ScriptResolveServiceDelegate resolveService = ScriptingComponentServiceDelegateFactory.getInstance()
                    .getScriptResolveService();

            ScriptMsg rq = new ScriptMsg();
            rq.setScript(browserElement.getViewModel().getScript());

            ScriptMsg rs = resolveService.resolveScript(rq);

            model.setScript(rs.getScript());
        } catch (ClientException ce) {
            Activator.getDefault().logError(ce);
        }
    }

}
