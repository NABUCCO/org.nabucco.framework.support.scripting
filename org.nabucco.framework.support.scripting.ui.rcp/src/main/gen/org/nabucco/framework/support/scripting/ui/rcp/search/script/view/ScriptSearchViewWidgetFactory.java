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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerCombo;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerComboParameter;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.support.scripting.ui.rcp.search.script.model.ScriptSearchViewModel;

/**
 * ScriptSearchViewWidgetFactory<p/>Search view for Scripts<p/>
 *
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public class ScriptSearchViewWidgetFactory extends WidgetFactory {

    private ScriptSearchViewModel model;

    public static final String LABEL_SCRIPTNAME = "script.name";

    public static final String OBSERVE_VALUE_SCRIPTNAME = ScriptSearchViewModel.PROPERTY_SCRIPT_NAME;

    public static final String LABEL_SCRIPTOWNER = "script.owner";

    public static final String OBSERVE_VALUE_SCRIPTOWNER = ScriptSearchViewModel.PROPERTY_SCRIPT_OWNER;

    public static final String LABEL_SCRIPTTYPE = "script.type";

    public static final String OBSERVE_VALUE_SCRIPTTYPE = ScriptSearchViewModel.PROPERTY_SCRIPT_TYPE;

    /**
     * Constructs a new ScriptSearchViewWidgetFactory instance.
     *
     * @param aModel the ScriptSearchViewModel.
     * @param nabuccoFormToolKit the NabuccoFormToolkit.
     */
    public ScriptSearchViewWidgetFactory(final NabuccoFormToolkit nabuccoFormToolKit, final ScriptSearchViewModel aModel) {
        super(nabuccoFormToolKit);
        model = aModel;
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
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_SCRIPTNAME);
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
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_SCRIPTOWNER);
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
    public ElementPickerCombo createElementComboScriptType(Composite parent, ElementPickerComboParameter params) {
        ElementPickerCombo elementCombo = new ElementPickerCombo(parent, SWT.NONE, params.getContentProvider(),
                params.getTableLabelProvider(), "", false);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement;
        IObservableValue modelElement;
        uiElement = SWTObservables.observeSelection(elementCombo.getCombo());
        modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_SCRIPTTYPE);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        elementCombo.addSelectionListener(new ScriptSearchViewScriptTypeComboBoxHandler(model));
        return elementCombo;
    }
}
