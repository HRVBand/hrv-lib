package hrv.calc.frequency.psd;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import hrv.calc.psd.PowerSpectrum;
import hrv.calc.psd.StandardPowerSpectralDensityEstimator;
import units.TimeUnitConverter.TimeUnit;

public class PSDEstimatorTest {

	@Test
	public void testStandardPSD() {
		double sinHz = 1; //Frequency of the sin function
		int sampleFrequency = 8; //Sample Frequency in Hz
		double xLength = 2; //Length of the data.
		double[] sinY = generateSinArray(xLength, sampleFrequency, sinHz);
		
		//Generate X-Axis
		double[] sinX = new double[sinY.length];
		for(int i = 0; i < sinX.length; i++) {
			sinX[i] = i * (1.0 / sampleFrequency);
		}
		
		RRData rr = new RRData(sinX, TimeUnit.SECOND, sinY, TimeUnit.SECOND);
		StandardPowerSpectralDensityEstimator psd = new StandardPowerSpectralDensityEstimator();
		PowerSpectrum ps = psd.calculateEstimate(rr);
		
		double[] freqResult = ps.getFrequency();
		double[] powerResult = ps.getPower();
		
		assertEquals(16, freqResult.length);
		assertEquals(0.0, freqResult[0], 0.0001);
		assertEquals(1.0, freqResult[2], 0.0001);
		
		assertEquals(16, powerResult.length);
		assertEquals(0.0, powerResult[0], 0.001);
		assertEquals(1.0, powerResult[2], 0.001);
		assertEquals(1.0, powerResult[14], 0.001);
	}
	
	private double[] generateSinArray(double xMax, int sampleFrequency, double sinHz) {
		double[] sin = new double[(int)xMax * sampleFrequency];
		
		for(int i = 0; i < sin.length; i++) {
			sin[i] = Math.sin(2 * Math.PI * sinHz * i * (1.0 / sampleFrequency));
		}
		
		return sin;
	}
}
