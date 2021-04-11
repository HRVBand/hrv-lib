package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.RRData;
import hrv.lib.units.TimeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MxDMnCalculatorTest {

	@Test
	void testCalculation() {
		double[] valueData = new double[] { 0.5, 1.0, 2.0, 3.0, 4.0, 4.1, 5.0, 5.01, 5.02, 6.0, 7.0 };
		RRData data = new RRData(null, TimeUnit.SECOND, valueData, TimeUnit.SECOND);
		
		MxDMnCalculator calc = new MxDMnCalculator();
		assertEquals(6.5, calc.process(data).getValue(), 0.0000001);
	}
}
