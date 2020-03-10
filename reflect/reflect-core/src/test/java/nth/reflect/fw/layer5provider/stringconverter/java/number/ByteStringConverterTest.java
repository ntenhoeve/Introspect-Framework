package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.ByteStringConverter;

public class ByteStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(DependencyInjectionContainer container, String formatPattern) {
		return new ByteStringConverter(container, formatPattern);
	}

	@Override
	protected Number getMinValue() {
		return Byte.MIN_VALUE;
	}
	
	@Override
	protected Number getMaxValue() {
		return Byte.MAX_VALUE;
	}


}