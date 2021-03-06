package nth.reflect.util.random.generator.number;

import java.math.BigDecimal;

import nth.reflect.util.random.RandomGenerator;

public class BigDecimalGenerator extends RandomGenerator<BigDecimal> {

	private final BigDecimal min;
	private final BigDecimal max;

	public BigDecimalGenerator() {
		this(BigDecimal.ZERO, BigDecimal.valueOf(Double.MAX_VALUE));
	}

	public BigDecimalGenerator(BigDecimal min, BigDecimal max) {
		this.min = min;
		this.max = max;
	}

	public BigDecimalGenerator forMax(BigDecimal max) {
		return new BigDecimalGenerator(BigDecimal.ZERO, max);
	}

	public BigDecimalGenerator forRange(BigDecimal min, BigDecimal max) {
		return new BigDecimalGenerator(min, max);
	}

	@Override
	public BigDecimal generate() {
		if (min.compareTo(max) > 0) {
			return min;
		}
		return min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
	}

}
