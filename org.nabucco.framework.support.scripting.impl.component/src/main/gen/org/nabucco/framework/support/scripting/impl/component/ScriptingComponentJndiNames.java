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
package org.nabucco.framework.support.scripting.impl.component;

/**
 * ScriptingComponentJndiNames<p/>Scripting component for NABUCCO<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public interface ScriptingComponentJndiNames {

    final String COMPONENT_RELATION_SERVICE_LOCAL = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.component.ComponentRelationService/local";

    final String COMPONENT_RELATION_SERVICE_REMOTE = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.component.ComponentRelationService/remote";

    final String QUERY_FILTER_SERVICE_LOCAL = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.component.QueryFilterService/local";

    final String QUERY_FILTER_SERVICE_REMOTE = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.component.QueryFilterService/remote";

    final String SCRIPT_SERVICE_LOCAL = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.service.ScriptService/local";

    final String SCRIPT_SERVICE_REMOTE = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.service.ScriptService/remote";

    final String SCRIPT_SEARCH_SERVICE_LOCAL = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.service.search.ScriptSearchService/local";

    final String SCRIPT_SEARCH_SERVICE_REMOTE = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.service.search.ScriptSearchService/remote";

    final String SCRIPT_MAINTAIN_SERVICE_LOCAL = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.service.maintain.ScriptMaintainService/local";

    final String SCRIPT_MAINTAIN_SERVICE_REMOTE = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.service.maintain.ScriptMaintainService/remote";

    final String SCRIPT_PRODUCE_SERVICE_LOCAL = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.service.produce.ScriptProduceService/local";

    final String SCRIPT_PRODUCE_SERVICE_REMOTE = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.service.produce.ScriptProduceService/remote";

    final String SCRIPT_RESOLVE_SERVICE_LOCAL = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.service.resolve.ScriptResolveService/local";

    final String SCRIPT_RESOLVE_SERVICE_REMOTE = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.service.resolve.ScriptResolveService/remote";

    final String SCRIPT_COMPILATION_SERVICE_LOCAL = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.service.compiler.ScriptCompilationService/local";

    final String SCRIPT_COMPILATION_SERVICE_REMOTE = "nabucco/org.nabucco.framework.support.scripting/org.nabucco.framework.support.scripting.facade.service.compiler.ScriptCompilationService/remote";
}
