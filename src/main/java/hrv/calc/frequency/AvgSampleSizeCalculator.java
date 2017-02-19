package hrv.calc.frequency;

import hrv.RRData;
import hrv.calc.HRVDataProcessor;

public class AvgSampleSizeCalculator implements HRVDataProcessor {

	@Override
	public Double process(RRData data) {
		double maxX = data.getTimeAxis()[data.getTimeAxis().length - 1];
		double minX = data.getTimeAxis()[0];
		double xLength = maxX - minX;
		return xLength / (data.getTimeAxis().length - 1);
	}
}
