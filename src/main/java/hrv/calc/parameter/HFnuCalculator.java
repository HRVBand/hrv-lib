package hrv.calc.parameter;

import hrv.calc.psd.PowerSpectrum;
import hrv.calc.psd.PowerSpectrumIntegralCalculator;

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
	public double process(PowerSpectrum ps) {
		
		PowerSpectrumIntegralCalculator calcHf = new PowerSpectrumIntegralCalculator(hfLowerLimit, hfUpperLimit);
		double hf = calcHf.process(ps);
		
		PowerSpectrumIntegralCalculator calcLf = new PowerSpectrumIntegralCalculator(lfLowerLimit, lfUpperLimit);
		double lf = calcLf.process(ps);
		
		return hf / (lf + hf);
	}

}
