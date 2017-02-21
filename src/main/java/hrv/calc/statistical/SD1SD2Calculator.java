package hrv.calc.statistical;

import hrv.HRVParameter;
import hrv.RRData;
import hrv.calc.HRVDataProcessor;

public class SD1SD2Calculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		SD1Calculator sd1Calc = new SD1Calculator();
		SD2Calculator sd2Calc = new SD2Calculator();

		return new HRVParameter("SD1SD2", sd1Calc.process(data).getValue() / sd2Calc.process(data).getValue(), "non");
	}

}
