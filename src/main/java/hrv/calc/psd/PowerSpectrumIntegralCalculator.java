package hrv.calc.psd;

import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator;

import hrv.calc.parameter.HRVParameter;
import hrv.calc.parameter.HRVParameterEnum;
import hrv.calc.parameter.HRVPowerSpectrumProcessor;

public class PowerSpectrumIntegralCalculator implements HRVPowerSpectrumProcessor {

	private double lowerIntegrationLimit;
	private double upperIntegrationLimit;

	public PowerSpectrumIntegralCalculator(double lowerIntegrationLimit, double upperIntegrationLimit) {
		this.lowerIntegrationLimit = lowerIntegrationLimit;
		this.upperIntegrationLimit = upperIntegrationLimit;
	}

	@Override
	public HRVParameter process(PowerSpectrum ps) {
		PowerSpectrumUnivariateFunctionAdapter psAdapter = new PowerSpectrumUnivariateFunctionAdapter(ps);
		TrapezoidIntegrator integrator = new TrapezoidIntegrator();
		return new HRVParameter(HRVParameterEnum.NON, integrator.integrate(TrapezoidIntegrator.DEFAULT_MAX_ITERATIONS_COUNT, psAdapter, lowerIntegrationLimit,
				upperIntegrationLimit), ps.getUnit() + "*" + ps.getUnit());
	}
}
