package common;

import java.util.List;

/**
 * Utility class for array operations.
 * 
 * @author Julian
 *
 */
public class ArrayUtils {
	/**
	 * Makes this class a utility class.
	 */
	private ArrayUtils() {}
	
	/**
	 * Converts a List of type double to an array of type double
	 * @param list List to convert
	 * @return Array filled with the data of the given list.
	 */
	public static double[] listToArray(final List<Double> list) {
		double[] array = new double[list.size()];
				
		for(int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		
		return array;
	}
	
	public static double min(double[] array) {
		double min = array[0];
		for (int i = 1; i < array.length; i++) {
			min = Math.min(min, array[i]);
		}
		return min;
	}

	public static double max(double[] array) {
		double max = array[0];
		for (int i = 1; i < array.length; i++) {
			max = Math.max(max, array[i]);
		}
		return max;
	}
}
