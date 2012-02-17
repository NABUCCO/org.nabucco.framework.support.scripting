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
package org.nabucco.framework.support.scripting.impl.service.maintain;

import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.message.ScriptListMsg;

/**
 * MaintainScriptsServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MaintainScriptsServiceHandlerImpl extends MaintainScriptsServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    public ScriptListMsg maintainScripts(ScriptListMsg msg) throws MaintainException {

        ScriptListMsg rs = new ScriptListMsg();

        for (Script script : msg.getScriptList()) {

            try {
                script = super.getPersistenceManager().persist(script);
                rs.getScriptList().add(script);

                script.getInputData().size();
                script.getOutputData().size();

            } catch (Exception e) {
                throw new MaintainException("Error maintaining Script [" + script.getName() + "].", e);
            }
        }

        return rs;
    }

}
