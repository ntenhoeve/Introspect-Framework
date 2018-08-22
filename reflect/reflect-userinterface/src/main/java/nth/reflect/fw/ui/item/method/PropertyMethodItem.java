package nth.reflect.fw.ui.item.method;

import nth.reflect.fw.generic.util.TitleUtil;
import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.GraphicalUserinterfaceController;
import nth.reflect.fw.ui.view.FormMode;
import nth.reflect.fw.ui.view.FormView;

public class PropertyMethodItem extends MethodItem {

	private PropertyInfo propertyInfo;
	private ReadOnlyValueModel propertyOwnerModel;
	private ActionMethodInfo propertyMethodInfo;
	private ReadOnlyValueModel parameterValueModel;
	private FormView formView;
	private boolean showPropertyName;

	public PropertyMethodItem(FormView formView, PropertyInfo propertyInfo,
			ActionMethodInfo propertyMethodInfo,
			ReadOnlyValueModel parameterValueModel, boolean showPropertyName) {
		super(formView.getUserInterfaceContainer(),  formView.getDomainValueModel().getValue(), propertyMethodInfo, parameterValueModel);
		this.formView = formView;
		this.showPropertyName = showPropertyName;
		this.propertyOwnerModel = formView.getDomainValueModel();
		this.propertyInfo = propertyInfo;
		this.propertyMethodInfo = propertyMethodInfo;
		this.parameterValueModel = parameterValueModel;
		setAction(createAction());
	}

	private Action createAction() {
		return new Action() {

			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				GraphicalUserinterfaceController<?> userInterfaceController = formView.getUserInterfaceContainer().get(GraphicalUserinterfaceController.class);
				Object propertyOwner = propertyOwnerModel.getValue();
				Object methodParameter = parameterValueModel.getValue();
				
 				userInterfaceController.getViewContainer().setSelectedView(formView);
				userInterfaceController.processActionMethod(propertyOwner, propertyMethodInfo, methodParameter);
			}
		};
	}

	
	@Override
	public String getText() {
		// text format: propertyName: propertyMethodName
		StringBuffer text = new StringBuffer();
		if (showPropertyName) {
			text.append(propertyInfo.getDisplayName());
			text.append(": ");
		}
		Object parameterValue = null;
		if (!propertyMethodInfo.hasParameterFactory() &&   propertyMethodInfo.hasParameter() ) {
			parameterValue = parameterValueModel.getValue();
		}
		ReflectionProvider reflectionProvider=formView.getUserInterfaceContainer().get(ReflectionProvider.class);
		text.append(TitleUtil.createTitle(reflectionProvider,propertyMethodInfo,
				parameterValue));
		return text.toString();
	}


	@Override
	public boolean isEnabled() {
		boolean methodIsEnabled = propertyMethodInfo.isEnabled(propertyOwnerModel.getValue());
		boolean hasNoParameter=!propertyMethodInfo.hasParameter();
		boolean hasParameterFactory = propertyMethodInfo.hasParameterFactory();
		boolean canGetParameterValue = parameterValueModel.canGetValue() ;//TODO check for type as well?
		return methodIsEnabled && ( hasNoParameter || hasParameterFactory || canGetParameterValue);
	}
	
	/**
	 * Hide this item when form is not in edit mode or if property method should be hidden
	 */
	@Override
	public boolean isVisible() {
		return FormMode.EDIT_MODE== formView.getFormMode() && propertyMethodInfo.isVisible(propertyOwnerModel.getValue());
	}
	

}