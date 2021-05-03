package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.RRData;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

public class SDSDCalculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		double[] rr = data.getValueAxis();
		var rrDiff = new double[rr.length - 1];

		for (var i = 0; i < rr.length - 1; i++) {
			rrDiff[i] = rr[i] - rr[i + 1];
		}

		var d = new StandardDeviation();
		return new HRVParameter(HRVParameterEnum.SDSD,d.evaluate(rrDiff), "non");
	}

	@Override
	public boolean validData(RRData data) {
		return data.getValueAxis().length > 0;
	}
}
