package nth.introspect.layer5provider.authorization;

public class DefaultAuthorizationProvider implements AuthorizationProvider {

	public String getCurrentUserName() {
		return System.getProperty("user.name");

	}

	public boolean userInRole(String userRole) {
		return true;
	}

}
