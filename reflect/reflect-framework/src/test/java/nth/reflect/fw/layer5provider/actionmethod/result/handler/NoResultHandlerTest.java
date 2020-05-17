package nth.reflect.fw.layer5provider.actionmethod.result.handler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.junit.UserInterfaceControllerForJUnit;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethod.result.handler.NoResultHandler;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class NoResultHandlerTest {

	private static final Class<ResultHandlerSerice> SERVICE_CLASS = ResultHandlerSerice.class;
	private UserInterfaceContainer container;
	private NoResultHandler resultHandler;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().addServiceClass(SERVICE_CLASS).createContainer();
		resultHandler = new NoResultHandler();
	}

	@Test
	public void testCanProcess_givenMethodWithoutReturnValue_isTrue() {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		ProviderContainer providerContainer = container.get(ProviderContainer.class);
		ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(SERVICE_CLASS);
		ActionMethodInfo actionMethodInfo = serviceClassInfo.getActionMethodInfo(ResultHandlerSerice.NO_RETURN_VALUE);
		boolean actual = resultHandler.canProcess(providerContainer, actionMethodInfo);
		assertThat(actual).isTrue();
	}

	@Test
	public void testCanProcess_givenMethodWithReturnValue_isFalse() {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		ProviderContainer providerContainer = container.get(ProviderContainer.class);
		ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(SERVICE_CLASS);
		ActionMethodInfo actionMethodInfo = serviceClassInfo
				.getActionMethodInfo(ResultHandlerSerice.DOMAIN_OBJECT_RETURN_VALUE);
		boolean actual = resultHandler.canProcess(providerContainer, actionMethodInfo);
		assertThat(actual).isFalse();
	}

	@Test
	public void testExecutesActionMethod_givenNoResultHandler_showsMessage() {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		ResultHandlerSerice serviceObject = container.get(SERVICE_CLASS);
		ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(SERVICE_CLASS);
		ActionMethodInfo actionMethodInfo = serviceClassInfo.getActionMethodInfo(ResultHandlerSerice.NO_RETURN_VALUE);
		Object methodParameter = null;
		Object methodResult = null;
		resultHandler.process(container, serviceObject, actionMethodInfo, methodParameter, methodResult);
		UserInterfaceControllerForJUnit userInterface = container.get(UserInterfaceControllerForJUnit.class);
		assertThat(userInterface.getEventsAndClear()).contains("showMessage(No return value was successfully executed.)");

	}

}