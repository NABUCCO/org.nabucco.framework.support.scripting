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
package org.nabucco.framework.support.scripting.impl.component;

import org.nabucco.framework.base.facade.component.handler.PostConstructHandler;
import org.nabucco.framework.base.facade.component.handler.PreDestroyHandler;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;
import org.nabucco.framework.base.impl.component.ComponentSupport;
import org.nabucco.framework.support.scripting.facade.component.ScriptingComponentLocal;
import org.nabucco.framework.support.scripting.facade.component.ScriptingComponentRemote;
import org.nabucco.framework.support.scripting.facade.service.ScriptService;
import org.nabucco.framework.support.scripting.facade.service.compiler.ScriptCompilationService;
import org.nabucco.framework.support.scripting.facade.service.maintain.ScriptMaintainService;
import org.nabucco.framework.support.scripting.facade.service.produce.ScriptProduceService;
import org.nabucco.framework.support.scripting.facade.service.resolve.ScriptResolveService;
import org.nabucco.framework.support.scripting.facade.service.search.ScriptSearchService;

/**
 * ScriptingComponentImpl<p/>Scripting component for NABUCCO<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public class ScriptingComponentImpl extends ComponentSupport implements ScriptingComponentLocal,
        ScriptingComponentRemote {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ScriptingComponent";

    /** Constructs a new ScriptingComponentImpl instance. */
    public ScriptingComponentImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PostConstructHandler handler = injector.inject(PostConstructHandler.getId());
        if ((handler == null)) {
            if (super.getLogger().isDebugEnabled()) {
                super.getLogger().debug("No post construct handler configured for \'", ID, "\'.");
            }
            return;
        }
        handler.setLocatable(this);
        handler.setLogger(super.getLogger());
        handler.invoke();
    }

    @Override
    public void preDestroy() {
        super.preDestroy();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PreDestroyHandler handler = injector.inject(PreDestroyHandler.getId());
        if ((handler == null)) {
            if (super.getLogger().isDebugEnabled()) {
                super.getLogger().debug("No pre destroy handler configured for \'", ID, "\'.");
            }
            return;
        }
        handler.setLocatable(this);
        handler.setLogger(super.getLogger());
        handler.invoke();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return COMPONENT_NAME;
    }

    @Override
    public String getJndiName() {
        return JNDI_NAME;
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.COMPONENT_RELATION_SERVICE_REMOTE,
                ComponentRelationService.class);
    }

    @Override
    public ComponentRelationService getComponentRelationServiceLocal() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.COMPONENT_RELATION_SERVICE_LOCAL,
                ComponentRelationService.class);
    }

    @Override
    public QueryFilterService getQueryFilterService() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.QUERY_FILTER_SERVICE_REMOTE, QueryFilterService.class);
    }

    @Override
    public QueryFilterService getQueryFilterServiceLocal() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.QUERY_FILTER_SERVICE_LOCAL, QueryFilterService.class);
    }

    @Override
    public ScriptService getScriptServiceLocal() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.SCRIPT_SERVICE_LOCAL, ScriptService.class);
    }

    @Override
    public ScriptService getScriptService() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.SCRIPT_SERVICE_REMOTE, ScriptService.class);
    }

    @Override
    public ScriptSearchService getScriptSearchServiceLocal() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.SCRIPT_SEARCH_SERVICE_LOCAL, ScriptSearchService.class);
    }

    @Override
    public ScriptSearchService getScriptSearchService() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.SCRIPT_SEARCH_SERVICE_REMOTE, ScriptSearchService.class);
    }

    @Override
    public ScriptMaintainService getScriptMaintainServiceLocal() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.SCRIPT_MAINTAIN_SERVICE_LOCAL, ScriptMaintainService.class);
    }

    @Override
    public ScriptMaintainService getScriptMaintainService() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.SCRIPT_MAINTAIN_SERVICE_REMOTE, ScriptMaintainService.class);
    }

    @Override
    public ScriptProduceService getScriptProduceServiceLocal() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.SCRIPT_PRODUCE_SERVICE_LOCAL, ScriptProduceService.class);
    }

    @Override
    public ScriptProduceService getScriptProduceService() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.SCRIPT_PRODUCE_SERVICE_REMOTE, ScriptProduceService.class);
    }

    @Override
    public ScriptResolveService getScriptResolveServiceLocal() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.SCRIPT_RESOLVE_SERVICE_LOCAL, ScriptResolveService.class);
    }

    @Override
    public ScriptResolveService getScriptResolveService() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.SCRIPT_RESOLVE_SERVICE_REMOTE, ScriptResolveService.class);
    }

    @Override
    public ScriptCompilationService getScriptCompilationServiceLocal() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.SCRIPT_COMPILATION_SERVICE_LOCAL,
                ScriptCompilationService.class);
    }

    @Override
    public ScriptCompilationService getScriptCompilationService() throws ServiceException {
        return super.lookup(ScriptingComponentJndiNames.SCRIPT_COMPILATION_SERVICE_REMOTE,
                ScriptCompilationService.class);
    }
}
