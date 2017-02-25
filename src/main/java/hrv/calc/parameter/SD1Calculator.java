package hrv.calc.statistical;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import hrv.HRVParameter;
import hrv.RRData;
import hrv.calc.HRVDataProcessor;

public class SD1Calculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		StandardDeviation sdnnCalc = new StandardDeviation();
		double sdnn = sdnnCalc.evaluate(data.getValueAxis());
		return new HRVParameter("SD1", Math.sqrt(0.5 * sdnn * sdnn), data.getValueAxisUnit().toString());
	}

}
