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
package org.nabucco.framework.support.scripting.facade.datatype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;

/**
 * ScriptData<p/>Definition of a script parameter. It is accessible from within a script.<p/>
 *
 * @author Jens Wurm, PRODYNA AG, 2010-05-03
 */
public class ScriptData extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m1,1;", "l0,255;u0,n;m1,1;",
            "l0,255;u0,n;m1,1;", "m1,1;" };

    public static final String NAME = "name";

    public static final String DESCRIPTION = "description";

    public static final String TYPENAME = "typeName";

    public static final String VALUE = "value";

    /** The name of the parameter based on which it will be accessible in the script. */
    private Name name;

    /** A brief description of the data. */
    private Description description;

    /** The full qualified name of the parameter type. */
    private Name typeName;

    /** The value that shall be passed into the script */
    private NabuccoDatatype value;

    /** Constructs a new ScriptData instance. */
    public ScriptData() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the ScriptData.
     */
    protected void cloneObject(ScriptData clone) {
        super.cloneObject(clone);
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getDescription() != null)) {
            clone.setDescription(this.getDescription().cloneObject());
        }
        if ((this.getTypeName() != null)) {
            clone.setTypeName(this.getTypeName().cloneObject());
        }
        if ((this.getValue() != null)) {
            clone.setValue(this.getValue().cloneObject());
        }
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
        propertyMap.put(TYPENAME,
                PropertyDescriptorSupport.createBasetype(TYPENAME, Name.class, 5, PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(VALUE, PropertyDescriptorSupport.createDatatype(VALUE, NabuccoDatatype.class, 6,
                PROPERTY_CONSTRAINTS[3], false, PropertyAssociationType.COMPONENT));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(ScriptData.getPropertyDescriptor(NAME), this.name, null));
        properties.add(super.createProperty(ScriptData.getPropertyDescriptor(DESCRIPTION), this.description, null));
        properties.add(super.createProperty(ScriptData.getPropertyDescriptor(TYPENAME), this.typeName, null));
        properties.add(super.createProperty(ScriptData.getPropertyDescriptor(VALUE), this.getValue(), null));
        return properties;
    }

    @Override
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
        } else if ((property.getName().equals(TYPENAME) && (property.getType() == Name.class))) {
            this.setTypeName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(VALUE) && (property.getType() == NabuccoDatatype.class))) {
            this.setValue(((NabuccoDatatype) property.getInstance()));
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
        final ScriptData other = ((ScriptData) obj);
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
        if ((this.typeName == null)) {
            if ((other.typeName != null))
                return false;
        } else if ((!this.typeName.equals(other.typeName)))
            return false;
        if ((this.value == null)) {
            if ((other.value != null))
                return false;
        } else if ((!this.value.equals(other.value)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.typeName == null) ? 0 : this.typeName.hashCode()));
        result = ((PRIME * result) + ((this.value == null) ? 0 : this.value.hashCode()));
        return result;
    }

    @Override
    public ScriptData cloneObject() {
        ScriptData clone = new ScriptData();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * The name of the parameter based on which it will be accessible in the script.
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * The name of the parameter based on which it will be accessible in the script.
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * The name of the parameter based on which it will be accessible in the script.
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
     * A brief description of the data.
     *
     * @return the Description.
     */
    public Description getDescription() {
        return this.description;
    }

    /**
     * A brief description of the data.
     *
     * @param description the Description.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * A brief description of the data.
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
     * The full qualified name of the parameter type.
     *
     * @return the Name.
     */
    public Name getTypeName() {
        return this.typeName;
    }

    /**
     * The full qualified name of the parameter type.
     *
     * @param typeName the Name.
     */
    public void setTypeName(Name typeName) {
        this.typeName = typeName;
    }

    /**
     * The full qualified name of the parameter type.
     *
     * @param typeName the String.
     */
    public void setTypeName(String typeName) {
        if ((this.typeName == null)) {
            if ((typeName == null)) {
                return;
            }
            this.typeName = new Name();
        }
        this.typeName.setValue(typeName);
    }

    /**
     * The value that shall be passed into the script
     *
     * @param value the NabuccoDatatype.
     */
    public void setValue(NabuccoDatatype value) {
        this.value = value;
    }

    /**
     * The value that shall be passed into the script
     *
     * @return the NabuccoDatatype.
     */
    public NabuccoDatatype getValue() {
        return this.value;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(ScriptData.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ScriptData.class).getAllProperties();
    }
}
