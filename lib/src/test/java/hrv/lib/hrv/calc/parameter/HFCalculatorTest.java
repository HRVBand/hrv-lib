package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.RRData;
import hrv.lib.hrv.calc.psd.PowerSpectrum;
import hrv.lib.hrv.calc.psd.StandardPowerSpectralDensityEstimator;
import hrv.lib.units.TimeUnit;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class HFCalculatorTest {

	@Test
	void testCalc() {
		double[] data = new double[16];
		Arrays.fill(data, 1);
		
		StandardPowerSpectralDensityEstimator est = new StandardPowerSpectralDensityEstimator();
		PowerSpectrum ps = est.calculateEstimate(RRData.createFromRRInterval(data, TimeUnit.SECOND));
		HFCalculator hfCalc = new HFCalculator();
		HRVParameter result = hfCalc.process(ps);
		
		//assertEquals(1.0 * (0.4 - 0.15), result.getValue(), 0.01);
	}
}
