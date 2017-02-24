package hrv.calc.statistical;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import units.TimeUnitConverter.TimeUnit;

public class AmplitudeModeCalculatorTest {

	@Test
	public void testCalculation() {
		double[] valueData = new double[] { 0.0, 1.0, 2.0, 3.0, 4.0, 4.1, 5.0, 5.01, 5.02, 6.0, 7.0 };
		RRData data = new RRData(null, TimeUnit.SECOND, valueData, TimeUnit.SECOND);
		AmplitudeModeCalculator calc = new AmplitudeModeCalculator();
		assertEquals(3.0 / valueData.length, calc.process(data).getValue(), 0.000001);
	}
}
