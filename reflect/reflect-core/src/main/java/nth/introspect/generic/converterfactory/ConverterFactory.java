package nth.introspect.generic.converterfactory;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;

import nth.introspect.generic.exception.TypeNotSupportedException;
import nth.introspect.generic.util.TypeUtil;

/**
 * Abstract factory class to help create a converter of type <T> (I.E. a
 * formatter, document or XML converter) for all different types
 * 
 * @author nilsth
 * 
 */
public abstract class ConverterFactory<T> extends
		NumberConverterFactory<T> {

	@SuppressWarnings("unchecked")
	public T createConverter(Class<?> type_) {
		// boolean
		if (Boolean.class.isAssignableFrom(type_)) {
			return createBooleanConverter();
		} else

		// numbers

		if (Number.class.isAssignableFrom(type_)) {
			return createNumberConverter((Class<? extends Number>) type_);
		} else

		// text
		if (Character.class.isAssignableFrom(type_)) {
			return createCharConverter();
		} else if (String.class.isAssignableFrom(type_)) {
			return createStringConverter();
		} else

		// enumeration
		if (type_.isEnum()) {
			return createEnumConverter();
		} else
		// date and time
		if (java.util.Date.class.isAssignableFrom(type_)) {
			return createDateConverter();
		} else if (Calendar.class.isAssignableFrom(type_)) {
			return createCalendarConverter();
		} else
		// URI
		if (URI.class.isAssignableFrom(type_)) {
			return createUriConverter();
		}  else if (URL.class.isAssignableFrom(type_)) {
			return createUrlConverter();
		}		else if (File.class.isAssignableFrom(type_)) {
			return createFileConverter();
		}
		//TODO URL!!!!	
			
		// domainObjects
		if (TypeUtil.isDomainType(type_)) {
			return createDomainConverter();
		} else
		// collections
		if (TypeUtil.isColection(type_)) {
			return createCollectionConverter();
		}

		// Not supported
		throw new TypeNotSupportedException(type_, this.getClass());
	}

	public abstract T  createFileConverter();

	public abstract T createUrlConverter() ;

	public abstract T createBooleanConverter();

	public abstract T createCalendarConverter();

	public abstract T createCharConverter();

	public abstract T createCollectionConverter();

	public abstract T createDateConverter();

	public abstract T createDomainConverter();

	public abstract T createEnumConverter();

	public abstract T createStringConverter();

	public abstract T createUriConverter();
}