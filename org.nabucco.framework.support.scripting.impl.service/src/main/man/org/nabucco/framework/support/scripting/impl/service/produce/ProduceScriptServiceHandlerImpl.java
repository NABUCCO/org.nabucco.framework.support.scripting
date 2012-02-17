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
package org.nabucco.framework.support.scripting.impl.service.produce;

import org.nabucco.framework.base.facade.component.NabuccoInstance;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.message.ScriptMsg;
import org.nabucco.framework.support.scripting.facade.message.produce.ProduceScriptRq;

/**
 * ProduceScriptServiceHandlerImpl
 * 
 * @author Jens Wurm, PRODYNA AG, 2010-04-30
 */
public class ProduceScriptServiceHandlerImpl extends ProduceScriptServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected ScriptMsg produceScript(ProduceScriptRq msg) throws ProduceException {

        Script script = new Script();
        script.setName(new Name());
        script.setDescription(new Description());
        script.setOwner(NabuccoInstance.getInstance().getOwner());
        script.setType(msg.getScriptType());
        script.setDatatypeState(DatatypeState.INITIALIZED);

        ScriptMsg rsMsg = new ScriptMsg();
        rsMsg.setScript(script);

        return rsMsg;
    }
}
