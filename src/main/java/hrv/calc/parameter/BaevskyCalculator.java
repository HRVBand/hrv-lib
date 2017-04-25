package hrv.calc.parameter;

import hrv.RRData;

/**
 * Calculates the Baevsky Stress Index corresponding to http://rainer-bartosch.de/HRV/Fachseminar_IMD_HRV.pdf
 * Normal values should be between 50 and 150. The value has no unit and is often just called SI.
 * 
 * @author Julian
 *
 */
public class BaevskyCalculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData rrinterval) {
		
		ModeCalculator modeCalc = new ModeCalculator();		
		double mode = modeCalc.process(rrinterval).getValue();

		AmplitudeModeCalculator ampModeCalc = new AmplitudeModeCalculator();
		double amplitudeMode = ampModeCalc.process(rrinterval).getValue();
		
		MxDMnCalculator mxdmnCalc = new MxDMnCalculator();
		double mxdmn = mxdmnCalc.process(rrinterval).getValue();
		
		return new HRVParameter(HRVParameterEnum.BAEVSKY, amplitudeMode / (2 * mode * mxdmn), "non");
	}
	

	@Override
	public boolean validData(RRData data) {
		return data.getValueAxis().length > 0;
	}
}
