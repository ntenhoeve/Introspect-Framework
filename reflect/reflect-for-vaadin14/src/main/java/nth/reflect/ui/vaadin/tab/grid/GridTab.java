package nth.reflect.ui.vaadin.tab.grid;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.function.ValueProvider;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.table.info.ColumnInfo;
import nth.reflect.fw.gui.component.table.info.TableInfo;
import nth.reflect.fw.gui.component.table.info.TableInfoForGridTab;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.translatable.Translatable;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.ui.vaadin.tab.Tab;

public class GridTab extends Tab implements nth.reflect.fw.gui.component.tab.grid.GridTab {

	private static final long serialVersionUID = -7981487179996807252L;
	@Translatable
	private static final String ERROR_INVOKING_ACTION_METHOD = "Error invoking action method: %s";
	@Translatable
	private static final String ERROR_INVOKING_ACTION_METHOD_THE_RETURN_TYPE_IS_NOT_SUPPORTED_FOR_A_TABLE_TAB = "Error invoking action method: %s The return type %s is not supported for a GridTab.";
	private final UserInterfaceContainer userInterfaceContainer;
	private final Object actionMethodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;
	private final Grid<?> grid;
	private final TableInfo tableInfo;
	private ReadOnlyValueModel selectedRowsModel;

	public GridTab(UserInterfaceContainer userInterfaceContainer, Object actionMethodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.actionMethodOwner = actionMethodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;
		tableInfo = new TableInfoForGridTab(this);

		setSizeFull();
		grid = createGrid();
		grid.setSizeFull();
		add(grid);

	}


	private Grid<Object> createGrid() {
		Grid<Object> grid = new Grid<Object>();
		grid.setDataProvider(createDataProvider());
		addGridColumns(grid);
		return grid;
	}

	@SuppressWarnings("unchecked")
	private void addGridColumns(Grid<?> grid) {
		List<ColumnInfo> columnInfos = tableInfo.getColumnInfos();
		for (ColumnInfo columnInfo : columnInfos) {
			ValueProvider valueProvider = createValueProvider(columnInfo);
			grid.addColumn(valueProvider).setHeader(columnInfo.getHeaderText());
		}
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private ValueProvider createValueProvider(ColumnInfo columnInfo) {
		return new ValueProvider<Object, Object>() {
			@Override
			public Object apply(Object value) {
				return columnInfo.getCellValue(value);
			}
		};
	}

	//
	private DataProvider<Object, ?> createDataProvider() {
		List<Object> values = tableInfo.getValueList();
		// TODO override DataProvider or one of its sub classes so that it
		// refreshes when needed
		return new ListDataProvider<>(values);
	}


	@Override
	public String getDisplayName() {
		return actionMethodInfo.getDisplayName();
	}

	@Override
	public String getDescription() {
		return actionMethodInfo.getDescription();
	}

	@Override
	public void onRefresh() {
		grid.getDataProvider().refreshAll();
	}


	@Override
	public Object getMethodOwner() {
		return actionMethodOwner;
	}


	@Override
	public ActionMethodInfo getMethodInfo() {
		return actionMethodInfo;
	}


	@Override
	public Object getMethodParameter() {
		return methodParameterValue;
	}


	@Override
	public ReadOnlyValueModel getSelectedRowModel() {
		if (selectedRowsModel == null) {
			selectedRowsModel = new ReadOnlyValueModel() {

				@Override
				public Object getValue() {
					return grid.getSelectionModel().getFirstSelectedItem().get();
				}

				@Override
				public Class<?> getValueType() {
					return actionMethodInfo.getReturnTypeInfo().getType();
				}

				@Override
				public boolean canGetValue() {
					return !grid.getSelectionModel().getFirstSelectedItem().isPresent();
				}

			};
		}
		return selectedRowsModel;
	}



	@Override
	public UserInterfaceContainer getUserInterfaceContainer() {
		return userInterfaceContainer;
	}

}
