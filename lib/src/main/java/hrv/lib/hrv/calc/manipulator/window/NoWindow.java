package hrv.lib.hrv.calc.manipulator.window;

import hrv.lib.hrv.RRData;
import hrv.lib.hrv.calc.manipulator.HRVDataManipulator;

public class NoWindow implements HRVDataManipulator {

	@Override
	public RRData manipulate(RRData data) {
		return data;
	}

}
