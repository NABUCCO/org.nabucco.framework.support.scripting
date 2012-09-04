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
package org.nabucco.framework.support.scripting.impl.service;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.base.impl.service.maintain.PersistenceServiceHandler;
import org.nabucco.framework.base.impl.service.maintain.PersistenceServiceHandlerSupport;
import org.nabucco.framework.support.scripting.facade.exception.ScriptExecutionException;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionMsg;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionResultMsg;

/**
 * ExecuteScriptServiceHandler<p/>Service for executing Scripts<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2011-01-30
 */
public abstract class ExecuteScriptServiceHandler extends PersistenceServiceHandlerSupport implements ServiceHandler,
        PersistenceServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.framework.support.scripting.impl.service.ExecuteScriptServiceHandler";

    /** Constructs a new ExecuteScriptServiceHandler instance. */
    public ExecuteScriptServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<ScriptExecutionMsg>.
     * @return the ServiceResponse<ScriptExecutionResultMsg>.
     * @throws ScriptExecutionException
     */
    protected ServiceResponse<ScriptExecutionResultMsg> invoke(ServiceRequest<ScriptExecutionMsg> rq)
            throws ScriptExecutionException {
        ServiceResponse<ScriptExecutionResultMsg> rs;
        ScriptExecutionResultMsg msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.executeScript(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<ScriptExecutionResultMsg>(rq.getContext());
            rs.setResponseMessage(msg);
            return rs;
        } catch (ScriptExecutionException e) {
            super.getLogger().error(e);
            throw e;
        } catch (NabuccoException e) {
            super.getLogger().error(e);
            ScriptExecutionException wrappedException = new ScriptExecutionException(e);
            throw wrappedException;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new ScriptExecutionException("Error during service invocation.", e);
        }
    }

    /**
     * Executes a given Script in its related engine.
     *
     * @param msg the ScriptExecutionMsg.
     * @return the ScriptExecutionResultMsg.
     * @throws ScriptExecutionException
     */
    protected abstract ScriptExecutionResultMsg executeScript(ScriptExecutionMsg msg) throws ScriptExecutionException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
