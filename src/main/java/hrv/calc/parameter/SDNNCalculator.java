package hrv.calc.parameter;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import hrv.RRData;

public class SDNNCalculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
        StandardDeviation d = new StandardDeviation();
        return new HRVParameter(HRVParameterEnum.SDNN, d.evaluate(data.getValueAxis()), data.getValueAxisUnit().toString());
	}
	
	@Override
	public boolean validData(RRData data) {
		return data.getValueAxis().length > 0;
	}
}
