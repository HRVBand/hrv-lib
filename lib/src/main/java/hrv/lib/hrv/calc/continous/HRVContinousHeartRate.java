package hrv.lib.hrv.calc.continous;

import hrv.lib.hrv.RRData;
import hrv.lib.hrv.calc.parameter.HRVParameter;
import hrv.lib.hrv.calc.parameter.HRVParameterEnum;

public class HRVContinousHeartRate extends HRVContinousParameterCalculator {

	public HRVContinousHeartRate(int size) {
		super(size);
	}

	@Override
	public HRVParameter process(RRData data) {
		
		double[] values = data.getValueAxis();
		double sum = 0.0;
		for(int i = 0; i < values.length; i++) {
			sum += 1.0 / values[i];
		}
		
		double beatsPerSecond = sum / values.length;
					
		return new HRVParameter(HRVParameterEnum.HEART_RATE, beatsPerSecond * 60, "Beats / Minute");
	}
}
