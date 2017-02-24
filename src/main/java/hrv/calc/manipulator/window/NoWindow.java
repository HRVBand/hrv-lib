package hrv.calc.manipulator.window;

import hrv.RRData;
import hrv.calc.manipulator.HRVDataManipulator;

public class NoWindow implements HRVDataManipulator {

	@Override
	public RRData manipulate(RRData data) {
		return data;
	}

}
