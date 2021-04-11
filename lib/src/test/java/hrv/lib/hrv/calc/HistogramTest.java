package hrv.lib.hrv.calc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HistogramTest {

	Histogram histogram1;
	Histogram histogram2;

	
	@Test
	public void testHistogram() {
		assertThrows(IllegalArgumentException.class, () -> new Histogram(new double[] {0.0}));
	}

	@Test
	public void testGetRange() {
		Histogram histo = new Histogram(new double[] {0.3, 0.0, 1.0});
		assertEquals(1.0, histo.getRange(), Double.MIN_VALUE);
	}

	@Test
	public void testGetBinSize() {
		Histogram histo = new Histogram(new double[] {0.0,1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0});
		assertEquals(3.0, histo.getBinSize(), Double.MIN_VALUE);
		

		Histogram histo2 = new Histogram(new double[] {5.0,1.0,8.0,5.0,4.0,5.0,0.0,7.0,8.0,9.0});
		assertEquals(3.0, histo2.getBinSize(), Double.MIN_VALUE);
	}

	@Test
	public void testSetBinSize() {
		Histogram histo3Bin = new Histogram(new double[] {0.0,1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0});
		
		histo3Bin.setBinSize(2.0);		
		assertEquals(4, histo3Bin.getBins().length);

		histo3Bin.setBinSize(3.0);		
		assertEquals(3, histo3Bin.getBins().length);
		
		Histogram histo1Bin = new Histogram(new double[] {1.0,1.0,1.0,1.0});
		histo1Bin.setBinSize(0.05);
		assertEquals(1, histo1Bin.getBins().length);
		
	}

	@Test
	public void testGetMode() {
		Histogram histoMode1 = new Histogram(new double[] {0.0,1.0,2.0,3.0,1.0,1.0,6.0,1.0,8.0,9.0});
		assertEquals(0.0, histoMode1.getMode(), Double.MIN_VALUE);
	}

	@Test
	public void testGetAmplitudeMode() {
		Histogram histoMode1 = new Histogram(new double[] {0.0,1.0,2.0,3.0,1.0,1.0,6.0,1.0,8.0,9.0});
		assertEquals(6, histoMode1.getAmplitudeMode());
	}
}
