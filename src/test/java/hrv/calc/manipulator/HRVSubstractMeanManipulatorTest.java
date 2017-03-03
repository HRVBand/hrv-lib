package hrv.calc.manipulator;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import hrv.calc.manipulator.HRVSubstractMeanManipulator;
import units.TimeUnit;

public class HRVSubstractMeanManipulatorTest {

	@Test
	public void testSub() {
		double[] time1 = new double[] { 0.0, 1.0, 2.0, 4.0 };
		double[] values1 = new double[] { 0.0, 1.0, 2.0, 4.0 };
		RRData data = new RRData(time1, TimeUnit.SECOND, values1, TimeUnit.SECOND);		
		
		HRVSubstractMeanManipulator mani = new HRVSubstractMeanManipulator();
		data = mani.manipulate(data);
		
		assertEquals(-7.0/4.0, data.getValueAxis()[0], 0.000001);
	}
}
