package nth.reflect.fw.javafx.control.window.mainmenu;

import javafx.scene.layout.BorderPane;
import nth.reflect.fw.javafx.control.itemtreelist.RfxItemTreeView;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.javafx.control.window.RfxWindow;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.style.MaterialColorSetCssName;

public class RfxMainMenuPane extends BorderPane {

	public RfxMainMenuPane(UserInterfaceContainer userInterfaceContainer) {
		super();
	addStyleClass();
	
		setMinWidth(RfxWindow.MENU_WIDTH);
		setMaxWidth(RfxWindow.MENU_WIDTH);

		RfxItemTreeView mainMenuList = new RfxMainMenuItemTreeView(userInterfaceContainer);
		setCenter(mainMenuList);
	}
	

	private void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxMainMenuPane.class));
	}
	
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxMainMenuPane.class)).getProperties()
		.setBorderWidth(0,1,0,0)
		.setBorderColor(MaterialColorSetCssName.CONTENT.FOREGROUND3());
		//TODO replace right border with shade (raised pane)?
	}	


}