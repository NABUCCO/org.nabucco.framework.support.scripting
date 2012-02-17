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
package org.nabucco.framework.support.scripting.ui.rcp.list.script;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoAbstractListLayouter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoDefaultListContentProvider;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoDefaultTableSorter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableColumnInfo;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableParameter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableViewer;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.ui.rcp.list.script.model.ScriptListViewModel;
import org.nabucco.framework.support.scripting.ui.rcp.list.script.view.ScriptListView;
import org.nabucco.framework.support.scripting.ui.rcp.list.script.view.ScriptListViewTableFilter;
import org.nabucco.framework.support.scripting.ui.rcp.list.script.view.ScriptListViewWidgetFactory;
import org.nabucco.framework.support.scripting.ui.rcp.list.script.view.comparator.ScriptListViewScriptDescriptionComparator;
import org.nabucco.framework.support.scripting.ui.rcp.list.script.view.comparator.ScriptListViewScriptNameComparator;
import org.nabucco.framework.support.scripting.ui.rcp.list.script.view.label.ScriptListViewScriptDescriptionLabelProvider;
import org.nabucco.framework.support.scripting.ui.rcp.list.script.view.label.ScriptListViewScriptNameLabelProvider;
import org.nabucco.framework.support.scripting.ui.rcp.list.script.view.label.ScriptListViewScriptOwnerLabelProvider;
import org.nabucco.framework.support.scripting.ui.rcp.list.script.view.label.ScriptListViewScriptTypeLabelProvider;

/**
 * ScriptListViewLayouter
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ScriptListViewLayouter extends NabuccoAbstractListLayouter<ScriptListViewModel> {

    private static final String COLUMN_NAME_TITLE = ScriptListView.ID + ".column.name.title";

    private static final String COLUMN_NAME_TOOLTIP = ScriptListView.ID + ".column.name.tooltip";

    private static final String COLUMN_DESCRIPTION_TITLE = ScriptListView.ID
            + ".column.description.title";

    private static final String COLUMN_DESCRIPTION_TOOLTIP = ScriptListView.ID
            + ".column.description.tooltip";

    private static final String COLUMN_OWNER_TITLE = ScriptListView.ID + ".column.owner.title";

    private static final String COLUMN_OWNER_TOOLTIP = ScriptListView.ID + ".column.owner.tooltip";

    private static final String COLUMN_TYPE_TITLE = ScriptListView.ID + ".column.type.title";

    private static final String COLUMN_TYPE_TOOLTIP = ScriptListView.ID + ".column.type.tooltip";

    @Override
    public NabuccoTableViewer layout(Composite parent, NabuccoMessageManager messageManager,
            ScriptListViewModel model, Layoutable<ScriptListViewModel> view) {

        NabuccoFormToolkit ntk = new NabuccoFormToolkit(parent);
        ScriptListViewWidgetFactory widgetFactory = new ScriptListViewWidgetFactory(ntk);

        NabuccoTableParameter parameter = new NabuccoTableParameter(
                new NabuccoDefaultTableSorter<Script>(createTableComparators()),
                new ScriptListViewTableFilter(), new NabuccoDefaultListContentProvider(model),
                createTableColumnInfo(), getDoubleClickCommand(view));
        return widgetFactory.createTable(parent, parameter, model);
    }

    private List<Comparator<Script>> createTableComparators() {
        List<Comparator<Script>> result = new LinkedList<Comparator<Script>>();
        result.add(new ScriptListViewScriptNameComparator());
        result.add(new ScriptListViewScriptDescriptionComparator());
        return result;
    }

    /**
     * Creates the table columns.
     * 
     * @return table columns
     */
    private NabuccoTableColumnInfo[] createTableColumnInfo() {
        return new NabuccoTableColumnInfo[] {

                new NabuccoTableColumnInfo(COLUMN_NAME_TITLE, COLUMN_NAME_TOOLTIP, 200, SWT.CENTER,
                        SWT.CENTER, new ScriptListViewScriptNameLabelProvider()),

                new NabuccoTableColumnInfo(COLUMN_TYPE_TITLE, COLUMN_TYPE_TOOLTIP, 100, SWT.RIGHT,
                        SWT.RIGHT, new ScriptListViewScriptTypeLabelProvider()),

                new NabuccoTableColumnInfo(COLUMN_OWNER_TITLE, COLUMN_OWNER_TOOLTIP, 100,
                        SWT.RIGHT, SWT.RIGHT, new ScriptListViewScriptOwnerLabelProvider()),

                new NabuccoTableColumnInfo(COLUMN_DESCRIPTION_TITLE, COLUMN_DESCRIPTION_TOOLTIP,
                        300, SWT.RIGHT, SWT.RIGHT,
                        new ScriptListViewScriptDescriptionLabelProvider()) };
    }
}
