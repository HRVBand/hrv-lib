package hrv.calc.parameter;

import hrv.calc.psd.PowerSpectrum;
import hrv.calc.psd.PowerSpectrumIntegralCalculator;

public class VLFCalculator implements HRVPowerSpectrumProcessor {

	private double vlfLowerBound = 0.00;
	private double vlfUpperBound = 0.04;
	
	@Override
	public HRVParameter process(PowerSpectrum ps) {
		PowerSpectrumIntegralCalculator calcVLF = new PowerSpectrumIntegralCalculator(vlfLowerBound, vlfUpperBound);
		HRVParameter result = calcVLF.process(ps);
		return new HRVParameter(HRVParameterEnum.VLF, result.getValue() * 1000000, result.getUnit());
	}

}
