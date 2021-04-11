package hrv.lib.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayUtilsTest {

	@Test
	public void testListToPrimitiveWithDefaultValue() {
		ArrayList<Double> list = new ArrayList<>();
		list.add(1.0);
		list.add(2.0);
		list.add(3.0);
		list.add(4.0);
		list.add(null);

		double[] array = ArrayUtils.toPrimitive(list, 0.0);

		assertEquals(array.length, list.size());
		assertEquals(array[0], list.get(0), 0.001);
		assertEquals(array[4], 0.0, 0.001);
	}

	@Test
	public void testListToPrimitiveIgnoreNull() {
		ArrayList<Double> list = new ArrayList<>();
		list.add(1.0);
		list.add(2.0);
		list.add(3.0);
		list.add(4.0);
		list.add(null);

		double[] array = ArrayUtils.toPrimitiveIgnoreNull(list);

		assertEquals(array.length, list.size() - 1);
		assertEquals(array[0], list.get(0), 0.001);
		assertEquals(array[3], 4.0, 0.001);
	}

	@Test
	public void testArrayToPrimitive() {
		Double[] array = new Double[] { 1.0, 2.0, 3.0, 4.0, null };
		double[] primitiveArray = ArrayUtils.toPrimitive(array, 5.0);

		assertEquals(array.length, primitiveArray.length);
		assertEquals(array[0], primitiveArray[0], 0.001);
		assertEquals(5.0, primitiveArray[4], 0.001);
	}
	
	@Test
	public void testArrayToPrimitiveIgnoreNull() {
		Double[] array = new Double[] { 1.0, 2.0, 3.0, 4.0, null };
		double[] primitiveArray = ArrayUtils.toPrimitiveIgnoreNull(array);

		assertEquals(array.length - 1, primitiveArray.length);
		assertEquals(array[0], primitiveArray[0], 0.001);
		assertEquals(array[3], 4.0, 0.001);
	}

	@Test
	public void testMin() {
		double[] arr = new double[] { 1.0, 2.0, 0.0, 100.0, 99.9999, 0.000001 };
		assertEquals(0.0, ArrayUtils.min(arr), 0.0000000001);
	}

	@Test
	public void testMax() {
		double[] arr = new double[] { 1.0, 2.0, 0.0, 100.0, 99.9999, 0.000001 };
		assertEquals(100.0, ArrayUtils.max(arr), 0.0000000001);
	}

	@Test
	public void testPadZeros() {
		double[] arr = new double[] { 1.0, 2.0, 0.0, 100.0, 99.9999, 0.000001 };
		double[] paddedArr = ArrayUtils.padZeros(arr, 4);
		assertEquals(100.0, paddedArr[3], 0.0000000001);
		assertEquals(0.000001, paddedArr[5], 0.0000000001);
		assertEquals(0.0, paddedArr[6], 0.0000000001);
		assertEquals(0.0, paddedArr[9], 0.0000000001);
		assertEquals(10, paddedArr.length);
	}

	@Test
	void testPadNoZeroes() {
		double[] arr = new double[] { 1.0, 2.0, 0.0, 100.0, 99.9999, 0.000001 };
		double[] paddedArr = ArrayUtils.padZeros(arr, 0);

		assertEquals(100.0, paddedArr[3], 0.0000000001);
		assertEquals(0.000001, paddedArr[5], 0.0000000001);
		assertEquals(arr.length, paddedArr.length);
	}
	
	@Test
	void testForPadZerosForIllegalArgumentExcetion() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> ArrayUtils.padZeros(new double[] {1.0}, -1));
	}

	@Test
	void testPadToEmptyArray() {
		double[] arr = new double[0];

		double[] paddedArr = ArrayUtils.padZeros(arr, 10);
		assertEquals(10, paddedArr.length);
		assertEquals(0.0, paddedArr[5], 0.0000000001);

		double[] paddedArr2 = ArrayUtils.padZeros(arr, 0);
		assertEquals(0, paddedArr2.length);
	}

	@Test
	void testContinouWith() {
		double[] arr = new double[] { 0.0, 1.0, 2.0 };
		double[] newArr = ArrayUtils.continueWith(arr, 1.0, 2);

		assertEquals(3.0, newArr[3], 0.00001);
		assertEquals(4.0, newArr[4], 0.00001);
		assertEquals(5, newArr.length);

		double[] arr2 = new double[] { 1.0, 2.0, 3.0 };
		double[] newArr2 = ArrayUtils.continueWith(arr2, 1.0, 2);

		assertEquals(4.0, newArr2[3], 0.00001);
		assertEquals(5.0, newArr2[4], 0.00001);
		assertEquals(5, newArr2.length);
	}

	@Test
	void testContinouWithZeroLengthArray() {
		double[] arr = new double[0];
		double[] newArr = ArrayUtils.continueWith(arr, 2.0, 2);

		assertEquals(2, newArr.length);
		assertEquals(2.0, newArr[0], 0.00001);
		assertEquals(4.0, newArr[1], 0.00001);
	}

	@Test
	void testContinouWithDontContinou() {
		double[] arr = new double[0];
		double[] newArr = ArrayUtils.continueWith(arr, 1.0, 0);
		assertEquals(0, newArr.length);

		double[] arr2 = new double[] { 1.0, 2.0, 3.0 };
		double[] newArr2 = ArrayUtils.continueWith(arr2, 1.0, 0);
		assertEquals(3, newArr2.length);
	}
	
	@Test
	void testContinouWithForIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () ->
		ArrayUtils.continueWith(new double[] {0.0}, 2, -100));
	}
}
