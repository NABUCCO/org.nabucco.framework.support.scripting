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
package org.nabucco.framework.support.scripting.ui.rcp.edit.script.view;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditViewModel;

/**
 * ScriptEditViewScriptTypeComboBoxHandler<p/>Edit view for datatype Script<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public class ScriptEditViewScriptTypeComboBoxHandler implements SelectionListener {

    private ScriptEditViewModel model;

    /**
     * Constructs a new ScriptEditViewScriptTypeComboBoxHandler instance.
     *
     * @param model the ScriptEditViewModel.
     */
    public ScriptEditViewScriptTypeComboBoxHandler(final ScriptEditViewModel model) {
        super();
        this.model = model;
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent selectionEvent) {
        if ((selectionEvent.widget instanceof Combo)) {
            Combo combo = ((Combo) selectionEvent.widget);
            model.setScriptType(combo.getText());
        }
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent) {
        if ((selectionEvent.widget instanceof Combo)) {
            Combo combo = ((Combo) selectionEvent.widget);
            model.setScriptType(combo.getText());
        }
    }
}
