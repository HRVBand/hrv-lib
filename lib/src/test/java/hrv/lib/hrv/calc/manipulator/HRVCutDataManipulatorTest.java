package hrv.lib.hrv.calc.manipulator;


import hrv.lib.hrv.RRData;
import hrv.lib.units.TimeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HRVCutDataManipulatorTest {

	@Test
	public void cutTest() {
		RRData data = RRData.createFromRRInterval(new double[] { 1.0, 1.1, 1.2, 0.9, 1.0 }, TimeUnit.SECOND);
		
		HRVCutDataManipulator cutter = new HRVCutDataManipulator(3);
		RRData cuttedData = cutter.manipulate(data);
		
		assertEquals(3, cuttedData.getTimeAxis().length);
	}
}
