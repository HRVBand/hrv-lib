package hrv.calc.statistical;

import common.ArrayUtils;
import hrv.RRData;
import hrv.calc.HRVDataProcessor;

/**
 * Calculates the Baevsky Stress Index corresponding to http://rainer-bartosch.de/HRV/Fachseminar_IMD_HRV.pdf
 * 
 * @author Julian
 *
 */
public class BaevskyCalculator implements HRVDataProcessor {

	@Override
	public double process(RRData rrinterval) {

		ModeCalculator modeCalc = new ModeCalculator();		
		double mode = modeCalc.process(rrinterval);

		AmplitudeModeCalculator ampModeCalc = new AmplitudeModeCalculator();
		double amplitudeMode = ampModeCalc.process(rrinterval); 
		
		MxDMnCalculator mxdmnCalc = new MxDMnCalculator();
		double mxdmn = mxdmnCalc.process(rrinterval);
		
		return amplitudeMode / (2 * mode * mxdmn);
	}
}
