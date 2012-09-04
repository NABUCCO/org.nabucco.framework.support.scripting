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
package org.nabucco.framework.support.scripting.ui.web.communication;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateFactorySupport;
import org.nabucco.framework.support.scripting.facade.component.ScriptingComponent;
import org.nabucco.framework.support.scripting.facade.component.ScriptingComponentLocator;
import org.nabucco.framework.support.scripting.ui.web.communication.ScriptServiceDelegate;
import org.nabucco.framework.support.scripting.ui.web.communication.compiler.ScriptCompilationServiceDelegate;
import org.nabucco.framework.support.scripting.ui.web.communication.maintain.ScriptMaintainServiceDelegate;
import org.nabucco.framework.support.scripting.ui.web.communication.produce.ScriptProduceServiceDelegate;
import org.nabucco.framework.support.scripting.ui.web.communication.resolve.ScriptResolveServiceDelegate;
import org.nabucco.framework.support.scripting.ui.web.communication.search.ScriptSearchServiceDelegate;

/**
 * ServiceDelegateFactoryTemplate<p/>Scripting component for NABUCCO<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-03-30
 */
public class ScriptingComponentServiceDelegateFactory extends ServiceDelegateFactorySupport<ScriptingComponent> {

    private static ScriptingComponentServiceDelegateFactory instance = new ScriptingComponentServiceDelegateFactory();

    private ScriptServiceDelegate scriptServiceDelegate;

    private ScriptSearchServiceDelegate scriptSearchServiceDelegate;

    private ScriptMaintainServiceDelegate scriptMaintainServiceDelegate;

    private ScriptProduceServiceDelegate scriptProduceServiceDelegate;

    private ScriptResolveServiceDelegate scriptResolveServiceDelegate;

    private ScriptCompilationServiceDelegate scriptCompilationServiceDelegate;

    /** Constructs a new ScriptingComponentServiceDelegateFactory instance. */
    private ScriptingComponentServiceDelegateFactory() {
        super(ScriptingComponentLocator.getInstance());
    }

    /**
     * Getter for the ScriptService.
     *
     * @return the ScriptServiceDelegate.
     * @throws ClientException
     */
    public ScriptServiceDelegate getScriptService() throws ClientException {
        try {
            if ((this.scriptServiceDelegate == null)) {
                this.scriptServiceDelegate = new ScriptServiceDelegate(this.getComponent().getScriptService());
            }
            return this.scriptServiceDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ScriptService", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ScriptSearchService.
     *
     * @return the ScriptSearchServiceDelegate.
     * @throws ClientException
     */
    public ScriptSearchServiceDelegate getScriptSearchService() throws ClientException {
        try {
            if ((this.scriptSearchServiceDelegate == null)) {
                this.scriptSearchServiceDelegate = new ScriptSearchServiceDelegate(this.getComponent()
                        .getScriptSearchService());
            }
            return this.scriptSearchServiceDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ScriptSearchService", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ScriptMaintainService.
     *
     * @return the ScriptMaintainServiceDelegate.
     * @throws ClientException
     */
    public ScriptMaintainServiceDelegate getScriptMaintainService() throws ClientException {
        try {
            if ((this.scriptMaintainServiceDelegate == null)) {
                this.scriptMaintainServiceDelegate = new ScriptMaintainServiceDelegate(this.getComponent()
                        .getScriptMaintainService());
            }
            return this.scriptMaintainServiceDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ScriptMaintainService", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ScriptProduceService.
     *
     * @return the ScriptProduceServiceDelegate.
     * @throws ClientException
     */
    public ScriptProduceServiceDelegate getScriptProduceService() throws ClientException {
        try {
            if ((this.scriptProduceServiceDelegate == null)) {
                this.scriptProduceServiceDelegate = new ScriptProduceServiceDelegate(this.getComponent()
                        .getScriptProduceService());
            }
            return this.scriptProduceServiceDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ScriptProduceService", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ScriptResolveService.
     *
     * @return the ScriptResolveServiceDelegate.
     * @throws ClientException
     */
    public ScriptResolveServiceDelegate getScriptResolveService() throws ClientException {
        try {
            if ((this.scriptResolveServiceDelegate == null)) {
                this.scriptResolveServiceDelegate = new ScriptResolveServiceDelegate(this.getComponent()
                        .getScriptResolveService());
            }
            return this.scriptResolveServiceDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ScriptResolveService", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ScriptCompilationService.
     *
     * @return the ScriptCompilationServiceDelegate.
     * @throws ClientException
     */
    public ScriptCompilationServiceDelegate getScriptCompilationService() throws ClientException {
        try {
            if ((this.scriptCompilationServiceDelegate == null)) {
                this.scriptCompilationServiceDelegate = new ScriptCompilationServiceDelegate(this.getComponent()
                        .getScriptCompilationService());
            }
            return this.scriptCompilationServiceDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ScriptCompilationService", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the Instance.
     *
     * @return the ScriptingComponentServiceDelegateFactory.
     */
    public static ScriptingComponentServiceDelegateFactory getInstance() {
        return instance;
    }
}
