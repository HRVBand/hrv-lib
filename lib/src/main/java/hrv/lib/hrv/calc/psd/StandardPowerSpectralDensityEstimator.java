package hrv.lib.hrv.calc.psd;

import hrv.lib.hrv.RRData;
import hrv.lib.hrv.calc.parameter.AvgSampleSizeCalculator;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

/**
 * Uses FFT to estimate the power spectra density of RR-Data-Intervals
 * 
 * @author Julian
 *
 */
public class StandardPowerSpectralDensityEstimator implements PowerSpectralDensityEstimator {

	/**
	 * Calculates the power spectrum density (PSD) of the given
	 * rr-interval-data. The sampling rate is used to sample the interpolated
	 * rr-interval-data.
	 * 
	 * @param rr
	 *            rr-interval-data in seconds
	 * @return power spectrum density (PSD) of RR-interval-data (unit is s squared)
	 */
	@Override
	public PowerSpectrum calculateEstimate(RRData rr) {
		double[] power = calculatePower(rr);
		double[] frequencies = calculateFrequencies(rr);
		
		return new PowerSpectrum(power, frequencies);
	}
	
	private double[] calculatePower(RRData rr) {
		double[] rrY = rr.getValueAxis();
		AvgSampleSizeCalculator calc = new AvgSampleSizeCalculator();
		double avgSampleSize = calc.process(rr).getValue();
		
		final FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
		final Complex[] fftres = fft.transform(rrY, TransformType.FORWARD);

		// Calculate the power spectral density of the Fourier Transform
		double[] betrag = new double[rrY.length];
		for (int i = 0; i < rrY.length; i++) {
			//Calculate power spectral density
			//Unit: s*s / Hz = s*s*s
			double abs = fftres[i].abs(); 
			betrag[i] =  abs * abs / (rrY.length * (1 / avgSampleSize) * 0.5); 
		}
		
		return betrag;
	}
	
	private double[] calculateFrequencies(RRData rr) {
		AvgSampleSizeCalculator calc = new AvgSampleSizeCalculator();
		double sampleFrequency = 1 / calc.process(rr).getValue();
		double maxAbtastFrequenz = 0.5 * sampleFrequency;

		// Calculate Frequencies to corresponding power values
		// Frequency Steps in Hz
		double frequencySteps = (2 * maxAbtastFrequenz) / (rr.getTimeAxis().length);
		double[] frequencies = new double[rr.getTimeAxis().length];

		for (int i = 0; i < frequencies.length; i++) {
			frequencies[i] = (frequencySteps) * i;
		}
		
		return frequencies;
	}
}
