package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.RRData;

public class AmplitudeModeCalculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		ModeCalculator modeCalc = new ModeCalculator();
		double mode = modeCalc.process(data).getValue();
		
		int counter = 0;
		for (double aRRInterval : data.getValueAxis()) {
			if (!((aRRInterval > mode * 1.05) || (aRRInterval < mode * 0.95)))
				counter++;
		}
		return new HRVParameter(HRVParameterEnum.AMPLITUDEMODE, counter / (double) data.getValueAxis().length, "non");
	}

	@Override
	public boolean validData(RRData data) {
		return data.getValueAxis().length > 0;
	}
}
