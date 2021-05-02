package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.RRData;

public class SD1SD2Calculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		var sd1Calc = new SD1Calculator();
		var sd2Calc = new SD2Calculator();

		return new HRVParameter(HRVParameterEnum.SD1SD2, sd1Calc.process(data).getValue() / sd2Calc.process(data).getValue(), "non");
	}


	@Override
	public boolean validData(RRData data) {
		return data.getValueAxis().length > 1;
	}
}
