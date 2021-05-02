package hrv.lib.hrv.calc.manipulator;

import hrv.lib.common.ArrayUtils;
import hrv.lib.common.MathUtils;
import hrv.lib.hrv.RRData;

import java.util.ArrayList;
import java.util.List;

/**
 * Filters outlier by testing whether data changes significantly 
 * before and after a point.
 * @author Julian
 *
 */
public class HRVFilterOutlier implements HRVDataManipulator {

	private int testRange = 4;
	private double strength = 2;
	
	
	@Override
	public RRData manipulate(RRData data) {

		int dataLength = data.getValueAxis().length;
		double[] values = data.getValueAxis();

		//calculate avg array
		double[] averages = MathUtils.calculateAverageArray(values, testRange);
		
		//find outlier indices
		var outlierIndices = new ArrayList<Integer>();
		for(var i = 0; i < dataLength; i++) {
			double distanceToAvg = Math.abs(values[i] - averages[i]);
			
			if(distanceToAvg > averages[i] * strength) {
				outlierIndices.add(i);
			}
		}
		
		double[] cleanValues = removeIndices(values, outlierIndices);
		
		return RRData.createFromRRInterval(cleanValues, data.getValueAxisUnit());
	}

	/**
	 * The range of points to investigate before and after a point to test
	 * whether a point is an outlier.
	 * @return value of that range.
	 */
	public int getTestRange() {
		return testRange;
	}

	/**
	 * The range of points to investigate before and after a point to test
	 * whether a point is an outlier. Range has to be > 0. 
	 * @param testRange value of that range.
	 */
	public void setTestRange(int testRange) {
		this.testRange = testRange;
	}

	/**
	 * Strength of the outlier detection. If the distance of the possible outlier 
	 * is smaller then avg * strength, then it is not an outlier. 
	 * Avg is the average of the given range.
	 * @return Value of detection strength
	 */
	public double getStrength() {
		return strength;
	}

	/**
	 * Strength of the outlier detection. If the distance of the possible outlier 
	 * is smaller then avg * strength, then it is not an outlier. 
	 * Avg is the average of the given range.
	 * @param strength Value of detection strength
	 */
	public void setStrength(double strength) {
		this.strength = strength;
	}
	
	private double[] removeIndices(double[] data, List<Integer> indicesToRemove) {
		List<Double> newData = new ArrayList<>();

		for (var i = 0; i < data.length; i++) {
			if (!indicesToRemove.contains(i)) {
				newData.add(data[i]);
			}
		}

		return ArrayUtils.toPrimitive(newData, 0.0);
	}
}
