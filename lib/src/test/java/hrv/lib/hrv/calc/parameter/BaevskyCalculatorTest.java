package hrv.calc.parameter;

import hrv.lib.hrv.RRData;
import hrv.lib.hrv.calc.parameter.BaevskyCalculator;
import hrv.lib.units.TimeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaevskyCalculatorTest {

	@Test
	public void testCalculation() {
		double[] valueData = new double[] { 0.0, 1.0, 2.0, 3.0, 4.0, 4.1, 5.0, 5.01, 5.02, 6.0, 7.0 };
		RRData data = new RRData(null, TimeUnit.SECOND, valueData, TimeUnit.SECOND);
		
		BaevskyCalculator calc = new BaevskyCalculator();
		assertEquals(0.0765306122, calc.process(data).getValue(), 0.000001);
	}
}
