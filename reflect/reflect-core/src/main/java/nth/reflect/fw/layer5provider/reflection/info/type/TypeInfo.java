package nth.reflect.fw.layer5provider.reflection.info.type;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.generic.util.JavaTypeConverter;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * Provides information on a type, e.g. a {@link DomainObjectProperty} type, a
 * {@link ActionMethod} parameter type or a {@link ActionMethod} return type.
 * 
 * @author nilsth
 *
 */
public class TypeInfo {

	private final Class<?> type;
	private final Class<?> genericType;
	private final boolean isVoid;
	private final boolean isCollection;
	private final boolean hasMultipleValues;
	private final boolean isJavaVariableType;
	private final boolean isDomainClass;
	private final boolean isArray;
	private final boolean isMap;
	private final boolean isEnum;
	private final boolean isHierarchicalDomainType;

	public TypeInfo(ReflectApplication reflectApplication, Class<?> type, Type genericType) {
		this.type = type;
		this.genericType = getGenericType(genericType);
		this.isVoid = isVoid(type);
		this.isCollection = Collection.class.isAssignableFrom(this.type);
		this.isMap = Map.class.isAssignableFrom(this.type);
		this.isArray = this.type.isArray();
		this.isEnum = this.type.isEnum();
		this.isJavaVariableType = isJavaVariableType(this.genericType);
		this.hasMultipleValues = isArray || isCollection || isMap;
		boolean isReflectApplication = ReflectApplication.class.isAssignableFrom(this.type);
		boolean isServiceClass = isServiceClass(this.type, reflectApplication);
		boolean isInfrastructureClass = isInfrastructureClass(this.type, reflectApplication);
		this.isDomainClass = !isVoid && !isJavaVariableType && !hasMultipleValues && !isReflectApplication
				&& !isServiceClass && !isInfrastructureClass;
		this.isHierarchicalDomainType = isHierarchicalDomainType(type);
	}

	/**
	 * @param type
	 *            e.g. the generic parameter or the generic return type of a
	 *            method
	 * @return If type is an {@link Array} it returns its type<br>
	 *         If type is an {@link ParameterizedType} (e.g. the parameter or
	 *         the return type of a method) it returns the generic type of a
	 *         collection<br>
	 *         Otherwise it returns
	 */
	public static Class<?> getGenericType(Type type) {
		if (type instanceof Class<?>) {
			Class<?> cls = (Class<?>) type;
			if (cls.isArray()) {
				return cls.getComponentType();
			} else {
				return cls;
			}
		} else if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			if (actualTypeArguments.length != 1) {
				return (Class<?>) parameterizedType.getRawType();
			}
			Type actualType = actualTypeArguments[0];
			if (actualType.toString().equals("java.lang.Class<?>")) {
				return Class.class;
			}
			Class<?> genericType = (Class<?>) actualType;
			return genericType;
		}
		return (Class<?>) type;
	}

	private static boolean isVoid(Class<?> type) {
		return type == Void.TYPE;
	}

	/**
	 * See {@link #getGenericType(Type)}
	 */
	public Class<?> getGenericType() {
		return genericType;
	}

	public Class<?> getType() {
		return type;
	}

	/**
	 * @return true if the type can contain multiple values (can be displayed as
	 *         a table or grid) at a given time. Note that a {@link Enum} only
	 *         contains one value at a time.
	 */
	public boolean hasMultipleValues() {
		return hasMultipleValues;
	}

	public boolean isArray() {
		return isArray;
	}

	public boolean isCollection() {
		return isCollection;
	}

	/**
	 * 
	 * @return true if the type is a custom made class {@link DomainObject}
	 *         class (not part of java language or {@link ReflectFramework})
	 *         that can be displayed in a form. Note that an custom {@link Enum}
	 *         can contain getter methods and is therefore a (none editable)
	 *         domain object that could be displayed in a form
	 */
	public boolean isDomainClass() {
		return isDomainClass;
	}

	/**
	 *
	 * @param type
	 * @return true if type is a domain type and has a method <Collection>
	 *         getChildren()
	 */
	private boolean isHierarchicalDomainType(Class<?> type) {
		if (isDomainClass) {
			Class<?>[] arguments = new Class[0];
			try {
				Method method = type.getMethod("getChildren", arguments);
				return method.getReturnType().isAssignableFrom(Collection.class);
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * @return See {@link #isHierarchicalDomainType(Class)}
	 */
	public boolean isHierarchicalDomainType() {
		return isHierarchicalDomainType;
	}

	public boolean isEnum() {
		return isEnum;
	}

	private boolean isInfrastructureClass(Class<?> classToFind, ReflectApplication reflectApplication) {
		List<Class<?>> infrastructureClasses = reflectApplication.getInfrastructureClasses();
		if (infrastructureClasses == null) {
			return false;
		} else {
			return infrastructureClasses.contains(classToFind);
		}
	}

	public boolean isJavaVariableType() {
		return isJavaVariableType;
	}

	public static boolean isJavaVariableType(Class<?> type) {
		Class<?> complexType = JavaTypeConverter.getComplexType(type);
		String canonicalName = complexType.getCanonicalName();
		boolean startsWithJavaPackageName = canonicalName.startsWith("java");
		return startsWithJavaPackageName;
	}

	public boolean isMap() {
		return isMap;
	}

	private boolean isServiceClass(Class<?> classToFind, ReflectApplication reflectApplication) {
		List<Class<?>> serviceClasses = reflectApplication.getServiceClasses();
		if (serviceClasses == null) {
			return false;
		} else {
			return serviceClasses.contains(classToFind);
		}
	}

	public boolean isVoid() {
		return isVoid;
	}

}