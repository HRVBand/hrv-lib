package hrv.calc.manipulator;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import units.TimeUnitConverter.TimeUnit;

public class HRVCutDataManipulatorTest {

	@Test
	public void cutTest() {
		RRData data = RRData.createFromRRInterval(new double[] { 1.0, 1.1, 1.2, 0.9, 1.0 }, TimeUnit.SECOND);
		
		HRVCutDataManipulator cutter = new HRVCutDataManipulator(3);
		RRData cuttedData = cutter.manipulate(data);
		
		assertEquals(3, cuttedData.getTimeAxis().length);
	}
}
