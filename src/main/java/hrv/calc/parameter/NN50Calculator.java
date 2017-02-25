package hrv.calc.parameter;

import hrv.RRData;
import units.TimeUnitConverter.TimeUnit;

/**
 * Calculates the NN50 parameter from the given RR-interval-data.
 * @author Julian
 *
 */
public class NN50Calculator implements HRVDataProcessor {
	
	/**
	 * Calculates the NN50 parameter from the given rr-interval-data.
	 * @param data RR-interval-data in seconds
	 * @return calculated NN50 parameter
	 */
	@Override
	public HRVParameter process(RRData data) {
		double[] rr = data.getValueAxis();
		double threshold = data.getValueAxisUnit() == TimeUnit.SECOND ? 0.05 : 50; //50 ms
		
		int nn50 = 0;

		for (int i = 0; i < rr.length - 1; i++) {
			if (Math.abs(rr[i] - rr[i + 1]) > threshold) {
				nn50 += 1;
			}
		}

		return new HRVParameter("NN50", nn50, "non");
	}

}
