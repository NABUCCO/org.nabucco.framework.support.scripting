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
package org.nabucco.framework.support.scripting.impl.service.resolve;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.support.scripting.facade.message.ScriptMsg;
import org.nabucco.framework.support.scripting.facade.service.resolve.ScriptResolveService;

/**
 * ScriptResolveServiceImpl<p/>Service for searching scripts.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-09-16
 */
public class ScriptResolveServiceImpl extends ServiceSupport implements ScriptResolveService {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ScriptResolveService";

    private static Map<String, String[]> ASPECTS;

    private ResolveScriptServiceHandler resolveScriptServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ScriptResolveServiceImpl instance. */
    public ScriptResolveServiceImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.resolveScriptServiceHandler = injector.inject(ResolveScriptServiceHandler.getId());
        if ((this.resolveScriptServiceHandler != null)) {
            this.resolveScriptServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveScriptServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("resolveScript", new String[] { "org.nabucco.aspect.resolving" });
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<ScriptMsg> resolveScript(ServiceRequest<ScriptMsg> rq) throws ResolveException {
        if ((this.resolveScriptServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveScript().");
            throw new InjectionException("No service implementation configured for resolveScript().");
        }
        ServiceResponse<ScriptMsg> rs;
        this.resolveScriptServiceHandler.init();
        rs = this.resolveScriptServiceHandler.invoke(rq);
        this.resolveScriptServiceHandler.finish();
        return rs;
    }
}
