package common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

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
	public void testPadNoZeroes() {
		double[] arr = new double[] { 1.0, 2.0, 0.0, 100.0, 99.9999, 0.000001 };
		double[] paddedArr = ArrayUtils.padZeros(arr, 0);

		assertEquals(100.0, paddedArr[3], 0.0000000001);
		assertEquals(0.000001, paddedArr[5], 0.0000000001);
		assertEquals(arr.length, paddedArr.length);
	}

	@Test
	public void testPadToEmptyArray() {
		double[] arr = new double[0];

		double[] paddedArr = ArrayUtils.padZeros(arr, 10);
		assertEquals(10, paddedArr.length);
		assertEquals(0.0, paddedArr[5], 0.0000000001);

		double[] paddedArr2 = ArrayUtils.padZeros(arr, 0);
		assertEquals(0, paddedArr2.length);
	}

	@Test
	public void testContinouWith() {
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
	public void testContinouWithZeroLengthArray() {
		double[] arr = new double[0];
		double[] newArr = ArrayUtils.continueWith(arr, 2.0, 2);

		assertEquals(2, newArr.length);		
		assertEquals(2.0, newArr[0], 0.00001);
		assertEquals(4.0, newArr[1], 0.00001);
	}
	
	@Test
	public void testContinouWithDontContinou() {
		double[] arr = new double[0];
		double[] newArr = ArrayUtils.continueWith(arr, 1.0, 0);
		assertEquals(0, newArr.length);		
		

		double[] arr2 = new double[] { 1.0, 2.0, 3.0 };
		double[] newArr2 = ArrayUtils.continueWith(arr2, 1.0, 0);
		assertEquals(3, newArr2.length);		
	}
}
