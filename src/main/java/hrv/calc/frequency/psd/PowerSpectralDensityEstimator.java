package hrv.calc.frequency.psd;

import hrv.RRData;

public interface PowerSpectralDensityEstimator {

	PowerSpectrum calculateEstimate(RRData rr);
}
