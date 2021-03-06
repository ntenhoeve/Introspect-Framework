package nth.reflect.fw.javafx.control.style;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.javafx.control.button.ContentBottomToolbarButton;
import nth.reflect.fw.javafx.control.button.ContentButton;
import nth.reflect.fw.javafx.control.button.PrimaryButton;
import nth.reflect.fw.javafx.control.dialog.Dialog;
import nth.reflect.fw.javafx.control.itemtreelist.ItemTreeCell;
import nth.reflect.fw.javafx.control.itemtreelist.ItemTreePanel;
import nth.reflect.fw.javafx.control.list.List;
import nth.reflect.fw.javafx.control.mainwindow.content.ContentPane;
import nth.reflect.fw.javafx.control.mainwindow.mainmenu.MainMenuPane;
import nth.reflect.fw.javafx.control.tab.form.FormTab;
import nth.reflect.fw.javafx.control.tab.form.PropertyGrid;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyLabel;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyLabelAndFieldPanel;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyPanel;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyValidationLabel;
import nth.reflect.fw.javafx.control.table.Table;
import nth.reflect.fw.javafx.control.toolbar.Toolbar;
import nth.reflect.fw.javafx.layer5provider.properyfield.factory.CheckBoxField;
import nth.reflect.fw.javafx.layer5provider.properyfield.factory.TableField;
import nth.reflect.fw.javafx.layer5provider.properyfield.factory.TextField;
import nth.reflect.fw.layer5provider.url.ReflectUrlStreamHandler;

public class StyleSheetUrlHandler extends ReflectUrlStreamHandler {

	private String css;

	public StyleSheetUrlHandler(ReflectApplicationForJavaFX applicationForJavaFX) {
		try {
			StyleSheet styleSheet = new StyleSheet();

			appendColorDefinitions(styleSheet, applicationForJavaFX);
			appendPanes(styleSheet);
			appendControls(styleSheet);

			System.out.println(styleSheet.toString());

			css = styleSheet.toString();
		} catch (Throwable e) {
			// Failed: toolbox not initialized?
			css = "";
		}
	}

	private void appendControls(StyleSheet styleSheet) {
		appendToolbars(styleSheet);
		appendButtons(styleSheet);
		appendPropertyPanel(styleSheet);
		appendFields(styleSheet);
		ItemTreePanel.appendStyleGroups(styleSheet);
		ItemTreeCell.appendStyleGroups(styleSheet);
	}

	private void appendFields(StyleSheet styleSheet) {
		TextField.appendStyleGroups(styleSheet);
		CheckBoxField.appendStyleGroups(styleSheet);
		TableField.appendStyleGroups(styleSheet);
	}

	private void appendPropertyPanel(StyleSheet styleSheet) {
		PropertyGrid.appendStyleGroups(styleSheet);
		PropertyPanel.appendStyleGroups(styleSheet);
		PropertyLabelAndFieldPanel.appendStyleGroups(styleSheet);
		PropertyLabel.appendStyleGroups(styleSheet);
		PropertyValidationLabel.appendStyleGroups(styleSheet);
	}

	private void appendToolbars(StyleSheet styleSheet) {
		Toolbar.appendStyleGroups(styleSheet);
	}

	private void appendButtons(StyleSheet styleSheet) {
		ContentButton.appendStyleGroups(styleSheet);
		ContentBottomToolbarButton.appendStyleGroups(styleSheet);
		PrimaryButton.appendStyleGroups(styleSheet);
	}

	private void appendPanes(StyleSheet styleSheet) {
		Dialog.appendStyleGroups(styleSheet);
		ContentPane.appendStyleGroups(styleSheet);
		FormTab.appendStyleGroups(styleSheet);
		List.appendStyleGroups(styleSheet);
		Table.appendStyleGroups(styleSheet);
		MainMenuPane.appendStyleGroups(styleSheet);
	}

	/**
	 * Fixme: get colors from
	 * {@link ReflectionProvider#getApplicationInfo().getColors() with annotaions in
	 * ReflectApplication class}
	 * 
	 * @param styleSheet
	 * @param applicationForJavaFX
	 * @param userInterfaceController
	 */
	private void appendColorDefinitions(StyleSheet styleSheet, ReflectApplicationForJavaFX applicationForJavaFX) {
		ColorProvider colors = applicationForJavaFX.getColorProvider();
		StyleGroup colorDefintion = styleSheet.addStyleGroup(StyleSelector.createFor("*"));
		colorDefintion.getProperties().setColorVariables(ReflectColorName.PRIMARY, colors.getPrimaryColors());
		colorDefintion.getProperties().setColorVariables(ReflectColorName.ACCENT, colors.getAccentColors());
		colorDefintion.getProperties().setColorVariables(ReflectColorName.CONTENT, colors.getContentColors());
	}

	@Override
	public String getProtocol() {
		return StyleSheetUrl.PROTOCOL;
	}

	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		return new URLConnection(url) {

			@Override
			public void connect() throws IOException {
			}

			@Override
			public InputStream getInputStream() throws IOException {
				return new ByteArrayInputStream(css.getBytes(StandardCharsets.UTF_8));
			}

		};
	}

}
