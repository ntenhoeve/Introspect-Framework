package nth.reflect.ui.vaadin.button;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.ui.style.ReflectColorName;

@SuppressWarnings("serial")
@HtmlImport("/frontend/styles/button.html")
public class Button extends Div implements HasSize, HasEnabled, HasStyle, HasText, Focusable<Button> {
	private static final String INNER = "inner";
	private static final String CONTAINER = "container";
	private static final String CLASS_NAME_PREFIX = "reflect-button";
	private final Div innerDiv;

	// TODO (split button up in PrimaryTextButton, PrimaryIconButton, ContentTextButton, ContentIconButton)
//	TODO focusable color 
//	TODO font
//	TODO iconButton (See mainmenubutton and tab selection button)

	public Button(ReflectColorName colorSetName) {
		super();
		setTabIndex(0);
		getClassNames().add(getClassName(CONTAINER));
		getClassNames().add(getClassName(colorSetName.name().toLowerCase()));
		innerDiv = createInnerDiv(colorSetName);
		super.add(innerDiv);
	}

	/**
	 * @param colorSetName 
	 * @return A inner {@link Div} so that we can use padding (without impacting the
	 *         button height) and center the
	 *         {@link Button} content (e.g. text and or icon) .
	 */
	private Div createInnerDiv(ReflectColorName colorSetName) {
		Div contentDiv = new Div();
		contentDiv.getClassNames().add(getClassName(INNER));
		contentDiv.getClassNames().add(getClassName(colorSetName.name().toLowerCase()));
		return contentDiv;
	}

	/**
	 * Overriding method so that content ends up in the {@link #innerDiv}
	 */
	@Override
	public void setText(String text) {
		innerDiv.setText(text);
	}

	/**
	 * Overriding method so that content ends up in the {@link #innerDiv}
	 */
	@Override
	public void add(Component... components) {
		innerDiv.add(components);
	}

	/**
	 * Overriding method so that component is removed from {@link #innerDiv}
	 */
	@Override
	public void remove(Component... components) {
		innerDiv.remove(components);
	}

	/**
	 * Overriding method so that all component are removed from {@link #innerDiv}
	 */
	@Override
	public void removeAll() {
		innerDiv.removeAll();
	}

	private String getClassName(String suffix) {
		StringBuilder className = new StringBuilder();
		className.append(CLASS_NAME_PREFIX);
		className.append("-");
		className.append(suffix);
		return className.toString();
	}

	public Button(ReflectColorName colorSetName, Item item) {
		this(colorSetName);
		setText(item.getText());
		//TODO icon
		getElement().addEventListener("click", e -> item.getAction().run());
	}

}