package nth.reflect.fw.container.exception;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ReflectContainerException extends TranslatableException {

	private static final long serialVersionUID = 7500700346364627419L;

	public ReflectContainerException(TranslatableString message) {
		super(message);
	}

	public ReflectContainerException(TranslatableString message, Exception exception) {
		super(message, exception);
	}
}
