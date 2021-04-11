package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.calc.psd.PowerSpectrum;
import hrv.lib.hrv.calc.psd.PowerSpectrumIntegralCalculator;

public class LFCalculator implements HRVPowerSpectrumProcessor {

	private static final double LF_LOWER_BOUND = 0.04;
	private static final double LF_UPPER_BOUND = 0.15;
	
	@Override
	public HRVParameter process(PowerSpectrum ps) {
		PowerSpectrumIntegralCalculator calcLF = new PowerSpectrumIntegralCalculator(LF_LOWER_BOUND, LF_UPPER_BOUND);
		HRVParameter result = calcLF.process(ps);
		return new HRVParameter(HRVParameterEnum.LF, result.getValue() * 1000000, result.getUnit());
	}

}
