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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.plugin.base.component.picker.combo.CodeComboViewer;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerCombo;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerComboParameter;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.layout.NabuccoLayouter;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditViewModel;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.view.label.ScriptEditViewScriptTypeLabelProvider;
import org.nabucco.framework.support.scripting.ui.rcp.util.ScriptingLayouterUtility;

/**
 * ScriptEditViewLayouter
 * 
 * @author Jens Wurm, PRODYNA AG
 */
public class ScriptEditViewLayouter implements NabuccoLayouter<ScriptEditViewModel> {

    private ScriptEditViewWidgetFactory widgetFactory;

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager,
            ScriptEditViewModel model, Layoutable<ScriptEditViewModel> view) {
        return this.layout(parent, messageManager, model);
    }

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager,
            ScriptEditViewModel model) {
        NabuccoFormToolkit ntk = new NabuccoFormToolkit(parent);
        this.widgetFactory = new ScriptEditViewWidgetFactory(ntk, model);

        return this.layoutSection(parent);
    }

    /**
     * Layout the edit view section.
     * 
     * @param parent
     *            the parent composite
     * 
     * @return the layouted section
     */
    private Composite layoutSection(Composite parent) {
        Section section = widgetFactory.createSectionHeading(parent);
        Composite child = widgetFactory.getNabuccoFormToolKit().createComposite(section,
                new GridLayout(2, false));
        section.setClient(child);

        layoutLabelAndInputFieldName(child);
        layoutLabelandInputFieldDescription(child);
        layoutLabelAndInputFieldOwner(child);
        layoutLabelAndComboBoxScriptType(child);
        layoutLabelAndInputFieldContextType(child);
        layoutLabelAndTextAreaSourceCode(child);

        return section;

    }

    /**
     * Layout the search parameter name.
     * 
     * @param parent
     *            the parent composite
     */
    private void layoutLabelAndInputFieldName(Composite parent) {
        Label label = widgetFactory.createLabelScriptName(parent);
        ScriptingLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldScriptName(parent);
        ScriptingLayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the search parameter owner.
     * 
     * @param parent
     *            the parent composite
     */
    private void layoutLabelAndInputFieldOwner(Composite parent) {
        Label label = widgetFactory.createLabelScriptOwner(parent);
        ScriptingLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldScriptOwner(parent);
        text.setEditable(false);
        text.setEnabled(false);
        ScriptingLayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the search parameter description.
     * 
     * @param parent
     *            the parent composite
     */
    private void layoutLabelandInputFieldDescription(Composite parent) {
        Label label = widgetFactory.createLabelScriptDescription(parent);
        ScriptingLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldScriptDescription(parent);
        ScriptingLayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the search parameter script type.
     * 
     * @param child
     *            the child composite
     */
    private void layoutLabelAndComboBoxScriptType(Composite child) {
        Label label = widgetFactory.createLabelScriptType(child);
        ScriptingLayouterUtility.layoutDefault(label);

        ElementPickerComboParameter params = new ElementPickerComboParameter(
                new ScriptEditViewScriptTypeContentProvider(),
                new ScriptEditViewScriptTypeLabelProvider());

        ElementPickerCombo combo = widgetFactory.createElementComboScriptType(child, params);
        ScriptingLayouterUtility.layoutDefault(combo);
    }

    /**
     * Layout the user type.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldContextType(Composite parent) {
        Label label = widgetFactory.createLabelScriptContextType(parent);
        ScriptingLayouterUtility.layoutDefault(label);

        CodeComboViewer comboViewer = widgetFactory.createComboScriptContextTyp(parent);
        ScriptingLayouterUtility.layoutDefault(comboViewer.getCombo());
    }
    
    /**
     * Layout the source code text field.
     * 
     * @param parent
     *            the parent composite
     */
    private void layoutLabelAndTextAreaSourceCode(Composite parent) {
        Label label = widgetFactory.createLabelScriptSourceCode(parent);
        ScriptingLayouterUtility.layoutDefault(label);

        Text textArea = widgetFactory.createInputFieldScriptSourceCode(parent);

        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.widthHint = 500;
        gridData.heightHint = 400;
        textArea.setLayoutData(gridData);
    }

}
