package nth.introspect.swing.demo;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.domain.classdiagram.ClassDiagramService;
import nth.introspect.domain.test.TestsService;
import nth.introspect.ui.swing.IntrospectApplicationForSwing;


public class IntrospectSwingDemo extends IntrospectApplicationForSwing {

	public IntrospectSwingDemo(String[] arguments) {
		super(arguments);
	}

	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		new IntrospectSwingDemo(arguments);
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		List<Class<?>> serviceClasses=new ArrayList<Class<?>>();
		serviceClasses.add(TestsService.class);
		serviceClasses.add(ClassDiagramService.class);
		return serviceClasses;
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return new ArrayList<Class<?>>();
	}

}
