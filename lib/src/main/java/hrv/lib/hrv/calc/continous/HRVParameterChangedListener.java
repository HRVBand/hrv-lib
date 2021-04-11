package hrv.lib.hrv.calc.continous;

import hrv.lib.hrv.calc.parameter.HRVParameter;

@FunctionalInterface
public interface HRVParameterChangedListener {

	void parameterChanged(HRVParameter param);
}
