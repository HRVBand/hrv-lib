package hrv.calc.frequency;

import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator;

import hrv.calc.frequency.psd.PowerSpectrum;
import hrv.calc.frequency.psd.PowerSpectrumUnivariateFunctionAdapter;

public class LFCalculator {

	public double calculate(PowerSpectrum ps) {
		PowerSpectrumUnivariateFunctionAdapter psAdapter = new PowerSpectrumUnivariateFunctionAdapter(ps);
		TrapezoidIntegrator integrator = new TrapezoidIntegrator();
		double integral = integrator.integrate(TrapezoidIntegrator.DEFAULT_MAX_ITERATIONS_COUNT, psAdapter, 0.04, 0.15); 
		return integral;
	}
}
