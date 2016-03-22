package nth.introspect.ui.swing.view.menu;

import java.util.List;

import javax.swing.DefaultListModel;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer2service.ServiceContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.swing.view.menu.item.Item;
import nth.introspect.ui.swing.view.menu.item.ServiceObjectItem;

public class MenuListModel extends DefaultListModel<Item> {

	private static final long serialVersionUID = 1676652394002556458L;

	public MenuListModel(DependencyInjectionContainer userInterfaceContainer) {
		populateListModel(userInterfaceContainer);
	}

	private void populateListModel(DependencyInjectionContainer userInterfaceContainer) {
		ServiceContainer serviceContainer = userInterfaceContainer.get(ServiceContainer.class);
		List<Object> serviceObjects = serviceContainer.getServiceObjects();
		ReflectionProvider reflectionProvider = userInterfaceContainer
				.get(ReflectionProvider.class);
		UserInterfaceController userInterfaceController = userInterfaceContainer
				.get(UserInterfaceController.class);

		for (Object serviceObject : serviceObjects) {
			ClassInfo serviceObjectInfo = reflectionProvider.getClassInfo(serviceObject.getClass());
			ServiceObjectItem serviceObjectItem = new ServiceObjectItem(userInterfaceController,
					serviceObject, serviceObjectInfo);
			addElement(serviceObjectItem);
			List<Item> actionMethodItems = serviceObjectItem.getActionMethodItems();
			for (Item actionMethoditem : actionMethodItems) {
				addElement(actionMethoditem);
			}

		}
	}

}