package hrv.calc.manipulator.window;

import hrv.RRData;
import hrv.calc.manipulator.HRVDataManipulator;

public class HammingWindow implements HRVDataManipulator {

	@Override
	public void manipulate(RRData data) {
		double[] values = data.getValueAxis();
		
		for(int i = 0; i < values.length; i++) {
			values[i] *= (0.54 - 0.46 * Math.cos((2 * Math.PI * i) / (values.length - 1)));
		}
	}
}
