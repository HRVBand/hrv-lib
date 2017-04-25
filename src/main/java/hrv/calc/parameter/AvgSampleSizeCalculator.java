package hrv.calc.parameter;

import hrv.RRData;

public class AvgSampleSizeCalculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		double maxX = data.getTimeAxis()[data.getTimeAxis().length - 1];
		double minX = data.getTimeAxis()[0];
		double xLength = maxX - minX;
		return new HRVParameter(HRVParameterEnum.AVG_SAMPLE_SIZE, xLength / (data.getTimeAxis().length - 1),
				"1 / " + data.getTimeAxisUnit().toString());
	}
	

	@Override
	public boolean validData(RRData data) {
		return data.getValueAxis().length > 0;
	}
}
