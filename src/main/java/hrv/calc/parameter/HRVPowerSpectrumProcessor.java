package hrv.calc.parameter;

import hrv.calc.psd.PowerSpectrum;

@FunctionalInterface
public interface HRVPowerSpectrumProcessor {

	public double process(PowerSpectrum ps);
}
