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
package org.nabucco.framework.support.scripting.facade.component;

import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;
import org.nabucco.framework.support.scripting.facade.component.ScriptingComponent;
import org.nabucco.framework.support.scripting.facade.service.ScriptService;
import org.nabucco.framework.support.scripting.facade.service.compiler.ScriptCompilationService;
import org.nabucco.framework.support.scripting.facade.service.maintain.ScriptMaintainService;
import org.nabucco.framework.support.scripting.facade.service.produce.ScriptProduceService;
import org.nabucco.framework.support.scripting.facade.service.resolve.ScriptResolveService;
import org.nabucco.framework.support.scripting.facade.service.search.ScriptSearchService;

/**
 * ScriptingComponentLocalProxy<p/>Scripting component for NABUCCO<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public class ScriptingComponentLocalProxy implements ScriptingComponent {

    private static final long serialVersionUID = 1L;

    private final ScriptingComponentLocal delegate;

    /**
     * Constructs a new ScriptingComponentLocalProxy instance.
     *
     * @param delegate the ScriptingComponentLocal.
     */
    public ScriptingComponentLocalProxy(ScriptingComponentLocal delegate) {
        super();
        if ((delegate == null)) {
            throw new IllegalArgumentException("Cannot create local proxy for component [null].");
        }
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return this.delegate.getId();
    }

    @Override
    public String getName() {
        return this.delegate.getName();
    }

    @Override
    public String getJndiName() {
        return this.delegate.getJndiName();
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return this.delegate.getComponentRelationServiceLocal();
    }

    @Override
    public QueryFilterService getQueryFilterService() throws ServiceException {
        return this.delegate.getQueryFilterServiceLocal();
    }

    @Override
    public String toString() {
        return this.delegate.toString();
    }

    @Override
    public ScriptService getScriptService() throws ServiceException {
        return this.delegate.getScriptServiceLocal();
    }

    @Override
    public ScriptSearchService getScriptSearchService() throws ServiceException {
        return this.delegate.getScriptSearchServiceLocal();
    }

    @Override
    public ScriptMaintainService getScriptMaintainService() throws ServiceException {
        return this.delegate.getScriptMaintainServiceLocal();
    }

    @Override
    public ScriptProduceService getScriptProduceService() throws ServiceException {
        return this.delegate.getScriptProduceServiceLocal();
    }

    @Override
    public ScriptResolveService getScriptResolveService() throws ServiceException {
        return this.delegate.getScriptResolveServiceLocal();
    }

    @Override
    public ScriptCompilationService getScriptCompilationService() throws ServiceException {
        return this.delegate.getScriptCompilationServiceLocal();
    }
}
