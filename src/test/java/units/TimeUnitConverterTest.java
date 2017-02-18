package units;

import org.junit.Test;

import units.TimeUnitConverter.TimeUnit;

import static org.junit.Assert.*;

public class TimeUnitConverterTest {

	@Test
	public void testSingelValues() {
		TimeUnitConverter converter = new TimeUnitConverter();
		
		double result1 = converter.convert(12.0, TimeUnit.SECOND, TimeUnit.MILLISECOND);
		assertEquals(12000, result1, 0.000001);
		
		double result2 = converter.convert(1.0, TimeUnit.DAY, TimeUnit.HOUR);
		assertEquals(24, result2, 0.000001);

		double result3 = converter.convert(1.5, TimeUnit.HOUR, TimeUnit.MINUTE);
		assertEquals(90, result3, 0.000001);
		
		double result4 = converter.convert(1001.0, TimeUnit.MILLISECOND, TimeUnit.SECOND);
		assertEquals(1.001, result4, 0.000001);
	}
	
	@Test
	public void testArray() {
		TimeUnitConverter converter = new TimeUnitConverter();
		
		double[] data = new double[] {1.0,2.0,3.0};
		converter.convert(data, TimeUnit.SECOND, TimeUnit.MILLISECOND);
		assertEquals(1000.0, data[0], 0.000001);
		assertEquals(2000.0, data[1], 0.000001);
		assertEquals(3000.0, data[2], 0.000001);
		
		converter.convert(data, TimeUnit.MILLISECOND, TimeUnit.SECOND);
		assertEquals(1.0, data[0], 0.000001);
		assertEquals(2.0, data[1], 0.000001);
		assertEquals(3.0, data[2], 0.000001);
		
	}
}
