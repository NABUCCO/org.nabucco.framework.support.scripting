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
package org.nabucco.framework.support.scripting.ui.rcp.browser.script;

import java.io.Serializable;
import java.util.Map;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.model.browser.DatatypeBrowserElement;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditViewModel;

/**
 * ScriptEditViewBrowserElement
 *
 * @author Undefined
 */
public class ScriptEditViewBrowserElement extends DatatypeBrowserElement implements
        NabuccoInjectionReciever {

    private ScriptEditViewModel viewModel;

    private ScriptEditViewBrowserElementHandler browserHandler;

    /**
     * Constructs a new ScriptEditViewBrowserElement instance.
     *
     * @param datatype the Script.
     */
    public ScriptEditViewBrowserElement(final Script datatype) {
        super();
        NabuccoInjector instance = NabuccoInjector.getInstance(ScriptEditViewBrowserElement.class);
        browserHandler = instance.inject(ScriptEditViewBrowserElementHandler.class);
        viewModel = new ScriptEditViewModel();
        viewModel.setScript(datatype);
    }

    @Override
    protected void fillDatatype() {
        viewModel = browserHandler.loadFull(viewModel);
    }

    @Override
    protected void createChildren() {
        this.clearChildren();
        browserHandler.createChildren(viewModel, this);
    }

    @Override
    public Map<String, Serializable> getValues() {
        return this.viewModel.getValues();
    }

    /**
     * Getter for the ViewModel.
     *
     * @return the ScriptEditViewModel.
     */
    public ScriptEditViewModel getViewModel() {
        return this.viewModel;
    }

    /**
     * Setter for the ViewModel.
     *
     * @param viewModel the ScriptEditViewModel.
     */
    public void setViewModel(ScriptEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
