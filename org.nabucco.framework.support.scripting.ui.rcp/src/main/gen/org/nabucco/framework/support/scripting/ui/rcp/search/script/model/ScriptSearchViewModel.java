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
package org.nabucco.framework.support.scripting.ui.rcp.search.script.model;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchViewModel;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptType;

/**
 * ScriptSearchViewModel<p/>Search view for Scripts<p/>
 *
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public class ScriptSearchViewModel extends NabuccoComponentSearchViewModel<Script> implements
        NabuccoComponentSearchParameter {

    public static final String ID = "org.nabucco.framework.support.scripting.ui.search.script.ScriptSearchViewModel";

    private Script script;

    public static final String PROPERTY_SCRIPT_NAME = "scriptName";

    public static final String PROPERTY_SCRIPT_OWNER = "scriptOwner";

    public static final String PROPERTY_SCRIPT_TYPE = "scriptType";

    public static String TITLE = (ID + "Title");

    /**
     * Constructs a new ScriptSearchViewModel instance.
     *
     * @param viewId the String.
     */
    public ScriptSearchViewModel(String viewId) {
        super();
        correspondingListView = viewId;
        this.script = new Script();
    }

    @Override
    public String getSearchModelId() {
        return searchModelId;
    }

    @Override
    public NabuccoComponentSearchParameter getSearchParameter() {
        return this;
    }

    /**
     * Getter for the Script.
     *
     * @return the Script.
     */
    public Script getScript() {
        return this.script;
    }

    /**
     * Setter for the ScriptName.
     *
     * @param newName the String.
     */
    public void setScriptName(String newName) {
        if (((script != null) && (script.getName() == null))) {
            Name name = new Name();
            script.setName(name);
        }
        String oldVal = script.getName().getValue();
        script.getName().setValue(newName);
        this.updateProperty(PROPERTY_SCRIPT_NAME, oldVal, newName);
        if (((!oldVal.equals(newName)) && script.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            script.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ScriptName.
     *
     * @return the String.
     */
    public String getScriptName() {
        if ((((script == null) || (script.getName() == null)) || (script.getName().getValue() == null))) {
            return "";
        }
        return script.getName().getValue();
    }

    /**
     * Setter for the ScriptOwner.
     *
     * @param newOwner the String.
     */
    public void setScriptOwner(String newOwner) {
        if (((script != null) && (script.getOwner() == null))) {
            Owner owner = new Owner();
            script.setOwner(owner);
        }
        String oldVal = script.getOwner().getValue();
        script.getOwner().setValue(newOwner);
        this.updateProperty(PROPERTY_SCRIPT_OWNER, oldVal, newOwner);
        if (((!oldVal.equals(newOwner)) && script.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            script.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ScriptOwner.
     *
     * @return the String.
     */
    public String getScriptOwner() {
        if ((((script == null) || (script.getOwner() == null)) || (script.getOwner().getValue() == null))) {
            return "";
        }
        return script.getOwner().getValue();
    }

    /**
     * Getter for the ScriptType.
     *
     * @return the String.
     */
    public String getScriptType() {
        if (((script == null) || (script.getType() == null))) {
            return "";
        }
        return script.getType().name();
    }

    /**
     * Setter for the ScriptType.
     *
     * @param newType the String.
     */
    public void setScriptType(final String newType) {
        String oldVal = "";
        if ((this.script.getType() != null)) {
            oldVal = this.script.getType().name();
        }
        this.script.setType(ScriptType.valueOf(newType));
        this.updateProperty(PROPERTY_SCRIPT_TYPE, oldVal, newType);
        if (((!oldVal.equals(newType)) && script.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            script.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    @Override
    public String getId() {
        return ScriptSearchViewModel.ID;
    }
}
