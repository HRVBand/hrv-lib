package hrv.calc.statistical;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import hrv.calc.parameter.NN50Calculator;
import units.TimeUnitConverter.TimeUnit;

public class NN50CalculatorTest {

	@Test
	public void testCalculationSeconds() {
		double[] valueData = new double[] { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0 };
		RRData data = new RRData(null, TimeUnit.SECOND, valueData, TimeUnit.SECOND);
		
		NN50Calculator calc = new NN50Calculator();
		assertEquals(7.0, calc.process(data).getValue(), 0.00001);
	}
	
	@Test
	public void testCalculationMilliseconds() {
		double[] valueData = new double[] { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 70.0 };
		RRData data = new RRData(null, TimeUnit.MILLISECOND, valueData, TimeUnit.MILLISECOND);
		
		NN50Calculator calc = new NN50Calculator();
		assertEquals(1.0, calc.process(data).getValue(), 0.00001);
	}
}
