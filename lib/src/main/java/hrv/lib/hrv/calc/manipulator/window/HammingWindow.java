package hrv.lib.hrv.calc.manipulator.window;

import hrv.lib.hrv.RRData;
import hrv.lib.hrv.calc.manipulator.HRVDataManipulator;

public class HammingWindow implements HRVDataManipulator {

	@Override
	public RRData manipulate(RRData data) {
		double[] oldRRY = data.getValueAxis();
		double[] oldRRX = data.getTimeAxis();
	
		var newRRY = new double[data.getValueAxis().length];
		var newRRX = new double[data.getTimeAxis().length];
		
		for(var i = 0; i < oldRRY.length; i++) {
			newRRY[i] = oldRRY[i] * (0.54 - 0.46 * Math.cos((2 * Math.PI * i) / (oldRRY.length - 1)));
			newRRX[i] = oldRRX[i];
		}

		return new RRData(newRRX, data.getTimeAxisUnit(), newRRY, data.getValueAxisUnit());
	}
}
