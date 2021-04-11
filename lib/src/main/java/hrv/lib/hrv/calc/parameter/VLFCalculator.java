package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.calc.psd.PowerSpectrum;
import hrv.lib.hrv.calc.psd.PowerSpectrumIntegralCalculator;

public class VLFCalculator implements HRVPowerSpectrumProcessor {

	private static final double VLF_LOWER_BOUND = 0.00;
	private static final double VLF_UPPER_BOUND = 0.04;
	
	@Override
	public HRVParameter process(PowerSpectrum ps) {
		PowerSpectrumIntegralCalculator calcVLF = new PowerSpectrumIntegralCalculator(VLF_LOWER_BOUND, VLF_UPPER_BOUND);
		HRVParameter result = calcVLF.process(ps);
		return new HRVParameter(HRVParameterEnum.VLF, result.getValue() * 1000000, result.getUnit());
	}

}
