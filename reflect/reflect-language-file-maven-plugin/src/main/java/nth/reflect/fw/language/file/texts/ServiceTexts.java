package nth.reflect.fw.language.file.texts;

import java.util.List;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer2service.ServiceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class ServiceTexts extends Texts {

	private static final long serialVersionUID = -1810508085164204734L;

	public ServiceTexts(DependencyInjectionContainer container) {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		ServiceContainer serviceContainer = container.get(ServiceContainer.class);
		List<Class<?>> serviceClasses = serviceContainer.getAllClasses();
		for (Class<?> serviceClass : serviceClasses) {
			ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(serviceClass);

			put(serviceClassInfo);

			List<ActionMethodInfo> actionMethodInfos = serviceClassInfo.getActionMethodInfosSorted();
			for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
				put(actionMethodInfo);
			}

			putPropertiesFromTranslatableStringsFromStaticFields(serviceClass);
		}
	}

}
