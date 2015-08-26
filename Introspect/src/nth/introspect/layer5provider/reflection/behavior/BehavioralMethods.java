package nth.introspect.layer5provider.reflection.behavior;

import java.lang.reflect.Method;

import nth.introspect.layer5provider.reflection.behavior.disabled.DisabledMethod;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenMethod;
import nth.introspect.layer5provider.reflection.behavior.icon.IconMethod;
import nth.introspect.layer5provider.reflection.behavior.parameterfactory.ParameterFactoryMethod;
import nth.introspect.layer5provider.reflection.behavior.validation.ValidationMethod;

public class BehavioralMethods {
	public static HiddenMethod HIDDEN=new HiddenMethod(); 
	public static DisabledMethod DISABLED=new DisabledMethod();
	public static IconMethod ICON=new IconMethod();
	public static ParameterFactoryMethod PARAMETER_FACTORY=new ParameterFactoryMethod();
	public static ValidationMethod VALIDATION=new ValidationMethod();
	public static BehavioralMethod[] ALL=new BehavioralMethod[] {HIDDEN, DISABLED, ICON, PARAMETER_FACTORY, VALIDATION};
	
	public static boolean isBehavioralMethod(Method method) {
		for (BehavioralMethod behavioralMethod: ALL) {
			if(behavioralMethod.isValid(method)) {
				return true;
			}
		}
		return false;
	}

}
