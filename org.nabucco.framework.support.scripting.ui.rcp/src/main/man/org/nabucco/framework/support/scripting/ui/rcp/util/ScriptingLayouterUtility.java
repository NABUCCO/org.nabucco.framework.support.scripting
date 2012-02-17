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
package org.nabucco.framework.support.scripting.ui.rcp.util;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerCombo;

/**
 * ScriptingLayouterUtility
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ScriptingLayouterUtility {

    /**
     * Private constructor
     */
    private ScriptingLayouterUtility() {
    }

    /**
     * Layout a textfield with default settings.
     * 
     * @param text
     *            the text field
     */
    public static void layoutDefault(Text text) {
        GridData data = new GridData();
        data.widthHint = 180;
        text.setLayoutData(data);
    }

    /**
     * Layout a label with default settings.
     * 
     * @param label
     *            the label
     */
    public static void layoutDefault(Label label) {
        GridData data = new GridData();
        data.widthHint = 100;
        label.setLayoutData(data);
    }

    /**
     * Layout a combobox with default settings.
     * 
     * @param combo
     *            the combobox
     */
    public static void layoutDefault(Combo combo) {
        GridData data = new GridData();
        data.widthHint = 165;
        combo.setLayoutData(data);
    }

    /**
     * Layout a combopicker with default settings.
     * 
     * @param combo
     *            the combo picker
     */
    public static void layoutDefault(ElementPickerCombo combo) {
        GridData data = new GridData();
        data.widthHint = 190;
        combo.setLayoutData(data);
    }
}
