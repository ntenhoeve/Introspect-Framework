package nth.reflect.fw.gui.item.about;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodExecutionProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.version.VersionProvider;

public class AboutItem extends Item {
	private static final String ABOUT_METHOD = "about";
	private static final TranslatableString ABOUT_TEXT = new TranslatableString(
			AboutItem.class.getCanonicalName() + ".text", "About");
	protected static final Object NO_PARAMETER = null;

	public AboutItem(final UserInterfaceContainer container) {
		super(container.get(LanguageProvider.class));
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		VersionProvider versionProvider = container.get(VersionProvider.class);
		setText(ABOUT_TEXT);
		setDescription(ABOUT_TEXT);
		try {
			setIconURL(new URL(FontAwesomeUrl.INFO_CIRCLE));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		setAction(new Action() {

			@Override
			public void run() {
				MethodNameFilter methodFilter = new MethodNameFilter(ABOUT_METHOD.toLowerCase());
				DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(versionProvider.getClass());
				List<ActionMethodInfo> actionMethodInfos = domainClassInfo.getActionMethodInfos(methodFilter);
				ActionMethodExecutionProvider executionProvider = container.get(ActionMethodExecutionProvider.class);
				if (actionMethodInfos.size() == 1) {
					ActionMethodInfo methodInfo = actionMethodInfos.get(0);
					executionProvider.process(container, methodInfo, versionProvider, NO_PARAMETER);
				} else {
					throw new NoAboutMethodException(versionProvider, ABOUT_METHOD);
				}
			}
		});
	}

}
