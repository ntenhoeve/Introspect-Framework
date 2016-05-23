package nth.reflect.javafx.control.list;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.ui.item.method.MethodOwnerItem;

public class RfxMainMenuListCellFactory implements Callback<ListView<Item>, ListCell<Item>> {

	@Override
	public ListCell<Item> call(ListView<Item> param) {
		return new RfxMainMenuListCell();
	}


}
