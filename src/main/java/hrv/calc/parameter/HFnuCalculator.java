package hrv.calc.parameter;

import hrv.calc.psd.PowerSpectrum;
import hrv.calc.psd.PowerSpectrumIntegralCalculator;

/**
 * Calculates the normalized unit of the spectral hf indice. Like defined in
 * https://www.ncbi.nlm.nih.gov/pmc/articles/PMC1978375/
 * 
 * @author Julian
 *
 */
public class HFnuCalculator implements HRVPowerSpectrumProcessor {

	double lfLowerLimit;
	double lfUpperLimit;
	double hfLowerLimit;
	double hfUpperLimit;

	public HFnuCalculator(double lfLowerLimit, double lfUpperLimit, double hfLowerLimit, double hfUpperLimit) {
		this.lfLowerLimit = lfLowerLimit;
		this.lfUpperLimit = lfUpperLimit;
		this.hfLowerLimit = hfLowerLimit;
		this.hfUpperLimit = hfUpperLimit;
	}

	@Override
	public HRVParameter process(PowerSpectrum ps) {

		PowerSpectrumIntegralCalculator calcHf = new PowerSpectrumIntegralCalculator(hfLowerLimit, hfUpperLimit);
		double hf = calcHf.process(ps).getValue();

		PowerSpectrumIntegralCalculator calcLf = new PowerSpectrumIntegralCalculator(lfLowerLimit, lfUpperLimit);
		double lf = calcLf.process(ps).getValue();

		return new HRVParameter(HRVParameterEnum.HFNU, hf / (lf + hf), "");
	}

}
