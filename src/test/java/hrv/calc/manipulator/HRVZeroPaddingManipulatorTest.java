package hrv.calc.manipulator;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import hrv.calc.manipulator.HRVZeroPadToPowerOfTwoManipulator;
import units.TimeUnitConverter.TimeUnit;

public class HRVZeroPaddingManipulatorTest {

	@Test
	public void testPadding() {
		double[] time2 = new double[] { 0.0, 1.0, 2.0, 3.0, 4.0 };
		double[] values2 = new double[] { 1.0, 1.1, 1.2, 0.9, 1.0 };
		RRData data2 = new RRData(time2, TimeUnit.SECOND, values2, TimeUnit.SECOND);

		HRVZeroPadToPowerOfTwoManipulator mani = new HRVZeroPadToPowerOfTwoManipulator();
		mani.manipulate(data2);
		
		assertEquals(8, data2.getValueAxis().length);
		assertEquals(1.0, data2.getValueAxis()[0], 0.00001);
		assertEquals(1.0, data2.getValueAxis()[4], 0.00001);
		assertEquals(0.0, data2.getValueAxis()[5], 0.00001);

		assertEquals(8, data2.getTimeAxis().length);
		assertEquals(0.0, data2.getTimeAxis()[0], 0.00001);
		assertEquals(4.0, data2.getTimeAxis()[4], 0.00001);
		assertEquals(5.0, data2.getTimeAxis()[5], 0.00001);
		assertEquals(6.0, data2.getTimeAxis()[6], 0.00001);
		assertEquals(7.0, data2.getTimeAxis()[7], 0.00001);
	}
	
	@Test
	public void testNoPadding() {
		double[] time1 = new double[] { 0.0, 1.0, 2.0, 4.0 };
		double[] values1 = new double[] { 1.0, 1.1, 1.2, 0.9 };
		RRData data = new RRData(time1, TimeUnit.SECOND, values1, TimeUnit.SECOND);
		
		HRVZeroPadToPowerOfTwoManipulator mani = new HRVZeroPadToPowerOfTwoManipulator();
		mani.manipulate(data);
		
		assertEquals(4, data.getTimeAxis().length);
		assertEquals(4, data.getValueAxis().length);
	}
}
