package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.RRData;
import hrv.lib.units.TimeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SDSDCalculatorTest {

	@Test
	public void testForZeroCalculation() {
		double[] valueData = new double[] { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0 };
		RRData data = new RRData(null, TimeUnit.SECOND, valueData, TimeUnit.SECOND);
		
		SDSDCalculator calc = new SDSDCalculator();
		assertEquals(0.0, calc.process(data).getValue(), 0.00001);
	}
	
	@Test
	public void testWikiSampleData() {
		//Vales needed after subtraction: 10.0, 9.0, 13.0, 15.0, 16.0
		//Sample Source: https://de.wikipedia.org/wiki/Empirische_Standardabweichung
		double[] valueData = new double[] { 100, 90, 81, 68, 53, 37 };
		RRData data = new RRData(null, TimeUnit.SECOND, valueData, TimeUnit.SECOND);
		
		SDSDCalculator calc = new SDSDCalculator();
		assertEquals(3.05	, calc.process(data).getValue(), 0.01);
	}
}
