package hrv.lib.hrv;

import hrv.lib.units.TimeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RRDataTest {

	@Test
	void testCreation() {
		
		double[] intervalData = new double[] {1.0, 1.0, 2.5, 1.0};
		RRData result = RRData.createFromRRInterval(intervalData, TimeUnit.SECOND);
		
		assertEquals(0.0, result.getTimeAxis()[0], 0.00001);
		assertEquals(1.0, result.getTimeAxis()[1], 0.00001);
		assertEquals(2.0, result.getTimeAxis()[2], 0.00001);
		assertEquals(4.5, result.getTimeAxis()[3], 0.00001);
	}
}
