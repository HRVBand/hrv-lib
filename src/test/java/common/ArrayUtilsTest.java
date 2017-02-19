package common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class ArrayUtilsTest {
	
	 @Test 
	 public void testListToArray() {
		 ArrayList<Double> list = new ArrayList<>();
		 list.add(1.0);
		 list.add(2.0);
		 list.add(3.0);
		 list.add(4.0);
		 
		 double[] array = ArrayUtils.listToArray(list);
		 
		 assertEquals(array.length, list.size());
		 assertEquals(array[0], list.get(0), 0.001);
	 }
	 
	 @Test
	 public void testMin() {
		 double[] arr = new double[] {1.0, 2.0, 0.0, 100.0, 99.9999, 0.000001};
		 assertEquals(0.0, ArrayUtils.min(arr), 0.0000000001);
	 }
	 
	 @Test
	 public void testMax() {
		 double[] arr = new double[] {1.0, 2.0, 0.0, 100.0, 99.9999, 0.000001};
		 assertEquals(100.0, ArrayUtils.max(arr), 0.0000000001);
	 }
}
