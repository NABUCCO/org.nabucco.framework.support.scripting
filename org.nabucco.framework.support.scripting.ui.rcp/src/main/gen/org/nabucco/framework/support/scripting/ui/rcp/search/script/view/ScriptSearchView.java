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
package org.nabucco.framework.support.scripting.ui.rcp.search.script.view;

import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.plugin.base.view.AbstractNabuccoSearchView;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.framework.plugin.base.view.NabuccoSearchView;
import org.nabucco.framework.support.scripting.ui.rcp.search.script.model.ScriptSearchViewModel;

/**
 * ScriptSearchView<p/>Search view for Scripts<p/>
 *
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public class ScriptSearchView extends AbstractNabuccoSearchView<ScriptSearchViewModel> implements NabuccoSearchView {

    private ScriptSearchViewModel model;

    public static final String ID = "org.nabucco.framework.support.scripting.ui.search.script.ScriptSearchView";

    /** Constructs a new ScriptSearchView instance. */
    public ScriptSearchView() {
        super();
        model = new ScriptSearchViewModel(this.getCorrespondingListView());
    }

    @Override
    public void createPartControl(final Composite parent, final NabuccoMessageManager aMessageManager) {
        this.getLayouter().layout(parent, aMessageManager, model);
    }

    @Override
    public ScriptSearchViewModel getModel() {
        return model;
    }

    @Override
    public String getId() {
        return ScriptSearchView.ID;
    }
}
