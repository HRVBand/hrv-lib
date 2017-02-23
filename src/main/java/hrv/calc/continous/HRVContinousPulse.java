package hrv.calc.continous;

import hrv.HRVParameter;
import hrv.RRData;

public class HRVContinousPulse extends HRVContinousParameterCalculator {

	public HRVContinousPulse(int size) {
		super(size);
	}

	@Override
	public HRVParameter process(RRData data) {
		double lastTimeValue = data.getTimeAxis()[data.getTimeAxis().length - 1];
		double beatsPerSecond = (data.getTimeAxis().length - 1) / lastTimeValue;
		return new HRVParameter("Pulse", beatsPerSecond * 60, "Beats / Minute");
	}
}
