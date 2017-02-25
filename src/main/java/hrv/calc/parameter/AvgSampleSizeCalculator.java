package hrv.calc.parameter;

import hrv.RRData;

public class AvgSampleSizeCalculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		double maxX = data.getTimeAxis()[data.getTimeAxis().length - 1];
		double minX = data.getTimeAxis()[0];
		double xLength = maxX - minX;
		return new HRVParameter("Avg. Sample Size", xLength / (data.getTimeAxis().length - 1), "1 / " + data.getTimeAxisUnit().toString());
	}
}
