package hrv.calc.parameter;

import hrv.calc.psd.PowerSpectrum;
import hrv.calc.psd.PowerSpectrumIntegralCalculator;

public class HFCalculator implements HRVPowerSpectrumProcessor {

	private double hfLowerBound = 0.15;
	private double hfUpperBound = 0.4;
	
	@Override
	public HRVParameter process(PowerSpectrum ps) {

		PowerSpectrumIntegralCalculator calcLF = new PowerSpectrumIntegralCalculator(hfLowerBound, hfUpperBound);
		HRVParameter result = calcLF.process(ps);
		return new HRVParameter(HRVParameterEnum.LF, result.getValue() * 1000000, result.getUnit());
	}
}
