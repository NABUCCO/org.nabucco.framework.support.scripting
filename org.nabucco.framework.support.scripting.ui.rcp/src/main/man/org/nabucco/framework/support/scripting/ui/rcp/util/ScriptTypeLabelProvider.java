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
package org.nabucco.framework.support.scripting.ui.rcp.util;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;

import org.nabucco.framework.plugin.base.layout.I18NLabelProviderContributor;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptType;

/**
 * ScriptTypeLabelProvider
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ScriptTypeLabelProvider implements I18NLabelProviderContributor {

    private static final String ID = ScriptType.class.getCanonicalName() + ".";

    @Override
    public String getImage(Object arg0) {
        return null;
    }

    @Override
    public Entry<String, Map<String, ? extends Serializable>> getText(Object object) {

        if (object instanceof ScriptType) {
            String name = ID + ((ScriptType) object).name();
            return new AbstractMap.SimpleEntry<String, Map<String, ? extends Serializable>>(name,
                    null);
        }

        return null;
    }
}
