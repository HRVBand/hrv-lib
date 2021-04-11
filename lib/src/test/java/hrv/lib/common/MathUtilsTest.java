package hrv.lib.common;

import org.junit.jupiter.api.Test;

import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest {

	@Test
	public void testRound() {
		double num = 111.1111111111111111;
		
		double roundedNum = MathUtils.round(num, 2);		
		assertEquals(111.11, roundedNum, 0.0001);
		
		double roundedNum2 = MathUtils.round(num, 2, RoundingMode.HALF_DOWN);
		assertEquals(111.11, roundedNum2, 0.0001);	
	}
	
	@Test
	public void testAlmostEquals() {
		assertFalse(MathUtils.almostEqual(0.0, 0.1, 0.01));
		assertTrue(MathUtils.almostEqual(0.0, 0.01, 0.1));
	}
	
	@Test
	public void testAvgsArray() {
		double[] testData = new double[] {1,2,3,4,5,6,7,8,9,10};
		
		double[] result = MathUtils.calculateAverageArray(testData, 2);
		
		assertArrayEquals(new double[] {2.5, 8.0 / 3.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0,  25.0 / 3.0, 17.0 / 2.0}, result, Double.MIN_VALUE);
	}
}
