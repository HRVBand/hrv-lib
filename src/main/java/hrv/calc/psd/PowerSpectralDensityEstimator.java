package hrv.calc.psd;

import hrv.RRData;

@FunctionalInterface
public interface PowerSpectralDensityEstimator {

	PowerSpectrum calculateEstimate(RRData rr);
}
