package hrv.lib.hrv.calc.manipulator;

import hrv.lib.hrv.RRData;
import hrv.lib.units.TimeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HRVFilterOutlierTest {

	@Test
	void testManipulate() {
		
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
}
