package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import nth.reflect.fw.gui.component.tab.form.DateTimeMode;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyFieldFactory;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.FieldModeType;

public class DateTimeFieldFactory implements PropertyFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		return new DateTimeField(propertyValueModel, DateTimeMode.DATE_AND_TIME);
	}

	@Override
	public boolean canCreateFor(PropertyValueModel propertyValueModel) {
		return propertyValueModel.getPropertyInfo().getFieldMode() == FieldModeType.DATE_TIME;
	}

}
