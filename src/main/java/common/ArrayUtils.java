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
	private ArrayUtils() {
	}

	/**
	 * Converts a List of type double to an array of type double
	 * 
	 * @param list
	 *            List to convert
	 * @return Array filled with the data of the given list.
	 */
	public static double[] listToArray(final List<Double> list) {
		double[] array = new double[list.size()];

		for (int i = 0; i < list.size(); i++) {
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

	/**
	 * Adds a number of Zeros to the array as specifies with {@code newZeroes}.
	 * 
	 * @param data
	 *            Data to be add zeroes to
	 * @param untilIndex
	 *            Number of zeroes to add (has to be positive).
	 * @return new array with trailing zeroes
	 */
	public static double[] padZeros(double[] data, int newZeroes) {
		if (newZeroes < 0)
			throw new IllegalArgumentException("Number of new zeroes must be larger than or equal to 0.");

		double[] newData = new double[data.length + newZeroes];

		// Transfer existing data
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}

		// Fill remaining values with zero and make x-Axis continuous
		for (int i = data.length; i < data.length + newZeroes; i++) {
			newData[i] = 0;
		}

		return newData;
	}

	public static double[] continueWith(double[] data, double increment, int numOfNewIncrements) {
		if(numOfNewIncrements < 0)
			throw new IllegalArgumentException("Number of new increments must be larger than or equal to 0.");
				
		double[] newData = new double[data.length + numOfNewIncrements];

		// Transfer existing data
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}

		// Fill remaining values with zero and make x-Axis continuous
		double firstOldElement = data.length == 0 ? 0 : data[data.length - 1];
		for (int i = data.length; i < data.length + numOfNewIncrements; i++) {
			newData[i] = firstOldElement + increment * (i - data.length + 1);
		}

		return newData;
	}
}
