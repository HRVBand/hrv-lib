package hrv.calc.parameter;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import hrv.RRData;

public class SD2Calculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		SDSDCalculator sdsdCalc = new SDSDCalculator();
		double sdsd = sdsdCalc.process(data).getValue();
		StandardDeviation sdnnCalc = new StandardDeviation();
		double sdnn = sdnnCalc.evaluate(data.getValueAxis());
		double val = 2 * sdsd * sdsd - 0.5 * sdnn * sdnn;
		return new HRVParameter(HRVParameterEnum.SD1SD2, Math.sqrt(val), "non");
	}

}
