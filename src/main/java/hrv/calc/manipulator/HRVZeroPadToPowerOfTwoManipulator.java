package hrv.calc.manipulator;

import common.ArrayUtils;
import common.MathUtils;
import hrv.RRData;
import hrv.calc.frequency.AvgSampleSizeCalculator;

/**
 * Appends Zeores to given RRData until the size of RRData is a power of two.
 * 
 * @author Julian
 *
 */
public class HRVZeroPadToPowerOfTwoManipulator implements HRVDataManipulator {

	@Override
	public void manipulate(RRData data) {
		int length = data.getTimeAxis().length;

		// Check if length is already a power of two.
		if ((length & -length) == length) {
			return; //If so return
		}

		int paddingUntil = MathUtils.largestNumThatIsPowerOf2(length) * 2;
		int numOfNewNumbers = paddingUntil - length;

		double[] newY = ArrayUtils.padZeros(data.getValueAxis(), numOfNewNumbers);

		AvgSampleSizeCalculator calc = new AvgSampleSizeCalculator();
		double avgSampleSize = calc.process(data);
		double[] newX = ArrayUtils.continueWith(data.getTimeAxis(), avgSampleSize, numOfNewNumbers);

		data.setTimeAxis(newX);
		data.setValueAxis(newY);
	}

}
