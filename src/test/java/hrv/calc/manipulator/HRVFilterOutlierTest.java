package hrv.calc.manipulator;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.RRData;
import units.TimeUnit;

public class HRVFilterOutlierTest {

	@Test
	public void testManipulate() {
		
		RRData data = RRData.createFromRRInterval(new double[] {1,1,1,1,1,1,1,1,1,1}, TimeUnit.SECOND);
		
		HRVFilterOutlier filter = new HRVFilterOutlier();
		RRData result = filter.manipulate(data);

		assertArrayEquals(data.getValueAxis(), result.getValueAxis(), Double.MIN_VALUE);
		

		RRData data2 = RRData.createFromRRInterval(new double[] {1,2,3,4,5,6,7,8,9,10}, TimeUnit.SECOND);
		RRData result2 = filter.manipulate(data2);		
		
		assertArrayEquals(new double[] {1,2,3,4,5,6,7,8,9,10}, result2.getValueAxis(), Double.MIN_VALUE);
		

		RRData data3 = RRData.createFromRRInterval(new double[] {1,2,3,4,50,6,7,8,9,10}, TimeUnit.SECOND);
		RRData result3 = filter.manipulate(data3);		
		
		assertArrayEquals(new double[] {1,2,3,4,6,7,8,9,10}, result3.getValueAxis(), Double.MIN_VALUE);
		

		RRData data4 = RRData.createFromRRInterval(new double[] {1,2,3,4,50,6,7,8,9,-10}, TimeUnit.SECOND);
		RRData result4 = filter.manipulate(data4);		
		
		assertArrayEquals(new double[] {1,2,3,4,6,7,8,9}, result4.getValueAxis(), Double.MIN_VALUE);
	}

	@Test
	public void testSetTestRange() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStrength() {
		fail("Not yet implemented");
	}

}
