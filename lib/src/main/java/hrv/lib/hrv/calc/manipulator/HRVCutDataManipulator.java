package hrv.lib.hrv.calc.manipulator;

import hrv.lib.hrv.RRData;

/**
 * Has the ability to cut a the data in a {@link RRData} object to a specified number.
 * The remaining data are the last values in the {@link RRData} arrays
 * 
 * @author Julian
 *
 */
public class HRVCutDataManipulator implements HRVDataManipulator {

	private final int numToKeep;
	
	public HRVCutDataManipulator(int numToKeep) {
		this.numToKeep = numToKeep;
	}
	
	@Override
	public RRData manipulate(RRData data) {
		double[] oldRRY = data.getValueAxis();
		double[] oldRRX = data.getTimeAxis();
		
		var newX = new double[numToKeep];
		var newY = new double[numToKeep];

		for (int i = data.getTimeAxis().length - numToKeep; i < data.getTimeAxis().length; i++) {
			newX[i - (data.getTimeAxis().length - numToKeep)] = oldRRX[i];
			newY[i - (data.getTimeAxis().length - numToKeep)] = oldRRY[i];
		}
		
		return new RRData(newX, data.getTimeAxisUnit(), newY, data.getValueAxisUnit());
	}

}
