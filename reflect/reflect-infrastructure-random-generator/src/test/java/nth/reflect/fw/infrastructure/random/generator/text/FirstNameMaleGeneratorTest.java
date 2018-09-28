package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class FirstNameMaleGeneratorTest {

	@Test
	public void testGenerate() {
		Set<String> result = Random.firstNameMaleGenerator().generateSet(100000);
		assertThat(result, hasSize(greaterThan(999)));
		assertThat(result, hasItem("Josh"));
		assertThat(result, hasItem("Jamie"));
	}

}
