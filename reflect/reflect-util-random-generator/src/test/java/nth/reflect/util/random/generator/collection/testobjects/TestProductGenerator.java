package nth.reflect.util.random.generator.collection.testobjects;

import java.math.BigDecimal;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.RandomGenerator;

public class TestProductGenerator extends RandomGenerator<TestProduct> {
	@Override
	public TestProduct generate() {
		TestProduct testProduct = new TestProduct();
		testProduct.setCode(Random.format().forFormat("AA-999-999-999").generate());
		nth.reflect.util.random.generator.name.RandomProduct randomProduct = Random.product().generate();
		testProduct.setName(randomProduct.getName());
		testProduct.setDetails(randomProduct.getDescription());
		testProduct.setCompanyName(randomProduct.getCompany());
		testProduct.setPrice(Random.bigDecimal().forRange(new BigDecimal("10.0"), new BigDecimal("1000.0")).generate());
		return testProduct;
	}
}
