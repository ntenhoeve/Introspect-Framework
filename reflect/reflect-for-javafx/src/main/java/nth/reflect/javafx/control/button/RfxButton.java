package nth.reflect.javafx.control.button;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import nth.introspect.ui.style.MaterialColorSet;
import nth.introspect.ui.style.basic.Color;
import nth.reflect.javafx.control.fonticon.RfxFontIcon;
import nth.reflect.javafx.control.fonticon.RfxFontIconName;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.style.RfxStyleGroup;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.window.RfxUtil;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.geometry.Insets;

public class RfxButton extends JFXButton {

	private static final int ICON_SIZE = 17;
	private RfxFontIconName fontIconName;
	private MaterialColorSet colorSet;

	public RfxButton() {
		super();
		init();
	}

	public RfxButton(String text) {
		super(text);
		init();
	}

	public RfxButton(RfxFontIconName fontIconName) {
		super();
		init();
		this.fontIconName = fontIconName;
		setFontIconName(fontIconName);
	}

	private void setFontIconName(RfxFontIconName fontIconName) {
		this.fontIconName = fontIconName;
		updateColors();
	}

	public RfxButton(String text, Node node) {
		super(text, node);
		init();
	}

	public void setColorSet(MaterialColorSet colorSet) {
		this.colorSet = colorSet;
		updateColors();
	}

	private void updateColors() {
		setBackground(
				new Background(new BackgroundFill(RfxColorFactory.create(colorSet.getBackground()),
						CornerRadii.EMPTY, Insets.EMPTY)));
		ripplerFillProperty().set(RfxColorFactory.create(colorSet.getBackgroundHighLighted()));
		Color foreColor = (isDisabled()) ? colorSet.getForeground3() : colorSet.getForeground1();
		setTextFill(RfxColorFactory.create(foreColor));
		if (fontIconName == null) {
			setGraphic(null);
		} else {
			setGraphic(new RfxFontIcon(fontIconName, ICON_SIZE, foreColor));
		}
	}

	private void init() {
		getStylesheets().add(RfxStyleSheet.createStyleClassName(RfxButton.class));
		setColorSet(RfxUtil.getContentColorSet());
		disabledProperty().addListener((observable, oldValue, newValue) -> {
			updateColors();
		});
	}

}
