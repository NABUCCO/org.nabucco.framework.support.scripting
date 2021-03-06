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

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.component.locator.ComponentLocator;
import org.nabucco.framework.base.facade.component.locator.ComponentLocatorSupport;

/**
 * Locator for ScriptingComponent.
 *
 * @author NABUCCO Generator, PRODYNA AG
 */
public class ScriptingComponentLocator extends ComponentLocatorSupport<ScriptingComponent> implements
        ComponentLocator<ScriptingComponent> {

    private static ScriptingComponentLocator instance;

    /**
     * Constructs a new ScriptingComponentLocator instance.
     *
     * @param component the Class<ScriptingComponent>.
     * @param jndiName the String.
     */
    private ScriptingComponentLocator(String jndiName, Class<ScriptingComponent> component) {
        super(jndiName, component);
    }

    @Override
    public ScriptingComponent getComponent() throws ConnectionException {
        ScriptingComponent component = super.getComponent();
        if ((component instanceof ScriptingComponentLocal)) {
            return new ScriptingComponentLocalProxy(((ScriptingComponentLocal) component));
        }
        return component;
    }

    /**
     * Getter for the Instance.
     *
     * @return the ScriptingComponentLocator.
     */
    public static ScriptingComponentLocator getInstance() {
        if ((instance == null)) {
            instance = new ScriptingComponentLocator(ScriptingComponent.JNDI_NAME, ScriptingComponent.class);
        }
        return instance;
    }
}
