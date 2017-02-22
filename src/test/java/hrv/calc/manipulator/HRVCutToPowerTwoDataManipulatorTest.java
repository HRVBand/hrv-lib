package hrv.calc.manipulator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hrv.RRData;
import hrv.calc.manipulator.HRVCutToPowerTwoDataManipulator;
import units.TimeUnitConverter.TimeUnit;

public class HRVCutToPowerTwoDataManipulatorTest {

	@Test
	public void testCutWithSameNum() {
		double[] time1 = new double[] { 0.0, 1.0, 2.0, 4.0 };
		double[] values1 = new double[] { 1.0, 1.1, 1.2, 0.9 };
		RRData data = new RRData(time1, TimeUnit.SECOND, values1, TimeUnit.SECOND);
		
		HRVCutToPowerTwoDataManipulator mani = new HRVCutToPowerTwoDataManipulator();
		data = mani.manipulate(data);
		
		assertEquals(4, data.getTimeAxis().length);
		assertEquals(4, data.getValueAxis().length);
	}
	
	@Test
	public void testCut() {
		double[] time2 = new double[] { 0.0, 1.0, 2.0, 3.0, 4.0 };
		double[] values2 = new double[] { 1.0, 1.1, 1.2, 0.9, 1.0 };
		RRData data2 = new RRData(time2, TimeUnit.SECOND, values2, TimeUnit.SECOND);

		HRVCutToPowerTwoDataManipulator mani = new HRVCutToPowerTwoDataManipulator();
		data2 = mani.manipulate(data2);
		
		assertEquals(4, data2.getValueAxis().length);
		assertEquals(1.1, data2.getValueAxis()[0], 0.00001);
		assertEquals(1.0, data2.getValueAxis()[3], 0.00001);

		assertEquals(4, data2.getTimeAxis().length);
		assertEquals(1.0, data2.getTimeAxis()[0], 0.00001);
		assertEquals(4.0, data2.getTimeAxis()[3], 0.00001);
	}
}
