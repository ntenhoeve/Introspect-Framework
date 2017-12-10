package nth.reflect.javafx.control.button;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.ui.style.MaterialColorSet;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.introspect.ui.style.MaterialFont;
import nth.reflect.javafx.ReflectApplicationForJavaFX;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.fonticon.RfxFontIcon;
import nth.reflect.javafx.control.fonticon.RfxFontIconName;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;

/**
 * {@link RfxControl} button in the content color, see
 * {@link ReflectApplicationForJavaFX#getContentColor()
 * 
 * @author nilsth
 *
 */
public class RfxContentButton extends JFXButton implements RfxControl {

	private static final int ICON_SIZE = 17;
	private static final int MIN_HEIGHT = 32;
	private RfxFontIconName fontIconName;
	private MaterialColorSet colorSet;

	public RfxContentButton() {
		super();
		addStyleClass();
	}

	public RfxContentButton(String text) {
		super(text);
		addStyleClass();
	}

	public RfxContentButton(RfxFontIconName fontIconName) {
		super();
		addStyleClass();
		setFontIconName(fontIconName);
	}

	public RfxContentButton(Item item) {
		super();
		addStyleClass();
		setText(item.getText());
		setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				item.getAction().run();
			}
		});
	}

	private void setFontIconName(RfxFontIconName fontIconName) {
		this.fontIconName = fontIconName;
		if (fontIconName == null) {
			setGraphic(null);
		} else {
			setGraphic(new RfxFontIcon(fontIconName, ICON_SIZE));
		}
	}

	public RfxContentButton(String text, Node node) {
		super(text, node);
		addStyleClass();
	}

	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxContentButton.class));
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxContentButton.class)).getProperties()
				.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND())
				.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND1()).setMinHeight(MIN_HEIGHT)
				.setFont(MaterialFont.getRobotoMedium14());
		// rippler color
		styleSheet
				.addStyleGroup(RfxStyleSelector.createFor(RfxContentButton.class)
						.appendChild("jfx-rippler"))
				.getProperties()
				.put("-jfx-rippler-fill", MaterialColorSetCssName.CONTENT.BACKGROUND_HIGHLIGHTED());
		// icon color
		styleSheet
				.addStyleGroup(RfxStyleSelector.createFor(RfxContentButton.class)
						.appendChild(RfxFontIcon.class))
				.getProperties().setFill(MaterialColorSetCssName.CONTENT.FOREGROUND1());
	}

}
