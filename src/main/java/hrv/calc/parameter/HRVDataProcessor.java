package hrv.calc.parameter;

import hrv.RRData;

@FunctionalInterface
public interface HRVDataProcessor {
	HRVParameter process(RRData data);
}
