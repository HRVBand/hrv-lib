package hrv.calc.parameter;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import hrv.RRData;

public class SDSDCalculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		double[] rr = data.getValueAxis();
		double[] rrdiff = new double[rr.length - 1];

		for (int i = 0; i < rr.length - 1; i++) {
			rrdiff[i] = rr[i] - rr[i + 1];
		}

		StandardDeviation d = new StandardDeviation();		
		return new HRVParameter(HRVParameterEnum.SDSD,d.evaluate(rrdiff), "non");
	}
}
