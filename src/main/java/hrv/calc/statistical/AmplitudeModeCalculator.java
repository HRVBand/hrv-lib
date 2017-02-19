package hrv.calc.statistical;

import hrv.RRData;
import hrv.calc.HRVDataProcessor;

public class AmplitudeModeCalculator implements HRVDataProcessor {

	@Override
	public double process(RRData data) {
		ModeCalculator modeCalc = new ModeCalculator();
		double mode = modeCalc.process(data);
		
		int counter = 0;
		for (double aRrinterval : data.getValueAxis()) {
			if (!((aRrinterval > mode * 1.05) || (aRrinterval < mode * 0.95)))
				counter++;
		}
		return counter / (double) data.getValueAxis().length;
	}

}
