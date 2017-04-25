package hrv.calc.parameter;

import hrv.RRData;
import hrv.calc.psd.PowerSpectrum;

@FunctionalInterface
public interface HRVPowerSpectrumProcessor {

	public HRVParameter process(PowerSpectrum ps);
}
