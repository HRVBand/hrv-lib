package hrv.calc;

import hrv.HRVParameter;
import hrv.RRData;

@FunctionalInterface
public interface HRVDataProcessor {
	HRVParameter process(RRData data);
}
