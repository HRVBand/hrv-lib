package hrv.calc.parameter;

import hrv.calc.psd.PowerSpectrum;
import hrv.calc.psd.PowerSpectrumIntegralCalculator;

public class LFnuCalculator implements HRVPowerSpectrumProcessor {
	
	double lfLowerLimit;
	double lfUpperLimit;
	double hfLowerLimit;
	double hfUpperLimit;
	
	public LFnuCalculator(double lfLowerLimit, double lfUpperLimit, double hfLowerLimit, double hfUpperLimit) {
		this.lfLowerLimit = lfLowerLimit;
		this.lfUpperLimit = lfUpperLimit;
		this.hfLowerLimit = hfLowerLimit;
		this.hfUpperLimit = hfUpperLimit;
	}
	@Override
	public double process(PowerSpectrum ps) {
		PowerSpectrumIntegralCalculator calcLf = new PowerSpectrumIntegralCalculator(lfLowerLimit, lfUpperLimit);
		double lf = calcLf.process(ps);
		
		PowerSpectrumIntegralCalculator calcHf = new PowerSpectrumIntegralCalculator(hfLowerLimit, hfUpperLimit);
		double hf = calcHf.process(ps);
		
		return lf / (lf + hf);
	}

}
