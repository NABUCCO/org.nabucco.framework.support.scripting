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
package org.nabucco.framework.support.scripting.impl.service.engine.javascript.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.mozilla.javascript.ScriptableObject;

/**
 * LocatorUtility
 * 
 * @author Lasse Asbach, PRODYNA AG
 */
public class LocatorUtility extends ScriptableObject {

    private static final long serialVersionUID = 1L;

    private static final String NAME = "LocatorUtility";

    @Override
    public String getClassName() {
        return NAME;
    }

    /**
     * Utility method exposed to Javascript:
     * 
     * Performs a lookup for a given url.
     * 
     * @param url
     *            the URL to lookup
     * @return the found object
     * 
     * @throws NamingException
     *             when the
     */
    public Object lookup(String url) throws NamingException {
        InitialContext context = new InitialContext();
        Object object = context.lookup(url);
        return object;
    }

}
