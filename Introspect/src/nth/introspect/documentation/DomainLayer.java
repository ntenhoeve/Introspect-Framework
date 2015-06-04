package nth.introspect.documentation;

import nth.introspect.container.impl.DomainContainer;

/**
 * The Domain layer is the hart of any Introspect application. The domain layer
 * represents concepts of the business, information about the business
 * situation, and business rules. State that reflects the business situation is
 * controlled and used here.<br>
 * <br>
 * Because there is many debate on the naming within a layered architecture,
 * the domain layer is sometimes also called:
 * <ul>
 * <li><a href="http://en.wikipedia.org/wiki/Business_logic">Business</a>  layer</li>
 * <li><a href="http://en.wikipedia.org/wiki/Business_logic">Business logic</a> layer</li>
 * <li><a href="http://en.wikipedia.org/wiki/Domain_model">Domain model</a> layer</li>
 * </ul>
 * The domain layer is basically where all the domain objects are. <br>
 * <br>
 * Note that this layer is a middle layer: the {@link DomainObject}s have no
 * knowledge of the objects in the higher layers ({@link UserInterfaceLayer} or
 * {@link ServiceLayer}). The {@link DomainObject}s may know the objects in the
 * lower {@link InfrastructureLayer} but not visa versa!
 * 
 * <h2>Domain Objects</h2> 
 * {@insert DomainObject}
 * 
 * @author Nils ten Hoeve
 * @see IntrospectArchitecture
 */

public interface DomainLayer extends Documentation {

}