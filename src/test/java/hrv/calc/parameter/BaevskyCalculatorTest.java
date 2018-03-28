package hrv.calc.parameter;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import hrv.calc.parameter.BaevskyCalculator;
import units.TimeUnit;

public class BaevskyCalculatorTest {

	@Test
	public void testCalculation() {
		double[] valueData = new double[] { 0.0, 1.0, 2.0, 3.0, 4.0, 4.1, 5.0, 5.01, 5.02, 6.0, 7.0 };
		RRData data = new RRData(null, TimeUnit.SECOND, valueData, TimeUnit.SECOND);
		
		BaevskyCalculator calc = new BaevskyCalculator();
		assertEquals(0.0765306122, calc.process(data).getValue(), 0.000001);
	}
}
