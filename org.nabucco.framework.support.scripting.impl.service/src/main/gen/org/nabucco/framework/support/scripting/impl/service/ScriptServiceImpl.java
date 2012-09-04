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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.support.scripting.facade.exception.ScriptExecutionException;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionMsg;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionResultMsg;
import org.nabucco.framework.support.scripting.facade.service.ScriptService;

/**
 * ScriptServiceImpl<p/>Service for executing Scripts<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2011-01-30
 */
public class ScriptServiceImpl extends ServiceSupport implements ScriptService {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ScriptService";

    private static Map<String, String[]> ASPECTS;

    private ExecuteScriptServiceHandler executeScriptServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ScriptServiceImpl instance. */
    public ScriptServiceImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.executeScriptServiceHandler = injector.inject(ExecuteScriptServiceHandler.getId());
        if ((this.executeScriptServiceHandler != null)) {
            this.executeScriptServiceHandler.setPersistenceManager(persistenceManager);
            this.executeScriptServiceHandler.setLogger(super.getLogger());
        }
    }

    @Override
    public void preDestroy() {
        super.preDestroy();
    }

    @Override
    public String[] getAspects(String operationName) {
        if ((ASPECTS == null)) {
            ASPECTS = new HashMap<String, String[]>();
            ASPECTS.put("executeScript", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<ScriptExecutionResultMsg> executeScript(ServiceRequest<ScriptExecutionMsg> rq)
            throws ScriptExecutionException {
        if ((this.executeScriptServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for executeScript().");
            throw new InjectionException("No service implementation configured for executeScript().");
        }
        ServiceResponse<ScriptExecutionResultMsg> rs;
        this.executeScriptServiceHandler.init();
        rs = this.executeScriptServiceHandler.invoke(rq);
        this.executeScriptServiceHandler.finish();
        return rs;
    }
}
