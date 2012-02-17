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
package org.nabucco.framework.support.scripting.impl.service.engine.java;

import java.util.List;

import org.nabucco.common.scripting.ScriptContainer;
import org.nabucco.common.scripting.ScriptParameter;
import org.nabucco.common.scripting.engine.ScriptingEngine;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptData;
import org.nabucco.framework.support.scripting.facade.exception.ScriptExecutionException;
import org.nabucco.framework.support.scripting.impl.service.engine.ScriptRunner;
import org.nabucco.framework.support.scripting.impl.service.util.ScriptingMapper;

/**
 * EcjScriptRunner
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class EcjScriptRunner extends ScriptRunner {

    @Override
    public List<ScriptData> run(Script script) throws ScriptExecutionException {

        ScriptContainer container = ScriptingMapper.getInstance().mapToContainer(script);

        try {
            ScriptingEngine engine = new ScriptingEngine(container.getType());
            engine.execute(container);

        } catch (Exception e) {
            throw new ScriptExecutionException("Error exeuting script '" + container.getName() + "'.", e);
        }

        NabuccoList<ScriptData> outputList = script.getOutputData();

        for (ScriptData output : outputList) {
            if (output.getName() == null || output.getName().getValue() == null) {
                continue;
            }
            if (output.getTypeName() == null || output.getTypeName().getValue() == null) {
                continue;
            }

            String name = output.getName().getValue();
            String type = output.getTypeName().getValue();

            for (ScriptParameter parameter : container.getOutputParameter()) {
                if (name.equals(parameter.getName()) && type.equals(parameter.getType())) {
                    output.setValue((NabuccoDatatype) parameter.getValue());
                    break;
                }
            }
        }

        return outputList;
    }
}
