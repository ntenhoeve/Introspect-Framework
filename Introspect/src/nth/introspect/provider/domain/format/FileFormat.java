package nth.introspect.provider.domain.format;

import java.io.File;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class FileFormat extends Format {

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
		if (obj == null) {
			toAppendTo.append("");
		} else {

			File file = (File) obj;
			toAppendTo.append(file.getAbsolutePath());
		}
		return toAppendTo;
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		File file = new File(source);
		return file;
	}

}
