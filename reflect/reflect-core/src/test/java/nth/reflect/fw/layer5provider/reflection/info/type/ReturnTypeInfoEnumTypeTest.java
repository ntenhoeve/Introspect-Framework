package nth.reflect.fw.layer5provider.reflection.info.type;

import static org.hamcrest.Matchers.equalTo;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.junit.ReflectApplicationForJUnit;

public class ReturnTypeInfoEnumTypeTest {

	private ReflectApplicationForJUnit application;
	private TypeInfo typeInfo;

	public TestEnum theMethod() {
		return TestEnum.A;
	}

	@Before
	public void setUp() throws Exception {
		application = new ReflectApplicationForJUnit();
		Method method = this.getClass().getMethod("theMethod");
		typeInfo = new ReturnTypeInfo(application, method);
	}

	@Test
	public void testGetType() {
		Assert.assertThat(typeInfo.getType(), equalTo(TestEnum.class));
	}

	@Test
	public void testGetGenericType() {
		Assert.assertThat(typeInfo.getGenericType(), equalTo(TestEnum.class));
	}

	@Test
	public void testIsDomainClass() {
		Assert.assertThat(typeInfo.isDomainClass(), equalTo(true));
	}

	@Test
	public void testIsVoid() {
		Assert.assertThat(typeInfo.isVoid(), equalTo(false));
	}

	@Test
	public void testIsCollection() {
		Assert.assertThat(typeInfo.isCollection(), equalTo(false));
	}

	@Test
	public void testHasMultipleValues() {
		Assert.assertThat(typeInfo.hasMultipleValues(), equalTo(false));
	}

	@Test
	public void testIsJavaType() {
		Assert.assertThat(typeInfo.isJavaVariableType(), equalTo(false));
	}

	@Test
	public void testIsArray() {
		Assert.assertThat(typeInfo.isArray(), equalTo(false));
	}

	@Test
	public void testIsMap() {
		Assert.assertThat(typeInfo.isMap(), equalTo(false));
	}

	@Test
	public void testIsEnum() {
		Assert.assertThat(typeInfo.isEnum(), equalTo(true));
	}

}
