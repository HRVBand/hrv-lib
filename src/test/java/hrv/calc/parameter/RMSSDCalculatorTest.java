package hrv.calc.parameter;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import hrv.calc.parameter.RMSSDCalculator;
import units.TimeUnit;

public class RMSSDCalculatorTest {

	@Test
	public void testCalculation() {
		double[] valueData = new double[] { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0 };
		RRData data = new RRData(null, TimeUnit.SECOND, valueData, TimeUnit.SECOND);
		
		RMSSDCalculator calc = new RMSSDCalculator();
		assertEquals(1.0, calc.process(data).getValue(), 0.00001);
	}
}
