package hrv.calc.parameter;

import hrv.RRData;

public class AmplitudeModeCalculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		ModeCalculator modeCalc = new ModeCalculator();
		double mode = modeCalc.process(data).getValue();
		
		int counter = 0;
		for (double aRrinterval : data.getValueAxis()) {
			if (!((aRrinterval > mode * 1.05) || (aRrinterval < mode * 0.95)))
				counter++;
		}
		return new HRVParameter("Amplitude Mode", counter / (double) data.getValueAxis().length, "non");
	}

}
