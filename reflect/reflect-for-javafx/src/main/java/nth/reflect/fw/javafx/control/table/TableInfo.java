package nth.reflect.fw.javafx.control.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

@SuppressWarnings("restriction")
public abstract class TableInfo {

	public abstract Object getValues();

	public abstract Class<?> getValuesType();

	public abstract ReflectionProvider getReflectionProvider();

	public abstract LanguageProvider getLanguageProvider();

	public abstract Collection<Item> getRowMenuItems(Object selectedObject);

	public List<TableColumn<Object, ?>> getTableColumns() {
		Class<?> type = getValuesType();
		if (hasOnlyOneColumn(type)) {
			return createColumnForObject();
		} else {
			return createColumnsForObject();
		}
	}

	/**
	 * @return true :only one column with object title for now
	 */
	private boolean hasOnlyOneColumn(Class<?> type) {
		return true;
		// return TypeInfo.isJavaVariableType(type) || type.isEnum();
	}

	private List<TableColumn<Object, ?>> createColumnForObject() {
		Class<?> itemType = getValuesType();
		ReflectionProvider reflectionProvider = getReflectionProvider();
		ClassInfo classInfo = reflectionProvider.getClassInfo(itemType);
		TableColumn<Object, ?> tableColumn = new TableColumn<Object, Object>();
		tableColumn.setMinWidth(100);
		tableColumn.setCellValueFactory(new CellValueFactory<>(classInfo));
		List<TableColumn<Object, ?>> tableColumns = new ArrayList<>();
		tableColumns.add(tableColumn);
		return tableColumns;
	}

	private List<TableColumn<Object, ?>> createColumnsForObject() {
		Class<?> itemType = getValuesType();
		ReflectionProvider reflectionProvider = getReflectionProvider();
		ClassInfo classInfo = reflectionProvider.getClassInfo(itemType);
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSortedAndVisibleInTable();
		List<TableColumn<Object, ?>> tableColumns = new ArrayList<>();
		for (PropertyInfo propertyInfo : propertyInfos) {
			TableColumn<Object, ?> tableColumn = new TableColumn<Object, Object>(propertyInfo.getDisplayName());
			tableColumn.setMinWidth(100);
			tableColumn.setCellValueFactory(new PropertyValueFactory<>(propertyInfo.getSimpleName()));
			tableColumns.add(tableColumn);
		}
		return tableColumns;
	}

	@SuppressWarnings("rawtypes")
	public ObservableList<Object> getObservableList() {
		Object values = getValues();
		if (values == null) {
			List<Object> emptyList = new ArrayList<>();
			return new ObservableListWrapper<Object>(emptyList);
		} else if (values instanceof List) {
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) values;
			return new ObservableListWrapper<Object>(list);
		} else if (values instanceof Collection) {
			@SuppressWarnings("unchecked")
			List<Object> list = new ArrayList<>((Collection) values);
			return new ObservableListWrapper<Object>(list);
		} else if (values.getClass().isArray()) {
			List<Object> list = Arrays.asList(values);
			return new ObservableListWrapper<Object>(list);
		} else {
			String message = getLanguageProvider().getText("Error getting table values. Unsupported return type.");
			throw new RuntimeException(message);
		}

	}

}
