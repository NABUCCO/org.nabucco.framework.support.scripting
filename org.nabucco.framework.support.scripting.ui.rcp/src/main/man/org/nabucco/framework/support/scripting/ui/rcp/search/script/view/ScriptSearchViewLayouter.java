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

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.base.facade.datatype.code.Code;
import org.nabucco.framework.plugin.base.component.picker.combo.CodeComboViewer;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerCombo;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerComboParameter;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerContentProvider;
import org.nabucco.framework.plugin.base.layout.BaseSearchViewLayouter;
import org.nabucco.framework.plugin.base.layout.I18NLabelProvider;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.layout.NabuccoLayouter;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.view.ScriptEditViewScriptTypeContentProvider;
import org.nabucco.framework.support.scripting.ui.rcp.search.script.model.ScriptSearchViewModel;
import org.nabucco.framework.support.scripting.ui.rcp.util.ScriptTypeLabelProvider;
import org.nabucco.framework.support.scripting.ui.rcp.util.ScriptingLayouterUtility;

/**
 * ScriptSearchViewLayouter
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ScriptSearchViewLayouter extends BaseSearchViewLayouter<ScriptSearchViewModel>
        implements NabuccoLayouter<ScriptSearchViewModel> {

    private static final String MESSAGE_OWNER_ID = "org.nabucco.framework.support.scripting.ui.rcp.search.script.view.ScriptSearchViewLayouter";

    private static final String SECTION_TITLE = ScriptSearchView.ID + ".section";

    private ScriptSearchViewWidgetFactory widgetFactory;
    
    private ScriptSearchViewModel model;

    @Override
    protected String getMessageOwnerId() {
        return MESSAGE_OWNER_ID;
    }

    @Override
    public Composite layoutComposite(Composite parent, NabuccoMessageManager msgManager,
            ScriptSearchViewModel model, Layoutable<ScriptSearchViewModel> view) {

        this.model = model;
        this.widgetFactory = new ScriptSearchViewWidgetFactory(nabuccoFormToolKit, model);

        Section section = nabuccoFormToolKit.createSection(parent, SECTION_TITLE, new GridLayout(1,
                true));

        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 10;
        layout.horizontalSpacing = 20;

        Composite sectionBody = nabuccoFormToolKit.createComposite(section, layout);
        section.setClient(sectionBody);

        layoutLabelAndInputFieldName(sectionBody);
        layoutLabelAndComboBoxType(sectionBody);
        layoutLabelAndInputFieldContextType(sectionBody);

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
     * Layout the search parameter type.
     * 
     * @param parent
     *            the parent composite
     */
    private void layoutLabelAndComboBoxType(Composite parent) {
        Label label = widgetFactory.createLabelScriptType(parent);
        ScriptingLayouterUtility.layoutDefault(label);

        ElementPickerContentProvider contentProvider = new ScriptEditViewScriptTypeContentProvider();
        ILabelProvider labelProvider = new I18NLabelProvider(new ScriptTypeLabelProvider());

        ElementPickerComboParameter parameter = new ElementPickerComboParameter(contentProvider,
                labelProvider);

        ElementPickerCombo combo = widgetFactory.createElementComboScriptType(parent, parameter);
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
        comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                StructuredSelection selection = (StructuredSelection) event.getSelection();
                Code code = (Code) selection.getFirstElement();
                ScriptSearchViewLayouter.this.model.getScript().setContextType(code);
            }
        });
    }

}
