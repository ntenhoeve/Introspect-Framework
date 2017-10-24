package nth.reflect.javafx.control.itemtreelist;

import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.text.Text;
import javafx.util.Callback;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.item.Item.Action;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.ui.item.method.MethodItem;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.introspect.ui.style.basic.Color;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.style.RfxStyleGroup;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbar;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarTitle;
import nth.reflect.javafx.control.verticalflingscroller.RfxVerticalFlingScroller;

/**
 * Test to replace {@link RfxMainMenuList}
 * 
 * TODO implement {@link RfxVerticalFlingScroller}<br>
 * TODO remove blue (focus?) border e.g. https://stackoverflow.com/questions/37524467/remove-all-focus-borders-from-javafx
 * 
 * @author nilsth
 *
 */
public class RfxItemTreeView extends TreeView {
	private static final String ENTER = "\r";
	private static final String SPACE = " ";

	
	public RfxItemTreeView(TreeItem<Item> rootItem) {
		super();
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxItemTreeView.class));
		setRoot(rootItem);
		setEditable(false);
		setShowRoot(false);
		setCellFactory(createCellFactory());
		setOnKeyTyped(createKeyHandler());
		setOnMouseClicked(createMouseHandler());
	}

	private Callback<TreeView<Item>, TreeCell<Item>> createCellFactory() {
		return new Callback<TreeView<Item>, TreeCell<Item>>() {
			@Override
			public TreeCell<Item> call(TreeView<Item> p) {
				return new RfxItemTreeCell();
			}
		};
	}

	private EventHandler<? super MouseEvent> createMouseHandler() {
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (MouseButton.PRIMARY.equals(event.getButton()) && event.getClickCount()==1) {
					RfxItemTreeView itemTreeView = (RfxItemTreeView) event.getSource();
					TreeItem<Item> treeItem = (TreeItem<Item>) itemTreeView.getFocusModel()
							.getFocusedItem();
					Item item = treeItem.getValue();
					if (item instanceof MethodItem) {
						onAction(treeItem.getValue());
					}
					
				}
			}
		};
	}

	private EventHandler<? super KeyEvent> createKeyHandler() {
		return new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				String character = event.getCharacter();
				if (SPACE.equals(character) || ENTER.equals(character)) {
					RfxItemTreeView itemTreeView = (RfxItemTreeView) event.getSource();
					TreeItem<Item> treeItem = (TreeItem<Item>) itemTreeView.getFocusModel()
							.getFocusedItem();
					Item item = treeItem.getValue();
					if (item instanceof MethodOwnerItem) {
						toggleIsExpanded(treeItem);
						getSelectionModel().select(treeItem);
					} else if (item instanceof MethodItem) {
						onAction(treeItem.getValue());
						getSelectionModel().select(treeItem);
					}
				}
			}
		};
	}

	protected void onAction(Item item) {
		
		Action action = item.getAction();
		if (action != null) {
			action.run();
		}
	}

	protected void toggleIsExpanded(TreeItem<Item> treeItem) {
		boolean expanded = treeItem.isExpanded();
		treeItem.setExpanded(!expanded);
	}



	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		//remove border
		RfxStyleProperties properties = styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxItemTreeView.class)).getProperties();
		properties.put("-fx-background-color", "transparent");
		properties.put("-fx-background-insets"," 0");
		properties.put("-fx-padding","0");

	}

}