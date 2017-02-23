package hrv.calc.continous;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import units.TimeUnitConverter.TimeUnit;

public class HRVContinousPulseTest {

	@Test
	public void testPulse() {
		RRData data = RRData.createFromRRInterval(new double[] {1.0, 1.0, 1.0, 1.0}, TimeUnit.SECOND);
		
		HRVContinousPulse pulseCalc = new HRVContinousPulse(4);
		assertEquals(60.0, pulseCalc.process(data).getValue(), 0.00001);
		
		RRData data2 = RRData.createFromRRInterval(new double[] { 2.0, 2.0, 2.0, 2.0, 2.0, 2.0 }, TimeUnit.SECOND);
		
		HRVContinousPulse pulseCalc2 = new HRVContinousPulse(6);
		assertEquals(30.0, pulseCalc2.process(data2).getValue(), 0.00001);
	}
}
