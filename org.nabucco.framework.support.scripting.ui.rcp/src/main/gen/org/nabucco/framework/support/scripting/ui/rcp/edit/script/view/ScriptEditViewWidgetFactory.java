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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.plugin.base.component.picker.combo.CodeComboViewer;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerCombo;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerComboParameter;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditViewModel;

/**
 * ScriptEditViewWidgetFactory<p/>Edit view for datatype Script<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public class ScriptEditViewWidgetFactory extends WidgetFactory {

    private ScriptEditViewModel model;

    public static final String SECTION = "SectionName";

    public static final String LABEL_SCRIPTNAME = "script.name";

    public static final String OBSERVE_VALUE_SCRIPTNAME = ScriptEditViewModel.PROPERTY_SCRIPT_NAME;

    public static final String LABEL_SCRIPTDESCRIPTION = "script.description";

    public static final String OBSERVE_VALUE_SCRIPTDESCRIPTION = ScriptEditViewModel.PROPERTY_SCRIPT_DESCRIPTION;

    public static final String LABEL_SCRIPTOWNER = "script.owner";

    public static final String OBSERVE_VALUE_SCRIPTOWNER = ScriptEditViewModel.PROPERTY_SCRIPT_OWNER;

    public static final String LABEL_SCRIPTTYPE = "script.type";

    public static final String OBSERVE_VALUE_SCRIPTTYPE = ScriptEditViewModel.PROPERTY_SCRIPT_TYPE;

    public static final String LABEL_SCRIPTSOURCECODE = "script.sourcecode";

    public static final String OBSERVE_VALUE_SCRIPTSOURCECODE = ScriptEditViewModel.PROPERTY_SCRIPT_SOURCECODE;

    public static final String LABEL_SCRIPTCONTEXTTYPE = "script.contexttype";

    public static final String OBSERVE_VALUE_USERTYPE = ScriptEditViewModel.PROPERTY_SCRIPT_CONTEXTTYPE;

    /**
     * Constructs a new ScriptEditViewWidgetFactory instance.
     *
     * @param model the ScriptEditViewModel.
     * @param nabuccoFormToolKit the NabuccoFormToolkit.
     */
    public ScriptEditViewWidgetFactory(NabuccoFormToolkit nabuccoFormToolKit,
            ScriptEditViewModel model) {
        super(nabuccoFormToolKit);
        this.model = model;
    }

    /**
     * CreateSectionHeading.
     *
     * @param parent the Composite.
     * @return the Section.
     */
    public Section createSectionHeading(Composite parent) {
        return nabuccoFormToolKit.createSection(parent, SECTION, new GridLayout());
    }

    /**
     * CreateLabelScriptName.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelScriptName(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_SCRIPTNAME);
    }

    /**
     * CreateInputFieldScriptName.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldScriptName(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_SCRIPTNAME);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelScriptDescription.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelScriptDescription(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_SCRIPTDESCRIPTION);
    }

    /**
     * CreateInputFieldScriptDescription.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldScriptDescription(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_SCRIPTDESCRIPTION);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelScriptOwner.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelScriptOwner(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_SCRIPTOWNER);
    }

    /**
     * CreateInputFieldScriptOwner.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldScriptOwner(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_SCRIPTOWNER);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelScriptType.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelScriptType(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_SCRIPTTYPE);
    }

    /**
     * CreateElementComboScriptType.
     *
     * @param params the ElementPickerComboParameter.
     * @param parent the Composite.
     * @return the ElementPickerCombo.
     */
    public ElementPickerCombo createElementComboScriptType(Composite parent,
            ElementPickerComboParameter params) {
        ElementPickerCombo elementCombo = new ElementPickerCombo(parent, SWT.NONE,
                params.getContentProvider(), params.getTableLabelProvider(), "", false);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement;
        IObservableValue modelElement;
        uiElement = SWTObservables.observeSelection(elementCombo.getCombo());
        modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_SCRIPTTYPE);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        elementCombo.addSelectionListener(new ScriptEditViewScriptTypeComboBoxHandler(model));
        return elementCombo;
    }

    /**
     * CreateLabelScriptSourceCode.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelScriptSourceCode(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_SCRIPTSOURCECODE);
    }

    /**
     * CreateInputFieldScriptSourceCode.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldScriptSourceCode(Composite parent) {
        Text result = nabuccoFormToolKit.createTextarea(parent, false);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_SCRIPTSOURCECODE);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelScriptContextType.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelScriptContextType(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_SCRIPTCONTEXTTYPE);
    }

    /**
     * CreateInputFieldName.
     * 
     * @param parent
     *            the Composite.
     * @return the Text.
     */
    public CodeComboViewer createComboScriptContextTyp(Composite parent) {
        CodeComboViewer codeComboViewer = new CodeComboViewer(parent,
                Script.getContextTypeCodePath(), model, OBSERVE_VALUE_USERTYPE);
        return codeComboViewer;
    }
}
