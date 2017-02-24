package hrv.calc.continous;

import hrv.HRVParameter;

@FunctionalInterface
public interface HRVParameterChangedListener {

	public void parameterChanged(HRVParameter param);
}
