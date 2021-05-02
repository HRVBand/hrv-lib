package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.calc.psd.PowerSpectrum;
import hrv.lib.hrv.calc.psd.PowerSpectrumIntegralCalculator;

public class HFCalculator implements HRVPowerSpectrumProcessor {

    private static final double HF_LOWER_BOUND = 0.15;
    private static final double HF_UPPER_BOUND = 0.4;

    @Override
    public HRVParameter process(PowerSpectrum ps) {

        var calcLF = new PowerSpectrumIntegralCalculator(HF_LOWER_BOUND, HF_UPPER_BOUND);
        HRVParameter result = calcLF.process(ps);
        return new HRVParameter(HRVParameterEnum.HF, result.getValue() * 1000000, result.getUnit());
    }
}
