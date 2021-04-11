package hrv.lib.hrv.calc.continous;

import hrv.lib.hrv.RRData;
import hrv.lib.hrv.calc.parameter.HRVParameter;
import hrv.lib.hrv.calc.parameter.HRVParameterEnum;

public class HRVContinuousHeartRate extends HRVContinuousParameterCalculator {

	public HRVContinuousHeartRate(int size) {
		super(size);
	}

	@Override
	public HRVParameter process(RRData data) {
		
		double[] values = data.getValueAxis();
		double sum = 0.0;
		for (double value : values) {
			sum += 1.0 / value;
		}
		
		double beatsPerSecond = sum / values.length;
					
		return new HRVParameter(HRVParameterEnum.HEART_RATE, beatsPerSecond * 60, "Beats / Minute");
	}
}
