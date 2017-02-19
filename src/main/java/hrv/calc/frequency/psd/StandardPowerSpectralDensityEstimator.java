package hrv.calc.frequency.psd;

import common.MathUtils;
import hrv.RRData;
import hrv.calc.manipulator.HRVCutToPowerTwoDataManipulator;
import hrv.calc.manipulator.HRVSubstractMeanManipulator;
import hrv.calc.manipulator.HRVZeroPadToPowerOfTwoManipulator;

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
	//private Window windowFunction = new NoWindow();

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
	//public void setWindowFunction(Window windowFunction) {
	//	this.windowFunction = windowFunction;
	//}

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
			
		if (doZeroPadding) {
			HRVZeroPadToPowerOfTwoManipulator mani = new HRVZeroPadToPowerOfTwoManipulator();
			mani.manipulate(rr);
		} else {
			HRVCutToPowerTwoDataManipulator mani = new HRVCutToPowerTwoDataManipulator();
			mani.manipulate(rr);
		}
		
		//double[] rrX = rr.getTimeAxis();
		//double[] rrY = rr.getValueAxis();
		//double sampleFrequency = rr.getAvgSampleFrequency();
		

		HRVSubstractMeanManipulator meanMani = new HRVSubstractMeanManipulator();
		meanMani.manipulate(rr);
		
		/*
		// Apply window
		windowFunction.applyWindow(rr);

		// FFT of the sampled function
		final FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
		final Complex[] fftres = fft.transform(rrY, TransformType.FORWARD);

		// Calculate the power spectral density of the Fourier Transform
		double[] betrag = new double[rrX.length];
		for (int i = 0; i < rrX.length; i++) {
			//Calculate power spectral density
			//Unit: s*s / Hz = s*s*s
			double abs = fftres[i].abs(); 
			betrag[i] =  abs * abs / (rr.getAvgSampleFrequency()); 
		}

		double maxAbtastFrequenz = 0.5 * sampleFrequency;

		// Calculate Frequencies to corresponding power values
		// Frequency Steps in Hz
		double frequencySteps = (2 * maxAbtastFrequenz) / rrX.length;
		double[] frequencies = new double[rrX.length];

		for (int i = 0; i < frequencies.length; i++) {
			frequencies[i] = (frequencySteps) * i;
		}

		return new PowerSpectrum(betrag, frequencies);*/
		return null;
	}
}
