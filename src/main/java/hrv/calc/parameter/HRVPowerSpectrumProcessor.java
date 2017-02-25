package hrv.calc.frequency;

import hrv.calc.frequency.psd.PowerSpectrum;

@FunctionalInterface
public interface HRVPowerSpectrumProcessor {

	public double process(PowerSpectrum ps);
}
