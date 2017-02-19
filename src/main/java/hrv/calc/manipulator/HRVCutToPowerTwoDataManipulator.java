package hrv.calc;

import common.MathUtils;
import hrv.RRData;

/**
 * Cuts the given data down, so that the resulting lenght of the data is a power of two.
 * 
 * @author Julian
 *
 */
public class HRVCutToPowerTwoDataManipulator implements HRVDataManipulator {

	@Override
	public void manipulate(RRData data) {
		int cutAt = MathUtils.largestNumThatIsPowerOf2(data.getTimeAxis().length);

		double[] newX = new double[cutAt];
		double[] newY = new double[cutAt];

		for (int i = data.getTimeAxis().length - cutAt; i < data.getTimeAxis().length; i++) {
			newX[i - (data.getTimeAxis().length - cutAt)] = data.getTimeAxis()[i];
			newY[i - (data.getTimeAxis().length - cutAt)] = data.getValueAxis()[i];
		}

		data.setTimeAxis(newX);
		data.setValueAxis(newY);
	}

}
