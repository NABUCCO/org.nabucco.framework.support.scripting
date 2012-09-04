/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.framework.support.scripting.facade.datatype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.Code;
import org.nabucco.framework.base.facade.datatype.code.CodePath;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoListImpl;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptCode;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptData;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptSourceCode;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptType;

/**
 * Script<p/>Definition of a script.<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-29
 */
public class Script extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m1,1;", "l0,255;u0,n;m0,1;",
            "l3,12;u0,n;m1,1;", "m1,1;", "m1,1;", "l0,64000;u0,n;m1,1;", "l0,64000;u0,n;m0,1;", "m0,n;", "m0,n;" };

    public static final String NAME = "name";

    public static final String DESCRIPTION = "description";

    public static final String OWNER = "owner";

    public static final String TYPE = "type";

    public static final String CONTEXTTYPE = "contextType";

    public static final String SOURCECODE = "sourcecode";

    public static final String CODE = "code";

    public static final String INPUTDATA = "inputData";

    public static final String OUTPUTDATA = "outputData";

    /** The name of the script. */
    private Name name;

    /** A brief description of the script. */
    private Description description;

    /** The owner of the script */
    private Owner owner;

    /** The type (language) of the script. */
    private ScriptType type;

    /** The context (origin) of this script. */
    private Code contextType;

    private Long contextTypeRefId;

    protected static final String CONTEXTTYPE_CODEPATH = "nabucco.scripting.source";

    /** The script source code. */
    private ScriptSourceCode sourcecode;

    /** The script code. May be null for JavaScript and other script languages. */
    private ScriptCode code;

    /** The input parameters of the script. These are available in the javascript environment under their given name, but are not returned as the results of the script. */
    private NabuccoList<ScriptData> inputData;

    /** The output parameters of the script (results of the script). As the input parameters, these are also available under their given name, but are returned as results of the script (basically, these are kind of call-by-reference parameters). */
    private NabuccoList<ScriptData> outputData;

    /** Constructs a new Script instance. */
    public Script() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the Script.
     */
    protected void cloneObject(Script clone) {
        super.cloneObject(clone);
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getDescription() != null)) {
            clone.setDescription(this.getDescription().cloneObject());
        }
        if ((this.getOwner() != null)) {
            clone.setOwner(this.getOwner().cloneObject());
        }
        clone.setType(this.getType());
        if ((this.getContextType() != null)) {
            clone.setContextType(this.getContextType().cloneObject());
        }
        if ((this.getContextTypeRefId() != null)) {
            clone.setContextTypeRefId(this.getContextTypeRefId());
        }
        if ((this.getSourcecode() != null)) {
            clone.setSourcecode(this.getSourcecode().cloneObject());
        }
        if ((this.getCode() != null)) {
            clone.setCode(this.getCode().cloneObject());
        }
        if ((this.inputData != null)) {
            clone.inputData = this.inputData.cloneCollection();
        }
        if ((this.outputData != null)) {
            clone.outputData = this.outputData.cloneCollection();
        }
    }

    /**
     * Getter for the InputDataJPA.
     *
     * @return the List<ScriptData>.
     */
    List<ScriptData> getInputDataJPA() {
        if ((this.inputData == null)) {
            this.inputData = new NabuccoListImpl<ScriptData>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<ScriptData>) this.inputData).getDelegate();
    }

    /**
     * Setter for the InputDataJPA.
     *
     * @param inputData the List<ScriptData>.
     */
    void setInputDataJPA(List<ScriptData> inputData) {
        if ((this.inputData == null)) {
            this.inputData = new NabuccoListImpl<ScriptData>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<ScriptData>) this.inputData).setDelegate(inputData);
    }

    /**
     * Getter for the OutputDataJPA.
     *
     * @return the List<ScriptData>.
     */
    List<ScriptData> getOutputDataJPA() {
        if ((this.outputData == null)) {
            this.outputData = new NabuccoListImpl<ScriptData>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<ScriptData>) this.outputData).getDelegate();
    }

    /**
     * Setter for the OutputDataJPA.
     *
     * @param outputData the List<ScriptData>.
     */
    void setOutputDataJPA(List<ScriptData> outputData) {
        if ((this.outputData == null)) {
            this.outputData = new NabuccoListImpl<ScriptData>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<ScriptData>) this.outputData).setDelegate(outputData);
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(NabuccoDatatype.class).getPropertyMap());
        propertyMap.put(NAME,
                PropertyDescriptorSupport.createBasetype(NAME, Name.class, 3, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(DESCRIPTION, PropertyDescriptorSupport.createBasetype(DESCRIPTION, Description.class, 4,
                PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(OWNER,
                PropertyDescriptorSupport.createBasetype(OWNER, Owner.class, 5, PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(TYPE,
                PropertyDescriptorSupport.createEnumeration(TYPE, ScriptType.class, 6, PROPERTY_CONSTRAINTS[3], false));
        propertyMap.put(CONTEXTTYPE, PropertyDescriptorSupport.createDatatype(CONTEXTTYPE, Code.class, 7,
                PROPERTY_CONSTRAINTS[4], false, PropertyAssociationType.COMPONENT, CONTEXTTYPE_CODEPATH));
        propertyMap.put(SOURCECODE, PropertyDescriptorSupport.createBasetype(SOURCECODE, ScriptSourceCode.class, 8,
                PROPERTY_CONSTRAINTS[5], false));
        propertyMap.put(CODE,
                PropertyDescriptorSupport.createBasetype(CODE, ScriptCode.class, 9, PROPERTY_CONSTRAINTS[6], false));
        propertyMap.put(INPUTDATA, PropertyDescriptorSupport.createCollection(INPUTDATA, ScriptData.class, 10,
                PROPERTY_CONSTRAINTS[7], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(OUTPUTDATA, PropertyDescriptorSupport.createCollection(OUTPUTDATA, ScriptData.class, 11,
                PROPERTY_CONSTRAINTS[8], false, PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(Script.getPropertyDescriptor(NAME), this.name, null));
        properties.add(super.createProperty(Script.getPropertyDescriptor(DESCRIPTION), this.description, null));
        properties.add(super.createProperty(Script.getPropertyDescriptor(OWNER), this.owner, null));
        properties.add(super.createProperty(Script.getPropertyDescriptor(TYPE), this.getType(), null));
        properties.add(super.createProperty(Script.getPropertyDescriptor(CONTEXTTYPE), this.getContextType(),
                this.contextTypeRefId));
        properties.add(super.createProperty(Script.getPropertyDescriptor(SOURCECODE), this.sourcecode, null));
        properties.add(super.createProperty(Script.getPropertyDescriptor(CODE), this.code, null));
        properties.add(super.createProperty(Script.getPropertyDescriptor(INPUTDATA), this.inputData, null));
        properties.add(super.createProperty(Script.getPropertyDescriptor(OUTPUTDATA), this.outputData, null));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(NAME) && (property.getType() == Name.class))) {
            this.setName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DESCRIPTION) && (property.getType() == Description.class))) {
            this.setDescription(((Description) property.getInstance()));
            return true;
        } else if ((property.getName().equals(OWNER) && (property.getType() == Owner.class))) {
            this.setOwner(((Owner) property.getInstance()));
            return true;
        } else if ((property.getName().equals(TYPE) && (property.getType() == ScriptType.class))) {
            this.setType(((ScriptType) property.getInstance()));
            return true;
        } else if ((property.getName().equals(CONTEXTTYPE) && (property.getType() == Code.class))) {
            this.setContextType(((Code) property.getInstance()));
            return true;
        } else if ((property.getName().equals(SOURCECODE) && (property.getType() == ScriptSourceCode.class))) {
            this.setSourcecode(((ScriptSourceCode) property.getInstance()));
            return true;
        } else if ((property.getName().equals(CODE) && (property.getType() == ScriptCode.class))) {
            this.setCode(((ScriptCode) property.getInstance()));
            return true;
        } else if ((property.getName().equals(INPUTDATA) && (property.getType() == ScriptData.class))) {
            this.inputData = ((NabuccoList<ScriptData>) property.getInstance());
            return true;
        } else if ((property.getName().equals(OUTPUTDATA) && (property.getType() == ScriptData.class))) {
            this.outputData = ((NabuccoList<ScriptData>) property.getInstance());
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if ((this == obj)) {
            return true;
        }
        if ((obj == null)) {
            return false;
        }
        if ((this.getClass() != obj.getClass())) {
            return false;
        }
        if ((!super.equals(obj))) {
            return false;
        }
        final Script other = ((Script) obj);
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
        if ((this.description == null)) {
            if ((other.description != null))
                return false;
        } else if ((!this.description.equals(other.description)))
            return false;
        if ((this.owner == null)) {
            if ((other.owner != null))
                return false;
        } else if ((!this.owner.equals(other.owner)))
            return false;
        if ((this.type == null)) {
            if ((other.type != null))
                return false;
        } else if ((!this.type.equals(other.type)))
            return false;
        if ((this.contextType == null)) {
            if ((other.contextType != null))
                return false;
        } else if ((!this.contextType.equals(other.contextType)))
            return false;
        if ((this.contextTypeRefId == null)) {
            if ((other.contextTypeRefId != null))
                return false;
        } else if ((!this.contextTypeRefId.equals(other.contextTypeRefId)))
            return false;
        if ((this.sourcecode == null)) {
            if ((other.sourcecode != null))
                return false;
        } else if ((!this.sourcecode.equals(other.sourcecode)))
            return false;
        if ((this.code == null)) {
            if ((other.code != null))
                return false;
        } else if ((!this.code.equals(other.code)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.owner == null) ? 0 : this.owner.hashCode()));
        result = ((PRIME * result) + ((this.type == null) ? 0 : this.type.hashCode()));
        result = ((PRIME * result) + ((this.contextType == null) ? 0 : this.contextType.hashCode()));
        result = ((PRIME * result) + ((this.contextTypeRefId == null) ? 0 : this.contextTypeRefId.hashCode()));
        result = ((PRIME * result) + ((this.sourcecode == null) ? 0 : this.sourcecode.hashCode()));
        result = ((PRIME * result) + ((this.code == null) ? 0 : this.code.hashCode()));
        return result;
    }

    @Override
    public Script cloneObject() {
        Script clone = new Script();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * The name of the script.
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * The name of the script.
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * The name of the script.
     *
     * @param name the String.
     */
    public void setName(String name) {
        if ((this.name == null)) {
            if ((name == null)) {
                return;
            }
            this.name = new Name();
        }
        this.name.setValue(name);
    }

    /**
     * A brief description of the script.
     *
     * @return the Description.
     */
    public Description getDescription() {
        return this.description;
    }

    /**
     * A brief description of the script.
     *
     * @param description the Description.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * A brief description of the script.
     *
     * @param description the String.
     */
    public void setDescription(String description) {
        if ((this.description == null)) {
            if ((description == null)) {
                return;
            }
            this.description = new Description();
        }
        this.description.setValue(description);
    }

    /**
     * The owner of the script
     *
     * @return the Owner.
     */
    public Owner getOwner() {
        return this.owner;
    }

    /**
     * The owner of the script
     *
     * @param owner the Owner.
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * The owner of the script
     *
     * @param owner the String.
     */
    public void setOwner(String owner) {
        if ((this.owner == null)) {
            if ((owner == null)) {
                return;
            }
            this.owner = new Owner();
        }
        this.owner.setValue(owner);
    }

    /**
     * The type (language) of the script.
     *
     * @return the ScriptType.
     */
    public ScriptType getType() {
        return this.type;
    }

    /**
     * The type (language) of the script.
     *
     * @param type the ScriptType.
     */
    public void setType(ScriptType type) {
        this.type = type;
    }

    /**
     * The type (language) of the script.
     *
     * @param type the String.
     */
    public void setType(String type) {
        if ((type == null)) {
            this.type = null;
        } else {
            this.type = ScriptType.valueOf(type);
        }
    }

    /**
     * The context (origin) of this script.
     *
     * @param contextType the Code.
     */
    public void setContextType(Code contextType) {
        this.contextType = contextType;
        if ((contextType != null)) {
            this.setContextTypeRefId(contextType.getId());
        } else {
            this.setContextTypeRefId(null);
        }
    }

    /**
     * The context (origin) of this script.
     *
     * @return the Code.
     */
    public Code getContextType() {
        return this.contextType;
    }

    /**
     * Getter for the ContextTypeRefId.
     *
     * @return the Long.
     */
    public Long getContextTypeRefId() {
        return this.contextTypeRefId;
    }

    /**
     * Setter for the ContextTypeRefId.
     *
     * @param contextTypeRefId the Long.
     */
    public void setContextTypeRefId(Long contextTypeRefId) {
        this.contextTypeRefId = contextTypeRefId;
    }

    /**
     * The script source code.
     *
     * @return the ScriptSourceCode.
     */
    public ScriptSourceCode getSourcecode() {
        return this.sourcecode;
    }

    /**
     * The script source code.
     *
     * @param sourcecode the ScriptSourceCode.
     */
    public void setSourcecode(ScriptSourceCode sourcecode) {
        this.sourcecode = sourcecode;
    }

    /**
     * The script source code.
     *
     * @param sourcecode the String.
     */
    public void setSourcecode(String sourcecode) {
        if ((this.sourcecode == null)) {
            if ((sourcecode == null)) {
                return;
            }
            this.sourcecode = new ScriptSourceCode();
        }
        this.sourcecode.setValue(sourcecode);
    }

    /**
     * The script code. May be null for JavaScript and other script languages.
     *
     * @return the ScriptCode.
     */
    public ScriptCode getCode() {
        return this.code;
    }

    /**
     * The script code. May be null for JavaScript and other script languages.
     *
     * @param code the ScriptCode.
     */
    public void setCode(ScriptCode code) {
        this.code = code;
    }

    /**
     * The script code. May be null for JavaScript and other script languages.
     *
     * @param code the byte[].
     */
    public void setCode(byte[] code) {
        if ((this.code == null)) {
            if ((code == null)) {
                return;
            }
            this.code = new ScriptCode();
        }
        this.code.setValue(code);
    }

    /**
     * The input parameters of the script. These are available in the javascript environment under their given name, but are not returned as the results of the script.
     *
     * @return the NabuccoList<ScriptData>.
     */
    public NabuccoList<ScriptData> getInputData() {
        if ((this.inputData == null)) {
            this.inputData = new NabuccoListImpl<ScriptData>(NabuccoCollectionState.INITIALIZED);
        }
        return this.inputData;
    }

    /**
     * The output parameters of the script (results of the script). As the input parameters, these are also available under their given name, but are returned as results of the script (basically, these are kind of call-by-reference parameters).
     *
     * @return the NabuccoList<ScriptData>.
     */
    public NabuccoList<ScriptData> getOutputData() {
        if ((this.outputData == null)) {
            this.outputData = new NabuccoListImpl<ScriptData>(NabuccoCollectionState.INITIALIZED);
        }
        return this.outputData;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(Script.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(Script.class).getAllProperties();
    }

    /**
     * Getter for the ContextTypeCodePath.
     *
     * @return the CodePath.
     */
    public static CodePath getContextTypeCodePath() {
        return new CodePath(CONTEXTTYPE_CODEPATH);
    }
}
