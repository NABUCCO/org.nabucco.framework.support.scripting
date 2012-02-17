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
package org.nabucco.framework.support.scripting.ui.rcp.edit.script.model;

import java.io.Serializable;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.Code;
import org.nabucco.framework.plugin.base.component.edit.model.EditViewModel;
import org.nabucco.framework.plugin.base.logging.Loggable;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptSourceCode;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptType;

/**
 * ScriptEditViewModel
 * <p/>
 * Edit view for datatype Script
 * <p/>
 * 
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public class ScriptEditViewModel extends EditViewModel implements Loggable {

    private Script script;

    public static final String PROPERTY_SCRIPT_NAME = "scriptName";

    public static final String PROPERTY_SCRIPT_DESCRIPTION = "scriptDescription";

    public static final String PROPERTY_SCRIPT_OWNER = "scriptOwner";

    public static final String PROPERTY_SCRIPT_TYPE = "scriptType";

    public static final String PROPERTY_SCRIPT_SOURCECODE = "scriptSourcecode";

    public static final String PROPERTY_SCRIPT_CONTEXTTYPE = "scriptContexttype";

    /** Constructs a new ScriptEditViewModel instance. */
    public ScriptEditViewModel() {
        super();
    }

    /**
     * Getter for the ID.
     * 
     * @return the String.
     */
    public String getID() {
        return "org.nabucco.framework.support.scripting.ui.rcp.edit.script.model.ScriptEditViewModel";
    }

    /**
     * Getter for the Values.
     * 
     * @return the Map<String, Serializable>.
     */
    public Map<String, Serializable> getValues() {
        Map<String, Serializable> result = super.getValues();
        result.put(PROPERTY_SCRIPT_TYPE, this.getScriptType());
        result.put(PROPERTY_SCRIPT_DESCRIPTION, this.getScriptDescription());
        result.put(PROPERTY_SCRIPT_NAME, this.getScriptName());
        result.put(PROPERTY_SCRIPT_SOURCECODE, this.getScriptSourcecode());
        result.put(PROPERTY_SCRIPT_OWNER, this.getScriptOwner());
        return result;
    }

    /**
     * Setter for the Script.
     * 
     * @param newValue
     *            the Script.
     */
    public void setScript(Script newValue) {
        Script oldValue = this.script;
        this.script = newValue;
        this.updateProperty(PROPERTY_SCRIPT_SOURCECODE, ((oldValue != null) ? oldValue.getSourcecode() : ""),
                ((newValue != null) ? newValue.getSourcecode() : ""));
        this.updateProperty(PROPERTY_SCRIPT_DESCRIPTION, ((oldValue != null) ? oldValue.getDescription() : ""),
                ((newValue != null) ? newValue.getDescription() : ""));
        this.updateProperty(PROPERTY_SCRIPT_NAME, ((oldValue != null) ? oldValue.getName() : ""),
                ((newValue != null) ? newValue.getName() : ""));
        this.updateProperty(PROPERTY_SCRIPT_OWNER, ((oldValue != null) ? oldValue.getOwner() : ""),
                ((newValue != null) ? newValue.getOwner() : ""));
        this.updateProperty(PROPERTY_SCRIPT_TYPE, ((oldValue != null) ? oldValue.getType() : ""),
                ((newValue != null) ? newValue.getType() : ""));
        this.updateProperty(PROPERTY_SCRIPT_CONTEXTTYPE,
                ((oldValue != null) ? (oldValue.getContextType() != null ? oldValue.getContextType()
                        : null) : null),
                ((newValue != null) ? (newValue.getContextType() != null ? newValue.getContextType()
                        : null) : null));
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
     * @param newName
     *            the String.
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
     * Setter for the ScriptDescription.
     * 
     * @param newDescription
     *            the String.
     */
    public void setScriptDescription(String newDescription) {
        if (((script != null) && (script.getDescription() == null))) {
            Description description = new Description();
            script.setDescription(description);
        }
        String oldVal = script.getDescription().getValue();
        script.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_SCRIPT_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && script.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            script.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ScriptDescription.
     * 
     * @return the String.
     */
    public String getScriptDescription() {
        if ((((script == null) || (script.getDescription() == null)) || (script.getDescription().getValue() == null))) {
            return "";
        }
        return script.getDescription().getValue();
    }

    /**
     * Setter for the ScriptOwner.
     * 
     * @param newOwner
     *            the String.
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
     * @param newType
     *            the String.
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

    /**
     * Setter for the ScriptSourcecode.
     * 
     * @param newSourcecode
     *            the String.
     */
    public void setScriptSourcecode(String newSourcecode) {
        if (((script != null) && (script.getSourcecode() == null))) {
            ScriptSourceCode sourcecode = new ScriptSourceCode();
            script.setSourcecode(sourcecode);
        }
        String oldVal = script.getSourcecode().getValue();
        script.getSourcecode().setValue(newSourcecode);
        this.updateProperty(PROPERTY_SCRIPT_SOURCECODE, oldVal, newSourcecode);
        if (((!oldVal.equals(newSourcecode)) && script.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            script.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ScriptSourcecode.
     * 
     * @return the String.
     */
    public String getScriptSourcecode() {
        if ((((script == null) || (script.getSourcecode() == null)) || (script.getSourcecode().getValue() == null))) {
            return "";
        }
        return script.getSourcecode().getValue();
    }

    /**
     * @param scriptContextType
     *            The scriptContextType to set.
     */
    public void setScriptContextType(Code scriptContextType) {
        if (this.script != null) {
            Code oldValue = this.script.getContextType();
            this.script.setContextType(scriptContextType);
            this.updateProperty(PROPERTY_SCRIPT_CONTEXTTYPE, oldValue, scriptContextType);
        }
    }

    /**
     * @return Returns the scriptContextType.
     */
    public Code getScriptContextType() {
        if (this.script == null) {
            return null;
        }
        return this.script.getContextType();
    }
}
