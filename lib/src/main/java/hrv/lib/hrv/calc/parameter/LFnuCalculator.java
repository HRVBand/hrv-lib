package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.calc.psd.PowerSpectrum;
import hrv.lib.hrv.calc.psd.PowerSpectrumIntegralCalculator;

/**
 * Calculates the normalized unit of the spectral lf indices. Like defined in
 * https://www.ncbi.nlm.nih.gov/pmc/articles/PMC1978375/
 * 
 * @author Julian
 *
 */
public class LFnuCalculator implements HRVPowerSpectrumProcessor {
	
	private final double lfLowerLimit;
	private final double lfUpperLimit;
	private final double hfLowerLimit;
	private final double hfUpperLimit;
	
	public LFnuCalculator(double lfLowerLimit, double lfUpperLimit, double hfLowerLimit, double hfUpperLimit) {
		this.lfLowerLimit = lfLowerLimit;
		this.lfUpperLimit = lfUpperLimit;
		this.hfLowerLimit = hfLowerLimit;
		this.hfUpperLimit = hfUpperLimit;
	}
	@Override
	public HRVParameter process(PowerSpectrum ps) {
		PowerSpectrumIntegralCalculator calcLf = new PowerSpectrumIntegralCalculator(lfLowerLimit, lfUpperLimit);
		double lf = calcLf.process(ps).getValue();
		
		PowerSpectrumIntegralCalculator calcHf = new PowerSpectrumIntegralCalculator(hfLowerLimit, hfUpperLimit);
		double hf = calcHf.process(ps).getValue();
		
		return new HRVParameter(HRVParameterEnum.LFNU, lf / (lf + hf), "");
	}

}
