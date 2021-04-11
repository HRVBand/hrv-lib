package hrv.lib.hrv.calc.manipulator;

import hrv.lib.hrv.RRData;
import hrv.lib.units.TimeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HRVSubtractMeanManipulatorTest {

	@Test
	void testSub() {
		double[] time1 = new double[] { 0.0, 1.0, 2.0, 4.0 };
		double[] values1 = new double[] { 0.0, 1.0, 2.0, 4.0 };
		RRData data = new RRData(time1, TimeUnit.SECOND, values1, TimeUnit.SECOND);
		
		HRVSubtractMeanManipulator mani = new HRVSubtractMeanManipulator();
		data = mani.manipulate(data);
		
		assertEquals(-7.0/4.0, data.getValueAxis()[0], 0.000001);
	}
}
