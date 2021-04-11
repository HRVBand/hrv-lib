package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.RRData;

/**
 * Calculates the pNN50-HRV-Parameter from the given RR-interval-data.
 * 
 * @author Julian
 *
 */
public class PNN50Calculator implements HRVDataProcessor {

	/**
	 * Calculates the pNN50-HRV-Parameter in %, from the given RR-interval-data.
	 * @param rr RR-interval-data in seconds.
	 * @return calculated pNN50 parameter.
	 */
	@Override
	public HRVParameter process(RRData data) {
		double[] rr = data.getValueAxis();
		
		NN50Calculator calc = new NN50Calculator();
		
		return new HRVParameter(HRVParameterEnum.PNN50, calc.process(data).getValue() / (double)(rr.length - 1) * 100, "%");
	}
	
	@Override
	public boolean validData(RRData data) {
		return data.getValueAxis().length > 1;
	}
}
