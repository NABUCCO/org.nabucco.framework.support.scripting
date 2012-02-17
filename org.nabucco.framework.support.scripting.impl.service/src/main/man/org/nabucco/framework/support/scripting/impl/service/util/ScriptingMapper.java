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
package org.nabucco.framework.support.scripting.impl.service.util;

import java.util.Arrays;

import org.nabucco.common.scripting.ScriptContainer;
import org.nabucco.common.scripting.ScriptParameter;
import org.nabucco.common.scripting.ScriptType;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptCode;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptData;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptSourceCode;

/**
 * Mapper between Scripting Datatypes.
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ScriptingMapper {

    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(ScriptingMapper.class);

    /**
     * Singleton instance.
     */
    private static ScriptingMapper instance = new ScriptingMapper();

    /**
     * Private constructor.
     */
    private ScriptingMapper() {
    }

    /**
     * Singleton access.
     * 
     * @return the ScriptingMapper instance.
     */
    public static ScriptingMapper getInstance() {
        return instance;
    }

    /**
     * Maps a Script into a ScriptContainer.
     * 
     * @param script
     *            the script to map
     * 
     * @return the mapped script container
     */
    public ScriptContainer mapToContainer(Script script) {
        if (script == null) {
            return null;
        }

        ScriptType type = this.mapToScriptType(script.getType());

        if (type == null) {
            throw new IllegalArgumentException("Cannot map Script with type [null].");
        }

        String name = this.mapToName(script.getName());
        String sourceCode = this.mapToSourceCode(script.getSourcecode());
        byte[] byteCode = this.mapToByteCode(script.getCode());

        if (name == null || name.isEmpty()) {
            logger.warning("No Script Name defined!");
        }
        if (sourceCode == null || sourceCode.isEmpty()) {
            logger.warning("No Script Source Code defined!");
        }

        ScriptContainer container = new ScriptContainer(name, type, sourceCode);
        container.setByteCode(byteCode);

        NabuccoList<ScriptData> inputData = script.getInputData();
        if (inputData.isTraversable()) {
            for (ScriptData data : inputData) {
                ScriptParameter parameter = this.mapToParameter(data);
                container.getInputParameter().add(parameter);
            }
        }

        NabuccoList<ScriptData> outputData = script.getOutputData();
        if (outputData.isTraversable()) {
            for (ScriptData data : outputData) {
                ScriptParameter parameter = this.mapToParameter(data);
                container.getOutputParameter().add(parameter);
            }
        }

        return container;
    }

    /**
     * Map a Script Data into a Script Parameter.
     * 
     * @param data
     *            the data to map
     * 
     * @return the mapped parameter
     */
    public ScriptParameter mapToParameter(ScriptData data) {

        String name = this.mapToName(data.getName());
        String type = this.mapToName(data.getTypeName());
        NabuccoDatatype value = data.getValue();

        return new ScriptParameter(name, type, value);
    }

    /**
     * Maps the name into a string.
     * 
     * @param name
     *            the name to map
     * 
     * @return the mapped string, or null if it cannot be mapped
     */
    private String mapToName(Name name) {
        if (name == null || name.getValue() == null) {
            return null;
        }

        return name.getValue();
    }

    /**
     * Maps the source code into a string.
     * 
     * @param sourceCode
     *            the code to map
     * 
     * @return the mapped code, or null if it cannot be mapped
     */
    private String mapToSourceCode(ScriptSourceCode sourceCode) {
        if (sourceCode == null || sourceCode.getValue() == null) {
            return null;
        }

        return sourceCode.getValue();
    }

    /**
     * Maps the code into a string.
     * 
     * @param code
     *            the code to map
     * 
     * @return the mapped code, or null if it cannot be mapped
     */
    private byte[] mapToByteCode(ScriptCode code) {
        if (code == null || code.getValue() == null) {
            return null;
        }

        return Arrays.copyOf(code.getValue(), code.getValue().length);
    }

    /**
     * Map the {@link org.nabucco.framework.support.scripting.facade.datatype.ScriptType} into a
     * {@link ScriptType}.
     * 
     * @param scriptType
     *            the script type to map
     * 
     * @return the mapped script type
     */
    public ScriptType mapToScriptType(org.nabucco.framework.support.scripting.facade.datatype.ScriptType scriptType) {
        if (scriptType == null) {
            return null;
        }

        switch (scriptType) {

        case JAVA:
            return ScriptType.JAVA;
        case JAVASCRIPT:
            return ScriptType.JAVA_SCRIPT;
        case BEANSHELL:
            return ScriptType.BEANSHELL;
        case GROOVY:
            return ScriptType.GROOVY;
        case OGNL:
            return ScriptType.OGNL;
        case RUBY:
            return ScriptType.RUBY;
        }

        throw new IllegalStateException("The Script Type '" + scriptType + "' is not supported yet.");
    }
}
