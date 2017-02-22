package hrv.calc.manipulator;

import hrv.RRData;

@FunctionalInterface
public interface HRVDataManipulator {

	public RRData manipulate(RRData data);
}
