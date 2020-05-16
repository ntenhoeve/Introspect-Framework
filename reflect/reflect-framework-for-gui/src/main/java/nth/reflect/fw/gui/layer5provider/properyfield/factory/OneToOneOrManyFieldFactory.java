package nth.reflect.fw.gui.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public abstract class OneToOneOrManyFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(FormTab formTab, PropertyValueModel propertyValueModel) {
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		boolean isDomainClass = propertyInfo.getTypeInfo().isDomainClass();
		return isDomainClass;
	}

}