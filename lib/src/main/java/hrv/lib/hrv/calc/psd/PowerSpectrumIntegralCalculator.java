package hrv.lib.hrv.calc.psd;

import hrv.lib.hrv.calc.parameter.HRVParameter;
import hrv.lib.hrv.calc.parameter.HRVParameterEnum;
import hrv.lib.hrv.calc.parameter.HRVPowerSpectrumProcessor;
import org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator;
import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator;

public class PowerSpectrumIntegralCalculator implements HRVPowerSpectrumProcessor {

    private final double lowerIntegrationLimit;
    private final double upperIntegrationLimit;

    public PowerSpectrumIntegralCalculator(double lowerIntegrationLimit, double upperIntegrationLimit) {
        this.lowerIntegrationLimit = lowerIntegrationLimit;
        this.upperIntegrationLimit = upperIntegrationLimit;
    }

    @Override
    public HRVParameter process(PowerSpectrum ps) {
        var psAdapter = new PowerSpectrumUnivariateFunctionAdapter(ps);
        var integrator = new TrapezoidIntegrator();
        return new HRVParameter(HRVParameterEnum.NON,
                integrator.integrate(BaseAbstractUnivariateIntegrator.DEFAULT_MAX_ITERATIONS_COUNT,
                        psAdapter,
                        lowerIntegrationLimit,
                        upperIntegrationLimit),
                ps.getUnit() + "*" + ps.getUnit());
    }
}
