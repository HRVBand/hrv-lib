package hrv.calc.manipulator;

import hrv.RRData;

public class HRVCutDataManipulator implements HRVDataManipulator {

	private int numToKeep;
	
	public HRVCutDataManipulator(int numToKeep) {
		this.numToKeep = numToKeep;
	}
	
	@Override
	public RRData manipulate(RRData data) {
		double[] oldRRY = data.getValueAxis();
		double[] oldRRX = data.getTimeAxis();
		
		double[] newX = new double[numToKeep];
		double[] newY = new double[numToKeep];

		for (int i = data.getTimeAxis().length - numToKeep; i < data.getTimeAxis().length; i++) {
			newX[i - (data.getTimeAxis().length - numToKeep)] = oldRRX[i];
			newY[i - (data.getTimeAxis().length - numToKeep)] = oldRRY[i];
		}
		
		return new RRData(newX, data.getTimeAxisUnit(), newY, data.getValueAxisUnit());
	}

}
