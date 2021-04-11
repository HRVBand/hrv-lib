package hrv.lib.common;

import java.util.ArrayList;
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
	public static double[] toPrimitive(final List<Double> list, double defaultValue) {
		double[] array = new double[list.size()];

		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i) == null ? defaultValue : list.get(i);
		}

		return array;
	}

	/**
	 * Casts the given Double array to a primitive double array. For occurring
	 * null values in the Object array, a default value is inserted.
	 * 
	 * @param array Array to cast to primitive double array
	 * @param defaultValue Value to add for null values in the given {@code Double} object array
	 * @return primitive double array
	 */
	public static double[] toPrimitive(Double[] array, double defaultValue) {
		double[] newArray = new double[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i] == null ? defaultValue : array[i];
		}

		return newArray;
	}

	/**
	 * Casts a given array of {@code Double} to an array of primitive
	 * {@code double} type. If a Double value in the given array is
	 * {@code null}, the value is ignored.
	 * 
	 * @param array
	 *            Array to cast.
	 * @return double array containing the non null elements of the given array.
	 */
	public static double[] toPrimitiveIgnoreNull(Double[] array) {
		ArrayList<Double> newArray = new ArrayList<>();

		for (Double aDouble : array) {
			if (aDouble != null) {
				newArray.add(aDouble);
			}
		}

		return toPrimitive(newArray, 0.0);
	}

	/**
	 * Casts a given List of {@code Double} to an {@code double} array. If a
	 * Double value in the given list is {@code null}, the value is ignored.
	 * 
	 * @param list
	 *            List to cast.
	 * @return double array containing the non null elements of the list.
	 */
	public static double[] toPrimitiveIgnoreNull(List<Double> list) {
		List<Double> withoutNull = new ArrayList<>();

		for (Double d : list) {
			if (d != null) {
				withoutNull.add(d);
			}
		}

		return toPrimitive(withoutNull, 0.0);
	}

	/**
	 * Returns the smallest number in the array
	 * 
	 * @param array
	 *            array to search the smallest number in
	 * @return smallest number in the given array
	 */
	public static double min(double[] array) {
		double min = array[0];
		for (int i = 1; i < array.length; i++) {
			min = Math.min(min, array[i]);
		}
		return min;
	}

	/**
	 * Returns the biggest number in the array
	 * 
	 * @param array
	 *            array to search the biggest number in
	 * @return biggest number in the given array
	 */
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
	 * @param newZeroes
	 *            Number of zeroes to add (has to be positive).
	 * @return new array with trailing zeroes
	 */
	public static double[] padZeros(double[] data, int newZeroes) {
		if (newZeroes < 0)
			throw new IllegalArgumentException("Number of new zeroes must be larger than or equal to 0.");

		double[] newData = new double[data.length + newZeroes];

		// Transfer existing data
		System.arraycopy(data, 0, newData, 0, data.length);

		// Fill remaining values with zero and make x-Axis continuous
		for (int i = data.length; i < data.length + newZeroes; i++) {
			newData[i] = 0;
		}

		return newData;
	}

	/**
	 * Continuous the given data by increments of the last element in the data.
	 * The value of the increment is defined by {@code increment}. The number of
	 * increments is given by {@code numOfNewIncrements}. The length of the new
	 * data array is {@code data.length + numOfNewIncrements}
	 * 
	 * @param data
	 *            Data to add increments to
	 * @param increment
	 *            Size of the increments.
	 * @param numOfNewIncrements
	 *            Number of increments to add to the given data.
	 * @return new array containing the old data and appended are the
	 *         increments.
	 */
	public static double[] continueWith(double[] data, double increment, int numOfNewIncrements) {
		if (numOfNewIncrements < 0)
			throw new IllegalArgumentException("Number of new increments must be larger than or equal to 0.");

		double[] newData = new double[data.length + numOfNewIncrements];

		// Transfer existing data
		System.arraycopy(data, 0, newData, 0, data.length);

		// Fill remaining values with zero and make x-Axis continuous
		double firstOldElement = data.length == 0 ? 0 : data[data.length - 1];
		for (int i = data.length; i < data.length + numOfNewIncrements; i++) {
			newData[i] = firstOldElement + increment * (i - data.length + 1);
		}

		return newData;
	}
}
