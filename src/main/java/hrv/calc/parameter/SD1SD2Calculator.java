package hrv.calc.parameter;

import hrv.RRData;

public class SD1SD2Calculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		SD1Calculator sd1Calc = new SD1Calculator();
		SD2Calculator sd2Calc = new SD2Calculator();

		return new HRVParameter(HRVParameterEnum.SD1SD2, sd1Calc.process(data).getValue() / sd2Calc.process(data).getValue(), "non");
	}

}
