package hrv.calc.manipulator;

import hrv.RRData;

@FunctionalInterface
public interface HRVDataManipulator {

	public void manipulate(RRData data);
}
