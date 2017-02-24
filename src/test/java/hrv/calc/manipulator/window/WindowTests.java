package hrv.calc.manipulator.window;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import units.TimeUnitConverter.TimeUnit;

public class WindowTests {

	@Test
	public void testNoWindow() {
		double[] time1 = new double[] { 0.0, 1.0, 2.0, 4.0 };
		double[] values1 = new double[] { 1.0, 1.1, 1.2, 0.9 };
		RRData data = new RRData(time1, TimeUnit.SECOND, values1, TimeUnit.SECOND);
		
		NoWindow wnd = new NoWindow();
		data = wnd.manipulate(data);
		
		assertEquals(1.0, data.getValueAxis()[0], 0.000001);
		assertEquals(1.1, data.getValueAxis()[1], 0.000001);
		assertEquals(1.2, data.getValueAxis()[2], 0.000001);
		assertEquals(0.9, data.getValueAxis()[3], 0.000001);
	}
	
	@Test
	public void testHammingWindow() {

		double[] time1 = new double[] { 0.0, 1.0, 2.0, 4.0 };
		double[] values1 = new double[] { 1.0, 1.0, 1.0, 1.0 };
		RRData data = new RRData(time1, TimeUnit.SECOND, values1, TimeUnit.SECOND);
		
		HammingWindow wnd = new HammingWindow();
		data = wnd.manipulate(data);
		
		assertEquals(0.08, data.getValueAxis()[0], 0.00001);
		assertEquals(0.77, data.getValueAxis()[1], 0.00001);
		assertEquals(0.77, data.getValueAxis()[2], 0.00001);
		assertEquals(0.08, data.getValueAxis()[3], 0.00001);
	}
}
