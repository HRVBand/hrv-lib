package hrv.calc.statistical;

import hrv.HRVParameter;
import hrv.RRData;
import hrv.calc.HRVDataProcessor;

public class ModeCalculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		
		double[] a = data.getValueAxis();
		double maxValue = 0;
		int maxCount = 0;

		// For each element in a calculate the occurrences of that element in a.
		for (double anA : a) {
			int count = 0;
			for (double anA1 : a) {

				// Because the elements are of floating point precision they are
				// almost never the same.
				// Therefore they have to be in a certain range.
				if (!((anA1 > anA * 1.05) || (anA1 < anA * 0.95)))
					++count;
			}
			if (count > maxCount) {
				maxCount = count;
				maxValue = anA;
			}
		}

		return new HRVParameter("Mode", maxValue, "non");
	}
}
