package hrv.calc.continous;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import hrv.calc.parameter.HRVParameter;
import units.TimeUnitConverter.TimeUnit;

public class HRVContinousPulseTest {

	@Test
	public void testPulse() {
		RRData data = RRData.createFromRRInterval(new double[] { 1.0, 1.0, 1.0, 1.0 }, TimeUnit.SECOND);

		HRVContinousPulse pulseCalc = new HRVContinousPulse(4);
		assertEquals(60.0, pulseCalc.process(data).getValue(), 0.00001);

		RRData data2 = RRData.createFromRRInterval(new double[] { 2.0, 2.0, 2.0, 2.0, 2.0, 2.0 }, TimeUnit.SECOND);

		HRVContinousPulse pulseCalc2 = new HRVContinousPulse(6);
		assertEquals(30.0, pulseCalc2.process(data2).getValue(), 0.00001);
	}

	@Test
	public void testContinuity() {
		HRVContinousPulse pulseCalc = new HRVContinousPulse(10);

		HRVParameterChangedMockListener listener = new HRVParameterChangedMockListener();
		pulseCalc.addHRVParameterChangedListener(listener);

		HRVRRIntervalEvent event1 = new HRVRRIntervalEvent();
		event1.setRr(1.0);
		HRVRRIntervalEvent event2 = new HRVRRIntervalEvent();
		event2.setRr(1.0);
		HRVRRIntervalEvent event3 = new HRVRRIntervalEvent();
		event3.setRr(1.0);
		HRVRRIntervalEvent event4 = new HRVRRIntervalEvent();
		event4.setRr(1.0);

		pulseCalc.newRRInterval(event1);
		pulseCalc.newRRInterval(event2);
		pulseCalc.newRRInterval(event3);
		pulseCalc.newRRInterval(event4);

	}

	class HRVParameterChangedMockListener implements HRVParameterChangedListener {

		@Override
		public void parameterChanged(HRVParameter param) {

			assertEquals(60.0, param.getValue(), 0.0001);			
		}
	}

}
