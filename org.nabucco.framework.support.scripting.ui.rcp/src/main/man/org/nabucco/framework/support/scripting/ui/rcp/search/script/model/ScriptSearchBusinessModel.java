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
package org.nabucco.framework.support.scripting.ui.rcp.search.script.model;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchModel;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptType;
import org.nabucco.framework.support.scripting.facade.message.search.ScriptSearchRq;
import org.nabucco.framework.support.scripting.ui.rcp.browser.script.ScriptListViewBrowserElement;
import org.nabucco.framework.support.scripting.ui.rcp.communication.ScriptingComponentServiceDelegateFactory;
import org.nabucco.framework.support.scripting.ui.rcp.communication.search.ScriptSearchServiceDelegate;

/**
 * ScriptSearchBusinessModel
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ScriptSearchBusinessModel implements NabuccoComponentSearchModel {

    public static String ID = "org.nabucco.framework.support.scripting.ui.rcp.search.script.model.ScriptSearchBusinessModel";

    @Override
    public ScriptListViewBrowserElement search(NabuccoComponentSearchParameter searchParameter) {

        if (!(searchParameter instanceof ScriptSearchViewModel)) {
            return null;
        }

        ScriptSearchViewModel searchModel = (ScriptSearchViewModel) searchParameter;

        List<Script> resultList = this.search(searchModel);

        if (resultList.size() <= 0) {
            return null;
        }

        return new ScriptListViewBrowserElement(resultList);
    }

    /**
     * Execute the search.
     * 
     * @param model
     *            the search model holding the parameters
     * 
     * @return the list of found scripts
     */
    private List<Script> search(ScriptSearchViewModel model) {

        try {
            ScriptSearchRq msg = createSearchMsg(model);

            ScriptSearchServiceDelegate searchService = ScriptingComponentServiceDelegateFactory
                    .getInstance().getScriptSearchService();

            return searchService.searchScripts(msg).getScriptList();

        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }

        return null;
    }

    /**
     * Create the search message.
     * 
     * @param model
     *            the search view model
     * 
     * @return the created message
     */
    private ScriptSearchRq createSearchMsg(ScriptSearchViewModel model) {
        ScriptSearchRq msg = new ScriptSearchRq();
        msg.setName(this.getNameFromModel(model));
        msg.setType(this.getScriptTypeFromModel(model));
        msg.setOwner(this.getOwnerFromModel(model));
        return msg;
    }

    /**
     * Resolve the script name from search model.
     * 
     * @param model
     *            the search model
     * 
     * @return the name
     */
    private Name getNameFromModel(ScriptSearchViewModel model) {
        String name = model.getScriptName();
        if (name == null || name.isEmpty()) {
            return null;
        }
        return new Name(name);
    }

    /**
     * Resolve the script owner from search model.
     * 
     * @param model
     *            the search model
     * 
     * @return the owner
     */
    private Owner getOwnerFromModel(ScriptSearchViewModel model) {
        String owner = model.getScriptOwner();
        if (owner == null || owner.isEmpty()) {
            return null;
        }
        return new Owner(owner);
    }

    /**
     * Resolve the script type from search model.
     * 
     * @param model
     *            the search model
     * 
     * @return the type
     */
    private ScriptType getScriptTypeFromModel(ScriptSearchViewModel model) {
        String codeType = model.getScriptType();
        if (codeType == null || codeType.isEmpty()) {
            return null;
        }
        return ScriptType.valueOf(codeType.toUpperCase());
    }

}
