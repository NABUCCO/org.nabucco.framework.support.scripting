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
package org.nabucco.framework.support.scripting.impl.service.produce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.support.scripting.facade.message.ScriptMsg;
import org.nabucco.framework.support.scripting.facade.message.produce.ProduceScriptRq;
import org.nabucco.framework.support.scripting.facade.service.produce.ScriptProduceService;

/**
 * ScriptProduceServiceImpl<p/>Service for producing new scripts.<p/>
 *
 * @version 1.0
 * @author Jens Wurm, PRODYNA AG, 2010-04-30
 */
public class ScriptProduceServiceImpl extends ServiceSupport implements ScriptProduceService {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ScriptProduceService";

    private static Map<String, String[]> ASPECTS;

    private ProduceScriptServiceHandler produceScriptServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ScriptProduceServiceImpl instance. */
    public ScriptProduceServiceImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.produceScriptServiceHandler = injector.inject(ProduceScriptServiceHandler.getId());
        if ((this.produceScriptServiceHandler != null)) {
            this.produceScriptServiceHandler.setPersistenceManager(persistenceManager);
            this.produceScriptServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("produceScript", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<ScriptMsg> produceScript(ServiceRequest<ProduceScriptRq> rq) throws ProduceException {
        if ((this.produceScriptServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceScript().");
            throw new InjectionException("No service implementation configured for produceScript().");
        }
        ServiceResponse<ScriptMsg> rs;
        this.produceScriptServiceHandler.init();
        rs = this.produceScriptServiceHandler.invoke(rq);
        this.produceScriptServiceHandler.finish();
        return rs;
    }
}
