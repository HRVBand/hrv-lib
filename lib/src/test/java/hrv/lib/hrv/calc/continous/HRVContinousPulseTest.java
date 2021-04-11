package hrv.lib.hrv.calc.continous;

import hrv.lib.hrv.RRData;
import hrv.lib.hrv.calc.parameter.HRVParameter;
import hrv.lib.units.TimeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HRVContinousPulseTest {

	@Test
	public void testPulse() {
		RRData data = RRData.createFromRRInterval(new double[] { 1.0, 1.0, 1.0, 1.0 }, TimeUnit.SECOND);

		HRVContinousHeartRate pulseCalc = new HRVContinousHeartRate(4);
		assertEquals(60.0, pulseCalc.process(data).getValue(), 0.00001);

		RRData data2 = RRData.createFromRRInterval(new double[] { 2.0, 2.0, 2.0, 2.0, 2.0, 2.0 }, TimeUnit.SECOND);

		HRVContinousHeartRate pulseCalc2 = new HRVContinousHeartRate(6);
		assertEquals(30.0, pulseCalc2.process(data2).getValue(), 0.00001);
	}

	@Test
	public void testContinuity() {
		HRVContinousHeartRate pulseCalc = new HRVContinousHeartRate(10);

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
