package nth.introspect.junit;

import nth.introspect.junit.generic.titlebuilder.TitleBuilderTest;
import nth.introspect.junit.layer5provider.authorization.AcmeAuthorizationProviderTest;
import nth.introspect.junit.layer5provider.reflection.behavior.disabled.DisabledModelForActionMethodTest;
import nth.introspect.junit.layer5provider.reflection.behavior.disabled.DisabledModelForPropertiesTest;
import nth.introspect.junit.layer5provider.reflection.behavior.executionmode.ExecutionModeTest;
import nth.introspect.junit.layer5provider.reflection.behavior.hidden.HiddenModelForActionMethodTest;
import nth.introspect.junit.layer5provider.reflection.behavior.hidden.HiddenModelForPropertiesTest;
import nth.introspect.junit.layer5provider.reflection.behavior.icon.IconUriClassResourceTest;
import nth.introspect.junit.layer5provider.reflection.behavior.icon.foractionmethod.IconModelForActionMethodTest;
import nth.introspect.junit.layer5provider.reflection.behavior.icon.forclass.IconModelForClassTest;
import nth.introspect.junit.layer5provider.reflection.behavior.parameterfactory.ParameterFactoryModelTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TitleBuilderTest.class, AcmeAuthorizationProviderTest.class,
		DisabledModelForPropertiesTest.class,
		DisabledModelForActionMethodTest.class,
		HiddenModelForPropertiesTest.class,
		HiddenModelForActionMethodTest.class, ParameterFactoryModelTest.class,
		ExecutionModeTest.class, IconUriClassResourceTest.class,
		IconModelForClassTest.class, IconModelForActionMethodTest.class })
public class IntrospectAllTests {

}