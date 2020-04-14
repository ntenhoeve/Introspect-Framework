package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;

import org.junit.Before;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.FullFeatureDomainObject;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public abstract class FieldFactoryTest {

	private DependencyInjectionContainer container;

	@Before
	public void setUp() throws Exception {
		createProviderContainer();
	}

	private void createProviderContainer() {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		container = application.createContainer();
	}

	public PropertyFieldFactoryInfo createPropertyFieldFactoryInfo(String getterMethodName) {
		PropertyInfo propertyInfo = createPropertyInfo(getterMethodName);
		PropertyValueModel propertyValueModel = new PropertyValueModel(null, propertyInfo, null);
		FormTab formTab = createFormTab();
		PropertyFieldFactoryInfo propertyFieldFactoryInfo = new PropertyFieldFactoryInfo(formTab, propertyValueModel);
		return propertyFieldFactoryInfo;
	}

	private FormTab createFormTab() {
		FormTab formTab = new FormTab() {

			@Override
			public void onRefresh() {
			}

			@Override
			public String getDisplayName() {
				return null;
			}

			@Override
			public String getDescription() {
				return null;
			}

			@Override
			public Object getMethodParameter() {
				return null;
			}

			@Override
			public Object getMethodOwner() {
				return null;
			}

			@Override
			public ActionMethodInfo getMethodInfo() {
				return null;
			}

			@Override
			public UserInterfaceContainer getUserInterfaceContainer() {
				return container.get(UserInterfaceContainer.class);
			}

			@Override
			public FormMode getFormMode() {
				return null;
			}

			@Override
			public ReadOnlyValueModel getDomainValueModel() {
				return null;
			}

			@Override
			public Object getDomainObject() {
				return null;
			}
		};
		return formTab;
	}

	public PropertyInfo createPropertyInfo(String getterMethodName) {
		Method getterMethod;
		try {
			getterMethod = getMethod(getterMethodName);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		ProviderContainer providerContainer = container.get(ProviderContainer.class);
		PropertyInfo propertyInfo = new PropertyInfo(providerContainer, DomainObject.class, getterMethod);
		return propertyInfo;
	}

	private Method getMethod(String getterMethodName) throws NoSuchMethodException {
		Method getterMethod = FullFeatureDomainObject.class.getMethod(getterMethodName);
		return getterMethod;
	}

	public void assertCanCreate(PropertyFieldFactory fieldFactory, String getterMethodName,
			boolean expectedReturnValue) {
		PropertyFieldFactoryInfo info = createPropertyFieldFactoryInfo(getterMethodName);
		boolean result = fieldFactory.canCreate(info);
		assertThat(result)
				.describedAs("%s.canCreate(PropertyFieldFactoryInfo for %s.%s) resulted in a incorrect return value",
						DateTimeFieldFactory.class.getSimpleName(), DomainObject.class.getSimpleName(),
						getterMethodName)
				.isEqualTo(expectedReturnValue);
	}

}
