package common;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for common mathematics operations.
 * @author Julian
 *
 */
public class MathUtils {
	
	/**
	 * Makes this class a utility class.
	 */
	private MathUtils() {}
	
	/**
	 * Rounds the given number (value) to places places. With rounding mode half down (5.5 => 5)
	 * @param value Value to round
	 * @param places Number of places the given number should be rounded to
	 * @return rounded number.
	 */
	public static double round(double value, int places) {
	    if (places < 0) 
	    	throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_DOWN);
	    return bd.doubleValue();
	}
	
	public static double round(double value, int places, RoundingMode mode) {
	    if (places < 0) 
	    { throw new IllegalArgumentException(); }

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, mode);
	    return bd.doubleValue();
	}
	
	/**
	 * Returns the largest number that is a power of two 
	 * and smaller than the given number (n) 
	 * @param n given number 
	 * @return the largest number that is a power of two 
	 * and smaller than the given number (n)
	 */
	public static int largestNumThatIsPowerOf2(int n) {
		return Integer.highestOneBit(n);
	}
}
