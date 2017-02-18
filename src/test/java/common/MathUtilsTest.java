package common;

import static org.junit.Assert.assertEquals;

import java.math.RoundingMode;

import org.junit.Test;

public class MathUtilsTest {

	@Test
	public void testRound() {
		double num = 111.1111111111111111;
		
		double roundedNum = MathUtils.round(num, 2);		
		assertEquals(111.11, roundedNum, 0.0001);
		
		double roundedNum2 = MathUtils.round(num, 2, RoundingMode.HALF_DOWN);
		assertEquals(111.11, roundedNum2, 0.0001);	
	}
}
