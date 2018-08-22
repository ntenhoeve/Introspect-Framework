package nth.reflect.fw.javafx.control.view.form.field;

import javafx.scene.layout.Region;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.FieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.form.field.DateTimeMode;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormView;

public class FieldFactory {

	public static Region create(FormView formView, ReflectionProvider reflectionProvider,  PropertyValueModel propertyValueModel) {
		// TODO how do we make sure that all fields implement refreshable?
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		FieldModeType fieldMode = propertyInfo.getFieldMode();
		switch (fieldMode) {
		case TEXT:
			return new RfxTextField(propertyValueModel);
		case PASSWORD:
			return new RfxPasswordField(propertyValueModel);
		case TEXT_AREA:
			return new RfxTextAreaField(propertyValueModel);
		case CHECK_BOX:
			return new RfxCheckBox(propertyValueModel);
		case NUMBER:
			return new RfxNumericField(propertyValueModel);
		case CHAR:
			return new RfxTextField(propertyValueModel);
		case TIME:
			return new RfxDateTimeField(propertyValueModel, DateTimeMode.TIME);
		case DATE:
			return new RfxDateTimeField(propertyValueModel, DateTimeMode.DATE);
		case DATE_TIME:
			return new RfxDateTimeField(propertyValueModel, DateTimeMode.DATE_AND_TIME);
		case COMBO_BOX:
			LanguageProvider languageProvider=formView.getUserInterfaceContainer().get(LanguageProvider.class);
			return new RfxComboBox(propertyValueModel, reflectionProvider, languageProvider);
		case ONE_TO_ONE_OR_MANY:
			return new RfxOneToOneOrManyField(formView,  propertyValueModel);
		case MANY_TO_ONE_OR_MANY:
			return new RfxManyToOneOrManyField(formView, propertyValueModel);
		default:
			throw new RuntimeException("Field mode " + fieldMode.name() + " not supported for property " + propertyInfo.getCanonicalName());
		}

	}

}