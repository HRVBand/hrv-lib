package hrv.calc;

import hrv.RRData;
import org.apache.commons.math3.stat.descriptive.moment.Mean;

public class HRVSubstractMeanManipulator implements HRVDataManipulator {

	@Override
	public void manipulate(RRData data) {
		double[] rrY = data.getValueAxis();
		Mean m = new Mean();
		double mean = m.evaluate(rrY);

		for (int i = 0; i < rrY.length; i++) {
			rrY[i] -= mean;
		}
	}

}
