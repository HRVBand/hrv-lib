package hrv.calc;

import hrv.RRData;

@FunctionalInterface
public interface HRVDataProcessor {
	double process(RRData data);
}
