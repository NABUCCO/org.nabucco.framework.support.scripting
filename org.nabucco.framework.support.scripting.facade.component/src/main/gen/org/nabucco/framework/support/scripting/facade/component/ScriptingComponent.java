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

import org.nabucco.framework.base.facade.component.Component;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.support.scripting.facade.service.ScriptService;
import org.nabucco.framework.support.scripting.facade.service.compiler.ScriptCompilationService;
import org.nabucco.framework.support.scripting.facade.service.maintain.ScriptMaintainService;
import org.nabucco.framework.support.scripting.facade.service.produce.ScriptProduceService;
import org.nabucco.framework.support.scripting.facade.service.resolve.ScriptResolveService;
import org.nabucco.framework.support.scripting.facade.service.search.ScriptSearchService;

/**
 * ScriptingComponent<p/>Scripting component for NABUCCO<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public interface ScriptingComponent extends Component {

    final String COMPONENT_NAME = "org.nabucco.framework.support.scripting";

    final String COMPONENT_PREFIX = "scri";

    final String JNDI_NAME = ((((JNDI_PREFIX + "/") + COMPONENT_NAME) + "/") + "org.nabucco.framework.support.scripting.facade.component.ScriptingComponent");

    /**
     * Getter for the ScriptService.
     *
     * @return the ScriptService.
     * @throws ServiceException
     */
    ScriptService getScriptService() throws ServiceException;

    /**
     * Getter for the ScriptSearchService.
     *
     * @return the ScriptSearchService.
     * @throws ServiceException
     */
    ScriptSearchService getScriptSearchService() throws ServiceException;

    /**
     * Getter for the ScriptMaintainService.
     *
     * @return the ScriptMaintainService.
     * @throws ServiceException
     */
    ScriptMaintainService getScriptMaintainService() throws ServiceException;

    /**
     * Getter for the ScriptProduceService.
     *
     * @return the ScriptProduceService.
     * @throws ServiceException
     */
    ScriptProduceService getScriptProduceService() throws ServiceException;

    /**
     * Getter for the ScriptResolveService.
     *
     * @return the ScriptResolveService.
     * @throws ServiceException
     */
    ScriptResolveService getScriptResolveService() throws ServiceException;

    /**
     * Getter for the ScriptCompilationService.
     *
     * @return the ScriptCompilationService.
     * @throws ServiceException
     */
    ScriptCompilationService getScriptCompilationService() throws ServiceException;
}
