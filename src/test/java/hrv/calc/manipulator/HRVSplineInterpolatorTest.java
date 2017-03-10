package hrv.calc.manipulator;

import org.junit.Test;

import hrv.RRData;
import units.TimeUnit;

public class HRVSplineInterpolatorTest {

	@Test(expected=IllegalArgumentException.class)
	public void testTooShortData() {
		
		RRData data = RRData.createFromRRInterval(new double[] {1.0,0.9}, TimeUnit.SECOND);
		
		HRVSplineInterpolator interpolator = new HRVSplineInterpolator(4);
		interpolator.manipulate(data);
	}
}
