package hrv.lib.hrv.calc.manipulator;

import hrv.lib.hrv.RRData;
import hrv.lib.units.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HRVSplineInterpolatorTest {

    @Test
    void testTooShortData() {

        RRData data = RRData.createFromRRInterval(new double[]{1.0, 0.9}, TimeUnit.SECOND);

        HRVSplineInterpolator interpolator = new HRVSplineInterpolator(4);

        Assertions.assertThrows(IllegalArgumentException.class, () -> interpolator.manipulate(data));
    }
}
