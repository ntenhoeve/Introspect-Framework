package nth.reflect.fw.swing;

import java.net.MalformedURLException;
import java.util.List;

import javax.swing.JOptionPane;

import nth.reflect.fw.gui.GraphicalUserInterfaceController;
import nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldProvider;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.item.Item.Action;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.swing.dialog.toast.Toast;
import nth.reflect.fw.swing.dialog.toast.Toast.Style;
import nth.reflect.fw.swing.mainwindow.MainWindow;
import nth.reflect.fw.swing.tab.Tab;
import nth.reflect.fw.swing.tab.form.proppanel.PropertyPanel;
import nth.reflect.fw.swing.tab.form.proppanel.PropertyPanelFactory;

public class UserinterfaceControllerForSwing extends GraphicalUserInterfaceController<Tab, PropertyPanel> {

	private MainWindow mainWindow;

	public UserinterfaceControllerForSwing(UserInterfaceContainer container) {
		super(container);
	}

	@Override
	public void launch() {
		try {
			mainWindow = new MainWindow(container);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void showProgressDialog(TranslatableString taskDescription, int currentValue, int maxValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeProgressDialog() {
		// TODO Auto-generated method stub

	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	@Override
	public void showMessage(TranslatableString message) {
		String translatedMessage = message.getTranslation(languageProvider);
		Toast.makeText(mainWindow, translatedMessage, Style.NORMAL).display();
	}

	@Override
	public void showDialog(TranslatableString title, TranslatableString message, List<Item> items) {

		// get options, assuming that all items are enabled and visible
		Object[] options = new String[items.size()];
		for (int index = 0; index < items.size(); index++) {
			options[index] = items.get(index).getText();
		}
		Object defaultOption = options[items.size() - 1];

		// show dialog
		String translatedMessage = message.getTranslation(languageProvider);
		String translatedTitle = title.getTranslation(languageProvider);
		int selectedIndex = JOptionPane
				.showOptionDialog(mainWindow, translatedMessage, translatedTitle, JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, defaultOption);

		// execute selected item
		if (selectedIndex != -1) {
			Item selectedItem = items.get(selectedIndex);
			Action action = selectedItem.getAction();
			if (action != null) {
				action.run();
			}
		}

	}

	@Override
	public PropertyPanelFactory getPropertyPanelFactory() {
		PropertyFieldProvider propertyFieldProvider = container.get(PropertyFieldProvider.class);
		return new PropertyPanelFactory(propertyFieldProvider);
	}

}
