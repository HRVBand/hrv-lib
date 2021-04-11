package hrv.lib.hrv.calc.psd;

import hrv.lib.hrv.calc.parameter.HRVParameter;
import hrv.lib.hrv.calc.parameter.HRVParameterEnum;
import hrv.lib.hrv.calc.parameter.HRVPowerSpectrumProcessor;
import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator;

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
