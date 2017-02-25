package hrv.calc.frequency.psd;

import hrv.RRData;

@FunctionalInterface
public interface PowerSpectralDensityEstimator {

	PowerSpectrum calculateEstimate(RRData rr);
}
