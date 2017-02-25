package hrv.calc.continous;

import hrv.RRData;
import hrv.calc.parameter.HRVParameter;

public class HRVContinousPulse extends HRVContinousParameterCalculator {

	public HRVContinousPulse(int size) {
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
					
		return new HRVParameter("Pulse", beatsPerSecond * 60, "Beats / Minute");
	}
}
