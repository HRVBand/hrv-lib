package hrv.calc.frequency;

import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator;

import hrv.calc.frequency.psd.PowerSpectrum;
import hrv.calc.frequency.psd.PowerSpectrumUnivariateFunctionAdapter;

public class PowerSpectrumIntegralCalculator implements HRVPowerSpectrumProcessor {

	private double lowerIntegrationLimit;
	private double upperIntegrationLimit;

	public PowerSpectrumIntegralCalculator(double lowerIntegrationLimit, double upperIntegrationLimit) {
		this.lowerIntegrationLimit = lowerIntegrationLimit;
		this.upperIntegrationLimit = upperIntegrationLimit;
	}

	@Override
	public double process(PowerSpectrum ps) {
		PowerSpectrumUnivariateFunctionAdapter psAdapter = new PowerSpectrumUnivariateFunctionAdapter(ps);
		TrapezoidIntegrator integrator = new TrapezoidIntegrator();
		return integrator.integrate(TrapezoidIntegrator.DEFAULT_MAX_ITERATIONS_COUNT, psAdapter, lowerIntegrationLimit,
				upperIntegrationLimit);
	}
}
