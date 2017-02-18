package algorithm;

import org.junit.Test;
import static org.junit.Assert.*;

public class FindIndexOfClosestValueSortedTest {

	@Test
	public void testFindIndexOfClosestValueSorted() {
		
		double[] array = new double[] {2,3,4,5,6,7,8,9,100,101};
		
		FindIndexOfClosestValueSorted indexFinder = new FindIndexOfClosestValueSorted();
		
		int index1 = indexFinder.findClosestIndexInSortedArray(array, 1.0);
		assertEquals(0, index1);
		int index2 = indexFinder.findClosestIndexInSortedArray(array, 2.0);
		assertEquals(0, index2);
		int index3 = indexFinder.findClosestIndexInSortedArray(array, 3.0);
		assertEquals(1, index3);
		int index3p5 = indexFinder.findClosestIndexInSortedArray(array, 3.5);
		assertEquals(1, index3p5);
		int index9 = indexFinder.findClosestIndexInSortedArray(array, 9.0);
		assertEquals(7, index9);
		int index10 = indexFinder.findClosestIndexInSortedArray(array, 10.0);
		assertEquals(7, index10);
		int index50 = indexFinder.findClosestIndexInSortedArray(array, 50.0);
		assertEquals(7, index50);
		int index90 = indexFinder.findClosestIndexInSortedArray(array, 90.0);
		assertEquals(8, index90);		
	}
}
