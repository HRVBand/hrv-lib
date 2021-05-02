package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.RRData;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

public class SD2Calculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		var sdsdCalc = new SDSDCalculator();
		double sdsd = sdsdCalc.process(data).getValue();
		var sdnnCalc = new StandardDeviation();
		double sdnn = sdnnCalc.evaluate(data.getValueAxis());
		double val = 2 * sdsd * sdsd - 0.5 * sdnn * sdnn;
		return new HRVParameter(HRVParameterEnum.SD2, Math.sqrt(val), data.getValueAxisUnit().toString());
	}

	@Override
	public boolean validData(RRData data) {
		return data.getValueAxis().length > 1;
	}
}
