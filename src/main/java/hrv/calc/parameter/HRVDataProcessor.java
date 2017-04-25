package hrv.calc.parameter;

import hrv.RRData;

public interface HRVDataProcessor {
	
	/**
	 * Processes HRV-Data and creates a HRVParameter. RR-Data must at least contain three data points to be able to calculate all HRVParameters.
	 * @param data Data to process
	 * @return HRVParameter
	 */
	HRVParameter process(RRData data);
	
	boolean validData(RRData data);
}
