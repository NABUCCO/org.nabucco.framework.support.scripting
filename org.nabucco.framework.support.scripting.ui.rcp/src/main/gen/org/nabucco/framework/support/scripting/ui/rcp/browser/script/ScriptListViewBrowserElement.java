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

import java.util.List;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.framework.plugin.base.model.browser.BrowserListElement;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.ui.rcp.list.script.model.ScriptListViewModel;

/**
 * ScriptListViewBrowserElement
 *
 * @author Undefined
 */
public class ScriptListViewBrowserElement extends BrowserListElement<ScriptListViewModel> implements
        NabuccoInjectionReciever {

    private ScriptListViewBrowserElementHandler listViewBrowserElementHandler;

    /**
     * Constructs a new ScriptListViewBrowserElement instance.
     *
     * @param datatypeList the List<Script>.
     */
    public ScriptListViewBrowserElement(final List<Script> datatypeList) {
        this(datatypeList.toArray(new Script[datatypeList.size()]));
    }

    /**
     * Constructs a new ScriptListViewBrowserElement instance.
     *
     * @param datatypeArray the Script[].
     */
    public ScriptListViewBrowserElement(final Script[] datatypeArray) {
        super();
        NabuccoInjector instance = NabuccoInjector.getInstance(ScriptListViewBrowserElement.class);
        listViewBrowserElementHandler = instance.inject(ScriptListViewBrowserElementHandler.class);
        viewModel = new ScriptListViewModel();
        viewModel.setElements(datatypeArray);
    }

    @Override
    protected void createChildren() {
        this.clearChildren();
        listViewBrowserElementHandler.createChildren(viewModel, this);
    }

    @Override
    public void removeBrowserElement(final BrowserElement element) {
        super.removeBrowserElement(element);
        listViewBrowserElementHandler.removeChild(element, this);
    }
}
