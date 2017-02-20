package hrv.calc.frequency.psd;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import common.MathUtils;
import hrv.RRData;
import hrv.calc.frequency.AvgSampleSizeCalculator;
import hrv.calc.manipulator.HRVCutToPowerTwoDataManipulator;
import hrv.calc.manipulator.HRVDataManipulator;
import hrv.calc.manipulator.HRVSubstractMeanManipulator;
import hrv.calc.manipulator.HRVZeroPadToPowerOfTwoManipulator;
import hrv.calc.manipulator.window.NoWindow;

/**
 * Uses FFT to estimate the power spectra density of RR-Data-Intervals
 * 
 * @author Julian
 *
 */
public class StandardPowerSpectralDensityEstimator implements PowerSpectralDensityEstimator {

	/**
	 * Specifies whether the data is padded with zeros or if it is cut.
	 */
	private boolean doZeroPadding = false;

	/**
	 * Specifies the window function to apply before the FFT.
	 */
	private HRVDataManipulator windowFunction = new NoWindow();

	/**
	 * Creates a New PSD-Calculator Object to calculate the PSD of a sequence.
	 * 
	 * @param samplingRate
	 *            Sampling rate for the sampling of the interpolated data.
	 */
	public StandardPowerSpectralDensityEstimator() {
	}

	/**
	 * If enabled = true, zero padding is used before FFT otherwise the given
	 * data is cut to a number that is a power of two.
	 * 
	 * @param enabled
	 *            Enables zero padding.
	 */
	public void setZeroPadding(boolean enabled) {
		doZeroPadding = enabled;
	}

	/**
	 * Sets the window function that is applied to the given data before the
	 * calculation.
	 * 
	 * @param windowFunction
	 */
	public void setWindowFunction(HRVDataManipulator windowFunction) {
		this.windowFunction = windowFunction;
	}

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
			
/*		if (doZeroPadding) {
			HRVZeroPadToPowerOfTwoManipulator mani = new HRVZeroPadToPowerOfTwoManipulator();
			mani.manipulate(rr);
		} else {
			HRVCutToPowerTwoDataManipulator mani = new HRVCutToPowerTwoDataManipulator();
			mani.manipulate(rr);
		}
		
		HRVSubstractMeanManipulator meanMani = new HRVSubstractMeanManipulator();
		meanMani.manipulate(rr);
		
		windowFunction.manipulate(rr);*/

		double[] power = calculatePower(rr);
		double[] frequencies = calculateFrequencies(rr);

		
		return new PowerSpectrum(power, frequencies);
	}
	
	private double[] calculatePower(RRData rr) {
		double[] rrY = rr.getValueAxis();
		AvgSampleSizeCalculator calc = new AvgSampleSizeCalculator();
		double avgSampleSize = calc.process(rr);
		
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
		double sampleFrequency = 1 / calc.process(rr);
		double maxAbtastFrequenz = 0.5 * sampleFrequency;

		// Calculate Frequencies to corresponding power values
		// Frequency Steps in Hz
		double frequencySteps = (2 * maxAbtastFrequenz) / rr.getTimeAxis().length;
		double[] frequencies = new double[rr.getTimeAxis().length];

		for (int i = 0; i < frequencies.length; i++) {
			frequencies[i] = (frequencySteps) * i;
		}
		
		return frequencies;
	}
}
