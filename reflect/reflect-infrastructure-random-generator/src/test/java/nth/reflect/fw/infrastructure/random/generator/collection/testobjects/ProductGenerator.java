package nth.reflect.fw.infrastructure.random.generator.collection.testobjects;

import java.math.BigDecimal;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.WordLineParagraphChapterExample;

public class ProductGenerator extends RandomGenerator<Product> {
/**
 * FIXME replace {@link #CODE_12345} with a {@link RandomGenerator} (e.g. a FormatGenerator)
 */
private static final String CODE_12345 = "CODE-12345";
	private final WordLineParagraphChapterExample detailFactory;
	
	public ProductGenerator() {
//		idFactory=new StringFactory(5, false, true);
		detailFactory=new WordLineParagraphChapterExample(5, 10, 1, 5, 1, 3);
	}
	
	@Override
	public Product generate() {
		Product product=new Product();
		product.setCode(CODE_12345);
		product.setName(Random.productNameGenerator().generate());
		product.setDetails(detailFactory.generate());
		product.setCompanyName(detailFactory.generate());
		product.setPrice(Random.bigDecimalGenerator().forRange(new BigDecimal("10.0"),new BigDecimal("1000.0")) .generate());
		return product;
	}


}
