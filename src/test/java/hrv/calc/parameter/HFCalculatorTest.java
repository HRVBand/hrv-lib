package hrv.calc.parameter;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import hrv.calc.psd.PSDEstimatorTest;
import hrv.calc.psd.PowerSpectralDensityEstimator;
import hrv.calc.psd.PowerSpectrum;
import hrv.calc.psd.StandardPowerSpectralDensityEstimator;
import units.TimeUnit;

public class HFCalculatorTest {

	@Test
	public void testCalc() {
		double[] data = new double[16];
		for(int i = 0; i < data.length; i++) {
			data[i] = 1;
		}
		
		StandardPowerSpectralDensityEstimator est = new StandardPowerSpectralDensityEstimator();
		PowerSpectrum ps = est.calculateEstimate(RRData.createFromRRInterval(data, TimeUnit.SECOND));
		HFCalculator hfCalc = new HFCalculator();
		HRVParameter result = hfCalc.process(ps);
		
		//assertEquals(1.0 * (0.4 - 0.15), result.getValue(), 0.01);
	}
}
