package nth.introspect.layer5provider.reflection.behavior.hidden;

import java.lang.reflect.Method;

import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethods;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;

/**
 * A {@link DomainObjectProperty} or {@link ActionMethod} is visible on the user
 * interface by default,but in some cases you may want to hide them, e.g.:
 * <ul>
 * <li>Because not all information needs to be displayed in the user interface
 * (e.g. a database id key or version number for optimistic locking)</li>
 * <li>Because you want to limit the number of visible properties in a table
 * (e.g. to limit the number of columns in a table to prevent the table to
 * become cluttered with to much information)</li>
 * <li>Depending on the state of a {@link DomainObject} you do not want to
 * display Irrelevant information</li>
 * <li>Depending on the state of a domain object you do not want a user to
 * activate an action method (e.g. hide an {@link ActionMethod} �submit� once
 * the domainObject already is submitted)</li>
 * <li>Because a user is not authorized to see a property value</li>
 * <li>Because a user is not authorized to activate an {@link ActionMethod}</li>
 * </ul>
 * 
 * <h3>Hidden Annotation</h3>
 * <p>
 * {@insert Hidden}
 * </p>
 * 
 * <h3>Hidden Method</h3>
 * <p>
 * {@insert HiddenMethod}
 * </p>
 * 
 * 
 * @author nilsth
 *
 */

public class HiddenModelFactory {

	/**
	 * 
	 * @param method
	 *            can be a getterMethod of a {@link DomainObjectProperty} or a
	 *            {@link ActionMethod}
	 * @return A {@link HiddenModel} that checks if an item is hidden or not (at
	 *         runtime)
	 */
	public static HiddenModel create(
			AuthorizationProvider authorizationProvider, Method method) {

		HiddenMethodModel hiddenMethodModel = createHiddenMethodModel(method);
		HiddenAnnotationModel hiddenAnnotationModel = createHiddenAnnotationModel(
				authorizationProvider, method);

		boolean hasMethod = hiddenMethodModel != null;
		boolean hasAnnotation = hiddenAnnotationModel != null;

		if (hasAnnotation && !hasMethod) {
			return hiddenAnnotationModel;
		} else if (!hasAnnotation && hasMethod) {
			return hiddenMethodModel;
		} else if (hasAnnotation && hasMethod) {
			return new HiddenOrModel(hiddenAnnotationModel, hiddenMethodModel);
		} else { // !hasAnnotation && !hasMethod
			return HiddenFalseModel.getInstance();
		}
	}

	private static HiddenAnnotationModel createHiddenAnnotationModel(
			AuthorizationProvider authorizationProvider, Method method) {
		Hidden hiddenAnnotation = method.getAnnotation(Hidden.class);
		if (hiddenAnnotation == null) {
			return null;
		} else {
			return new HiddenAnnotationModel(authorizationProvider,
					hiddenAnnotation);
		}
	}

	private static HiddenMethodModel createHiddenMethodModel(Method method) {
		Method hiddenMethod = BehavioralMethods.HIDDEN.findFor(method);
		if (hiddenMethod == null) {
			return null;
		} else {
			return new HiddenMethodModel(hiddenMethod);
		}
	}

	public static HiddenModel create(
			AuthorizationProvider authorizationProvider, Method getterMethod,
			Method setterMethod, TypeCategory typeCategory) {

		HiddenModel hiddenModel = create(authorizationProvider, getterMethod);
		if (typeCategory == TypeCategory.COLLECTION_TYPE) {
			hiddenModel = new HiddenCollectionModel(hiddenModel);
		}
		return hiddenModel;
	}
}
