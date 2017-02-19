package hrv.calc.statistical;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import units.TimeUnitConverter.TimeUnit;

public class ModeCalculatorTest {

	@Test
	public void testModeCalc() {
		double[] valueData = new double[] { 0.0, 1.0, 2.0, 3.0, 4.0, 4.1, 5.0, 5.01, 5.02, 6.0, 7.0 };
		RRData data = new RRData(null, TimeUnit.SECOND, valueData, TimeUnit.SECOND);
		ModeCalculator calc = new ModeCalculator();
		assertEquals(5.0, calc.process(data), 0.00001);
	}
}
