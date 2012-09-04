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
package org.nabucco.framework.support.scripting.facade.component;

import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;
import org.nabucco.framework.support.scripting.facade.service.ScriptService;
import org.nabucco.framework.support.scripting.facade.service.compiler.ScriptCompilationService;
import org.nabucco.framework.support.scripting.facade.service.maintain.ScriptMaintainService;
import org.nabucco.framework.support.scripting.facade.service.produce.ScriptProduceService;
import org.nabucco.framework.support.scripting.facade.service.resolve.ScriptResolveService;
import org.nabucco.framework.support.scripting.facade.service.search.ScriptSearchService;

/**
 * ScriptingComponentLocal<p/>Scripting component for NABUCCO<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public interface ScriptingComponentLocal extends ScriptingComponent {

    /**
     * Getter for the ComponentRelationServiceLocal.
     *
     * @return the ComponentRelationService.
     * @throws ServiceException
     */
    ComponentRelationService getComponentRelationServiceLocal() throws ServiceException;

    /**
     * Getter for the QueryFilterServiceLocal.
     *
     * @return the QueryFilterService.
     * @throws ServiceException
     */
    QueryFilterService getQueryFilterServiceLocal() throws ServiceException;

    /**
     * Getter for the ScriptServiceLocal.
     *
     * @return the ScriptService.
     * @throws ServiceException
     */
    ScriptService getScriptServiceLocal() throws ServiceException;

    /**
     * Getter for the ScriptSearchServiceLocal.
     *
     * @return the ScriptSearchService.
     * @throws ServiceException
     */
    ScriptSearchService getScriptSearchServiceLocal() throws ServiceException;

    /**
     * Getter for the ScriptMaintainServiceLocal.
     *
     * @return the ScriptMaintainService.
     * @throws ServiceException
     */
    ScriptMaintainService getScriptMaintainServiceLocal() throws ServiceException;

    /**
     * Getter for the ScriptProduceServiceLocal.
     *
     * @return the ScriptProduceService.
     * @throws ServiceException
     */
    ScriptProduceService getScriptProduceServiceLocal() throws ServiceException;

    /**
     * Getter for the ScriptResolveServiceLocal.
     *
     * @return the ScriptResolveService.
     * @throws ServiceException
     */
    ScriptResolveService getScriptResolveServiceLocal() throws ServiceException;

    /**
     * Getter for the ScriptCompilationServiceLocal.
     *
     * @return the ScriptCompilationService.
     * @throws ServiceException
     */
    ScriptCompilationService getScriptCompilationServiceLocal() throws ServiceException;
}
