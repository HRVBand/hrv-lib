package hrv.calc.statistical;

import hrv.HRVParameter;
import hrv.RRData;
import hrv.calc.HRVDataProcessor;

public class RMSSDCalculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		double[] rr = data.getValueAxis();
		double sum = 0;

		for (int i = 1; i < rr.length; i++) {
			sum += (rr[i - 1] - rr[i]) * (rr[i - 1] - rr[i]);
		}

		return new HRVParameter("RMSSD", Math.sqrt(sum / (rr.length - 1)), data.getTimeAxisUnit().toString());
	}

}
