package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.calc.psd.PowerSpectrum;
import hrv.lib.hrv.calc.psd.PowerSpectrumIntegralCalculator;

public class LFCalculator implements HRVPowerSpectrumProcessor {

	private double lfLowerBound = 0.04;
	private double lfUpperBound = 0.15;
	
	@Override
	public HRVParameter process(PowerSpectrum ps) {
		PowerSpectrumIntegralCalculator calcLF = new PowerSpectrumIntegralCalculator(lfLowerBound, lfUpperBound);
		HRVParameter result = calcLF.process(ps);
		return new HRVParameter(HRVParameterEnum.LF, result.getValue() * 1000000, result.getUnit());
	}

}
