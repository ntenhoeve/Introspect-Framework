package nth.introspect.documentation;

import nth.introspect.IntrospectApplication;

/**
 * <p>
 * There are different implementations of the {@link IntrospectApplication}
 * class, to support different type of applications (e.g. for the command line,
 * desktop, mobile, web environments). This chapter will explain the different
 * {@link IntrospectProviderProjects} that come with the
 * {@link IntrospectFramework}. If needed you can of course free to write your
 * implementation of the {@link IntrospectApplication}.
 * </p>
 * 
 * <h2>Introspect for CommandLine</h2>
 * <p>
 * {@include IntrospectApplicationForCommandLine}
 * </p>
 * 
 * <h2>Introspect for Swing</h2>
 * <p>
 * {@include IntrospectApplicationForSwing}
 * </p>
 * 
 * <h2>Introspect for JUnit</h2>
 * <p>
 * {@include IntrospectApplicationForJUnit}
 * </p>
 * 
 * @author nilsth
 *
 */
public interface IntrospectApplicationProjects extends Documentation {

}
