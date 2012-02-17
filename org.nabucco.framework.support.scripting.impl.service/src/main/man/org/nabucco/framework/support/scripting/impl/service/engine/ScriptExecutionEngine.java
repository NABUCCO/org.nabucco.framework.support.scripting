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
package org.nabucco.framework.support.scripting.impl.service.engine;

import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.exception.ScriptExecutionException;

/**
 * ScriptExecutionEngine
 * 
 * @author Jens Wurm, PRODYNA AG
 */
public interface ScriptExecutionEngine {

    /**
     * Invoke and execute the script.
     * 
     * @param script
     *            the script to execute
     * 
     * @throws ScriptExecutionException
     */
    public void invoke(Script script) throws ScriptExecutionException;
}
