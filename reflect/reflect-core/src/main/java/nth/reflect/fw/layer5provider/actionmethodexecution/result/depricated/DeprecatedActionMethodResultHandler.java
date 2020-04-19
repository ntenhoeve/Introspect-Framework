package nth.reflect.fw.layer5provider.actionmethodexecution.result.depricated;

import java.lang.reflect.Method;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

/**
 * @deprecated All {@link UserInterfaceController}.show... methods need to be
 *             replaced with {@link ActionMethodResultHandler}s. This
 *             {@link ActionMethodResultHandler} meanwhile handles these
 *             methods.
 */
@Deprecated
public class DeprecatedActionMethodResultHandler implements ActionMethodResultHandler {

	private final Class<? extends UserInterfaceController> controllerClass;

	public DeprecatedActionMethodResultHandler(ReflectApplication reflectApplication) {
		this.controllerClass = reflectApplication.getUserInterfaceControllerClass();
	}

	@Override
	public boolean canProcess(ActionMethodInfo actionMethodInfo) {
		Method actionMethod = actionMethodInfo.getMethod();
		TypeInfo typeInfo = actionMethodInfo.getReturnTypeInfo();
		try {
			DeprecatedShowMethodFactory.create(controllerClass, actionMethod, typeInfo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void process(UserInterfaceController userInterfaceController, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameter, Object methodResult) {
		Method actionMethod = actionMethodInfo.getMethod();
		TypeInfo typeInfo = actionMethodInfo.getReturnTypeInfo();
		Method showResultMethod = DeprecatedShowMethodFactory
				.create(userInterfaceController.getClass(), actionMethod, typeInfo);
		try {
			if (actionMethodInfo.hasReturnValue()) {
				showResultMethod
						.invoke(userInterfaceController, methodOwner, actionMethodInfo, methodParameter, methodResult);
			} else {
				showResultMethod.invoke(userInterfaceController, methodOwner, actionMethodInfo, methodParameter);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
