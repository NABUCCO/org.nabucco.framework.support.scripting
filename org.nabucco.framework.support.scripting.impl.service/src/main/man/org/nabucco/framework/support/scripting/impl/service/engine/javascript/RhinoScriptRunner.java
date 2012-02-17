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
package org.nabucco.framework.support.scripting.impl.service.engine.javascript;

import java.util.ArrayList;
import java.util.List;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.ScriptableObject;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptData;
import org.nabucco.framework.support.scripting.facade.exception.ScriptExecutionException;
import org.nabucco.framework.support.scripting.impl.service.engine.ScriptRunner;
import org.nabucco.framework.support.scripting.impl.service.engine.javascript.util.LocatorUtility;
import org.nabucco.framework.support.scripting.impl.service.engine.javascript.util.invocation.ServiceOperationProxyFuntion;

/**
 * Encapsulates / adapts / facades access to the pure Rhino JavaScript classes of Mozilla, that are
 * not part of the official Java 6 release.
 * <p/>
 * This pure Rhino classes provide access to the <b>E4X</b> functionality.
 * 
 * @author Lasse Asbach, PRODYNA AG
 * @author Nicolas Moser, PRODYNA AG
 */
public class RhinoScriptRunner extends ScriptRunner {

    /** The logger */
    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(RhinoScriptRunner.class);

    /** The service context for service invocations. */
    private ServiceMessageContext serviceContext;

    /**
     * Creates a new {@link RhinoScriptRunner} instance.
     * 
     * @param context
     *            the service message context
     */
    public RhinoScriptRunner(ServiceMessageContext context) {
        this.serviceContext = context;
    }

    @Override
    public List<ScriptData> run(Script script) throws ScriptExecutionException {

        logger.info("Start execution of script '", script.getName().getValue(), "'.");

        try {
            Context context = Context.enter();
            ScriptableObject scope = context.initStandardObjects();

            ServiceOperationProxyFuntion serviceOperationProxyFuntion = new ServiceOperationProxyFuntion(
                    this.serviceContext);

            serviceOperationProxyFuntion.addFunctions(scope);

            this.addParameters(script, scope);

            String scriptName = script.getName().getValue();
            String scriptCode = script.getSourcecode().getValue();

            context.evaluateString(scope, scriptCode, scriptName, 1, null);
            return this.evaluateResult(script, scope);

        } catch (Exception e) {
            throw new ScriptExecutionException("Error executing script [" + script.getName() + "].", e);
        } finally {
            Context.exit();
            logger.info("Finish execution of script '", script.getName().getValue(), "'.");
        }
    }

    /**
     * Add the parameters to the script so that they are accessable in the code.
     * 
     * @param script
     *            the script holding parameters
     * @param scope
     *            the script scope
     */
    private void addParameters(Script script, ScriptableObject scope) {

        // Default Parameters
        Object jsLogger = Context.javaToJS(logger, scope);
        scope.put("logger", scope, jsLogger);
        logger.debug("Add script input parameter 'logger'.");

        Object jsLocator = Context.javaToJS(new LocatorUtility(), scope);
        scope.put("locator", scope, jsLocator);
        logger.debug("Add script input parameter 'locator'.");

        // Input Data
        for (ScriptData data : script.getInputData()) {

            String name = this.addData(data, scope);

            if (name == null) {
                throw new IllegalArgumentException("Cannot initialize input parameter [null].");
            }

            logger.debug("Add script input parameter '", name, "'.");
        }

        // Output Data
        for (ScriptData data : script.getOutputData()) {

            String name = this.addData(data, scope);

            if (name == null) {
                throw new IllegalArgumentException("Cannot initialize output parameter [null].");
            }

            logger.debug("Add script output parameter '", name, "'.");
        }
    }

    /**
     * Add a {@link ScriptData} object to the script.
     * 
     * @param data
     *            the script data
     * @param scope
     *            the script scope
     * 
     * @return the name of the data, or null if invalid
     */
    private String addData(ScriptData data, ScriptableObject scope) {
        if (data.getName() == null || data.getName().getValue() == null || data.getValue() == null) {
            logger.warning("Script data is not valid [null].");
            return null;
        }

        Name name = data.getName();

        if (name == null || name.getValue() == null) {
            throw new IllegalArgumentException("Cannot initialize script parameter [null].");
        }

        Object jsValue = Context.javaToJS(data.getValue(), scope);
        scope.put(name.getValue(), scope, jsValue);

        return name.getValue();
    }

    /**
     * Evaluate the script execution.
     * 
     * @param script
     *            the executed script
     * @param scope
     *            the script scope
     * 
     * @return the output parameters
     */
    private List<ScriptData> evaluateResult(Script script, ScriptableObject scope) {
        List<ScriptData> resultList = new ArrayList<ScriptData>();

        for (ScriptData data : script.getOutputData()) {
            if (data.getName() == null || data.getName().getValue() == null) {
                logger.warning("Script output data is not valid [null].");
                continue;
            }

            String name = data.getName().getValue();

            Object obj = scope.get(name, scope);
            if (obj instanceof NativeJavaObject) {
                obj = ((NativeJavaObject) obj).unwrap();
            }

            ScriptData scriptData = new ScriptData();
            scriptData.setName(name);
            scriptData.setDescription(data.getDescription());
            scriptData.setValue((NabuccoDatatype) obj);

            resultList.add(scriptData);
        }

        return resultList;
    }

}
