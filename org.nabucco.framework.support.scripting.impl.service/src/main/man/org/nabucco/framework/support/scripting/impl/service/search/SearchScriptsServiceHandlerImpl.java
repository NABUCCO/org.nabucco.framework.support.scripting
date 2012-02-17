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
package org.nabucco.framework.support.scripting.impl.service.search;

import java.util.List;

import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.message.ScriptListMsg;
import org.nabucco.framework.support.scripting.facade.message.search.ScriptSearchRq;

/**
 * SearchScriptsServiceHandlerImpl
 * 
 * @author Jens Wurm, PRODYNA AG
 */
public class SearchScriptsServiceHandlerImpl extends SearchScriptsServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    public ScriptListMsg searchScripts(ScriptSearchRq rq) throws SearchException {

        StringBuilder queryString = new StringBuilder();
        queryString.append("select s from Script s");
        queryString.append(" where (s.owner = :owner)");
        queryString.append(" and (s.name = :name or :name is null)");
        queryString.append(" and (s.type = :type or :type is null)");

        ScriptListMsg rs = new ScriptListMsg();

        try {
            NabuccoQuery<Script> query = super.getPersistenceManager().createQuery(queryString.toString());
            query.setParameter("name", rq.getName());
            query.setParameter("owner", rq.getOwner());
            query.setParameter("type", rq.getType());

            List<Script> resultList = query.getResultList();

            if (!resultList.isEmpty()) {

                // force loading of lists
                for (Script current : resultList) {
                    current.getInputData().size();
                    current.getOutputData().size();
                }

                rs.getScriptList().addAll(resultList);
            }

        } catch (PersistenceException pe) {
            throw new SearchException("Error searching script.", pe);
        }

        return rs;
    }
}
