package nth.reflect.fw.gui.component.tab.form;

import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanel;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer3domain.DomainObjectPropertyActionMethod;

public enum FormMode {
	/**
	 * All {@link PropertyField}s within all {@link PropertyPanel}s are
	 * disabled:
	 * <ul>
	 * <li>{@link PropertyPanel}s only show the {@link DomainObjectProperty}
	 * values.</li>
	 * <li>No {@link DomainObjectPropertyActionMethod} are visible!</li>
	 * </ul>
	 */
	READ_ONLY,
	/**
	 * {@link PropertyField}s within all {@link PropertyPanel}s can be enabled
	 * or disabled.
	 * <ul>
	 * <li>{@link PropertyField}s that are enabled can edit the
	 * {@link DomainObjectProperty} value.</li>
	 * <li>{@link DomainObjectPropertyActionMethod} can be visible or not</li>
	 * </ul>
	 */
	EDIT
}
