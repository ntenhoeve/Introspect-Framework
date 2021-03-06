package nth.reflect.fw.swing.item.menu;

import javax.swing.JMenuItem;

import nth.reflect.fw.gui.item.HierarchicalItem;
import nth.reflect.fw.layer1userinterface.item.Item;

public class MenuItemFactory {
	public static JMenuItem create(Item item) {
		if (item instanceof HierarchicalItem) {
			HierarchicalItem hierarchicalItem = (HierarchicalItem) item;
			if (hierarchicalItem.getSize() == 0) {
				return new MenuItem(item);
			} else {
				return new Menu(hierarchicalItem);
			}
		}
		return new MenuItem(item);
	}
}
