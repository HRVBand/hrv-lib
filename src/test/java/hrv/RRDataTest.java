package hrv;

import static org.junit.Assert.*;

import org.junit.Test;

import units.TimeUnit;

public class RRDataTest {

	@Test
	public void testCreation() {
		
		double[] intervalData = new double[] {1.0, 1.0, 2.5, 1.0};
		RRData result = RRData.createFromRRInterval(intervalData, TimeUnit.SECOND);
		
		assertEquals(result.getTimeAxis()[0], 0.0, 0.00001);
		assertEquals(result.getTimeAxis()[1], 1.0, 0.00001);
		assertEquals(result.getTimeAxis()[2], 2.0, 0.00001);
		assertEquals(result.getTimeAxis()[3], 4.5, 0.00001);
	}
	
	@Test
	public void testUnitConversion() {

		double[] intervalData = new double[] {1.0, 1.0,2.5,1.0};
		double[] timeData = new double[] {0.0, 1.0, 2.0, 4.5};
		RRData data = new RRData(timeData, TimeUnit.SECOND, intervalData, TimeUnit.SECOND);
		data.changeTimeAxisUnit(TimeUnit.MILLISECOND);
		data.changeValuesAxisUnit(TimeUnit.MILLISECOND);
		
		assertEquals(1000, data.getValueAxis()[0], 0.0001);
		assertEquals(2500, data.getValueAxis()[2], 0.0001);

		assertEquals(0.0, data.getTimeAxis()[0], 0.0001);
		assertEquals(2000, data.getTimeAxis()[2], 0.0001);
		
		assertEquals(TimeUnit.MILLISECOND, data.getTimeAxisUnit());
	}
}
