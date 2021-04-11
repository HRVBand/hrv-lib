package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.calc.psd.PowerSpectrum;

@FunctionalInterface
public interface HRVPowerSpectrumProcessor {

	public HRVParameter process(PowerSpectrum ps);
}
