package common;

import static org.junit.Assert.*;

import org.junit.Test;

public class RotatingMaxSizeListTest {

	@Test
	public void testRotation() {
		RotatingMaxSizeList<Double> list = new RotatingMaxSizeList<Double>(new Double[4]);
		
		list.add(5.0);
		assertEquals(5.0, list.get(0), 0.00001);
		list.add(2.0);
		assertEquals(2.0, list.get(1), 0.00001);
		list.add(3.0);
		assertEquals(3.0, list.get(2), 0.00001);
		list.add(4.0);
		assertEquals(4.0, list.get(3), 0.00001);
		list.add(6.0);
		assertEquals(6.0, list.get(0), 0.00001);
	}
	
	@Test
	public void testSize() {
		RotatingMaxSizeList<Double> list = new RotatingMaxSizeList<Double>(new Double[4]);
		assertEquals(4, list.size());
	}
}
