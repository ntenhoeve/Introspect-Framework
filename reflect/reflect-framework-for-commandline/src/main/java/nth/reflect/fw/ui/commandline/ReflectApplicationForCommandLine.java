package nth.reflect.fw.ui.commandline;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer2service.ServiceObjectActionMethod;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandler;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandlerProvider;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.impl.ProcessDirectly;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandlerProvider;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.DefaultNotificationProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.DefaultReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.validation.ValidationProvider;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterFactories;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.url.ReflectUrlStreamHandler;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.layer5provider.url.UrlStreamHandlers;
import nth.reflect.fw.layer5provider.validation.DefaultValidationProvider;
import nth.reflect.fw.layer5provider.version.DefaultVersionProvider;
import nth.reflect.fw.layer5provider.version.VersionProvider;
import nth.reflect.fw.ui.commandline.layer5provider.actionmethod.execution.ActionMethodExecutionProvider;
import nth.reflect.fw.ui.commandline.layer5provider.actionmethod.result.ActionMethodResultHandlerClasses;

/**
 * <p>
 * The {@link ReflectApplication} is an implementation of the
 * {@link ReflectFramework} for
 * <a href="https://en.wikipedia.org/wiki/Command-line_interface">command line
 * interfaces</a>.<br>
 * <br>
 * If you execute the application with parameters it will:
 * <ul>
 * <li>parse the given command line</li>
 * <li>call one of the {@link ServiceObjectActionMethod}'s with an optional
 * parameter</li>
 * <li>Return the result of the method or return a message if a method was
 * successfully executed or not</li>
 * <li>The {@link ReflectApplicationForCommandLine} is than terminated (state is
 * lost unless the application stores state somehow)</li>
 * </ul>
 * If you execute the application without parameters it will:
 * <ul>
 * <li>Return a list of possible commands and arguments</li>
 * <li>The {@link ReflectApplicationForCommandLine} is than terminated</li>
 * </ul>
 * </p>
 * 
 * <h3>How to download a ReflectForCommandLine demo project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * <h3>How to create a new ReflectCommandLine project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * @author nilsth
 *
 */
public abstract class ReflectApplicationForCommandLine implements ReflectApplication {

	private String[] commandLineArguments;

	@Override
	public Class<? extends UserInterfaceController> getUserInterfaceControllerClass() {
		return UserInterfaceControllerForCommandLine.class;
	}

	@Override
	public Class<? extends ReflectionProvider> getReflectionProviderClass() {
		return DefaultReflectionProvider.class;
	}

	@Override
	public Class<? extends VersionProvider> getVersionProviderClass() {
		return DefaultVersionProvider.class;
	}

	@Override
	public Class<? extends LanguageProvider> getLanguageProviderClass() {
		return DefaultLanguageProvider.class;
	}

	@Override
	public Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
		return DefaultAuthorizationProvider.class;
	}

	@Override
	public Class<? extends ValidationProvider> getValidationProviderClass() {
		return DefaultValidationProvider.class;
	}

	@Override
	public Class<? extends NotificationProvider> getNotificationProviderClass() {
		return DefaultNotificationProvider.class;
	}

	@Override
	public Class<? extends UrlProvider> getUrlProviderClass() {
		return UrlProvider.class;
	}

	@Override
	public List<Class<? extends ReflectUrlStreamHandler>> getUrlStreamHandlerClasses() {
		return new UrlStreamHandlers();
	}

	@Override
	public Class<? extends StringConverterProvider> getStringConverterProviderClass() {
		return StringConverterProvider.class;
	}

	@Override
	public List<Class<? extends StringConverterFactory>> getStringConverterFactoryClasses() {
		return new StringConverterFactories();
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodExecutionProvider> getActionMethodExecutionProviderClass() {
		return ActionMethodExecutionProvider.class;
	}

	@Override
	public Class<? extends ActionMethodPreHandlerProvider> getActionMethodPreHandlerProviderClass() {
		return ActionMethodPreHandlerProvider.class;
	}

	/**
	 * {@link ReflectApplicationForCommandLine} only supports
	 * {@link ProcessDirectly}
	 */
	@Override
	public List<Class<? extends ActionMethodPreHandler>> getActionMethodPreHandlerClasses() {
		return Arrays.asList(ProcessDirectly.class);
	}

	@Override
	public Class<? extends ActionMethodResultHandlerProvider> getActionMethodResultHandlerProviderClass() {
		return ActionMethodResultHandlerProvider.class;
	}

	@Override
	public List<Class<? extends ActionMethodResultHandler>> getActionMethodResultHandlerClasses() {
		return new ActionMethodResultHandlerClasses();
	}

	public String[] getCommandLineArguments() {
		return commandLineArguments;
	}

	/**
	 * Launch a stand-alone application. This method is typically called from the
	 * main method(). It must not be called more than once or an exception will be
	 * thrown. This is equivalent to launch(TheClass.class, args) where TheClass is
	 * the immediately enclosing class of the method that called launch. It must be
	 * a subclass of Application or a RuntimeException will be thrown.
	 *
	 * Typical usage is:
	 * <ul>
	 * 
	 * <pre>
	 * public static void main(String[] args) {
	 * 	launch(args);
	 * }
	 * </pre>
	 * </ul>
	 *
	 * @param args the command line arguments passed to the application. An
	 *             application may get these parameters using the
	 *             {@link #getParameters()} method.
	 *
	 * @throws IllegalStateException if this method is called more than once.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void launch(String... args) {
		// Figure out the right class to call
		StackTraceElement[] cause = Thread.currentThread().getStackTrace();

		boolean foundThisMethod = false;
		String callingClassName = null;
		for (StackTraceElement se : cause) {
			// Skip entries until we get to the entry for this class
			String className = se.getClassName();
			String methodName = se.getMethodName();
			if (foundThisMethod) {
				callingClassName = className;
				break;
			} else if (ReflectApplicationForCommandLine.class.getName().equals(className)
					&& "launch".equals(methodName)) {

				foundThisMethod = true;
			}
		}

		if (callingClassName == null) {
			throw new NoApplicationClassException();
		}

		try {
			Class applicationClass = Class
					.forName(callingClassName, true, Thread.currentThread().getContextClassLoader());
			if (ReflectApplicationForCommandLine.class.isAssignableFrom(applicationClass)) {
				Class<? extends ReflectApplicationForCommandLine> appClass = applicationClass;
				Constructor<? extends ReflectApplicationForCommandLine> constructor = (Constructor<? extends ReflectApplicationForCommandLine>) appClass
						.getConstructors()[0];
				ReflectApplicationForCommandLine app = constructor.newInstance();
				app.setCommandlineArguments(args);
				ReflectFramework.launch(app);
			} else {
				throw new NoApplicationSubClassException(applicationClass);
			}
		} catch (RuntimeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private void setCommandlineArguments(String[] commandLineArguments) {
		this.commandLineArguments = commandLineArguments;
	}

}
