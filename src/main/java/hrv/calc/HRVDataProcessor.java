package hrv.calc;

import hrv.RRData;

@FunctionalInterface
public interface HRVDataProcessor {
	Double process(RRData data);
}
