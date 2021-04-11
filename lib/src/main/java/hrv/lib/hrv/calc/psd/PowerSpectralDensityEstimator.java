package hrv.lib.hrv.calc.psd;

import hrv.lib.hrv.RRData;

@FunctionalInterface
public interface PowerSpectralDensityEstimator {

	PowerSpectrum calculateEstimate(RRData rr);
}
