package nth.introspect.layer5provider.language;

import java.net.URI;
import java.net.URL;

import nth.introspect.layer5provider.path.PathProvider;

public class ResourceBundleClassLoader extends ClassLoader {

	
	private URI configPath;

	public ResourceBundleClassLoader(PathProvider pathProvider) {
		configPath=pathProvider.getConfigPath();
	}
	
	@Override
	protected URL findResource(String fileName) {
		try {
			URL url = configPath.resolve(fileName).toURL();
			return url;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
