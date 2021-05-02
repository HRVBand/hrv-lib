package hrv.lib.hrv.calc.manipulator;

import hrv.lib.common.ArrayUtils;
import hrv.lib.hrv.RRData;

import java.util.ArrayList;
import java.util.List;

public class HRVCleanRRDataByLimits implements HRVDataManipulator {

	private static final double LOWER_RR_LIMIT = 0.2; // = 300 beats per minute
	private static final double UPPER_RR_LIMIT = 3.0; // = 20 beats per minute

	@Override
	public RRData manipulate(RRData data) {

		double[] oldRRY = data.getValueAxis();

		List<Integer> indicesToRemove = getIndicesToRemoveByLimit(oldRRY);

		double[] newRRY = removeIndices(oldRRY, indicesToRemove);

		return RRData.createFromRRInterval(newRRY, data.getValueAxisUnit());
	}

	private List<Integer> getIndicesToRemoveByLimit(double[] data) {
		List<Integer> indicesToRemove = new ArrayList<>();

		for (var i = 0; i < data.length; i++) {
			if (data[i] < LOWER_RR_LIMIT || data[i] > UPPER_RR_LIMIT) {
				indicesToRemove.add(i);
			}
		}

		return indicesToRemove;
	}

	private double[] removeIndices(double[] data, List<Integer> indicesToRemove) {
		List<Double> newData = new ArrayList<>();

		for (int i = 0; i < data.length; i++) {
			if (!indicesToRemove.contains(i)) {
				newData.add(data[i]);
			}
		}
		return ArrayUtils.toPrimitive(newData, 0.0);
	}
}
