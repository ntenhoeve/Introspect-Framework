package nth.reflect.fw.javafx.control.tab.form.proppanel;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyLabelStyle;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;

public class PropertyLabel extends Label implements nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyLabel {

	public PropertyLabel() {
		getStyleClass().add(StyleSheet.createStyleClassName(PropertyLabel.class));
	}

	@Override
	public void setDescription(String description) {
		setTooltip(new Tooltip(description));
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet.addStyleGroup(StyleSelector.createFor(PropertyLabel.class)).getProperties()
				.setTextFill(ReflectColorName.CONTENT.FOREGROUND()).setFont(PropertyLabelStyle.getFont())
				.setWrapText(true);
	}

}
