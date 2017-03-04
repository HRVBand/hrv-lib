package hrv.calc.parameter;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import hrv.RRData;

public class SD1Calculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		StandardDeviation sdnnCalc = new StandardDeviation();
		double sdnn = sdnnCalc.evaluate(data.getValueAxis());
		return new HRVParameter(HRVParameterEnum.SD1, Math.sqrt(0.5 * sdnn * sdnn), data.getValueAxisUnit().toString());
	}

}
