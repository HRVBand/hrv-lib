package hrv.lib.hrv.calc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HistogramTest {

	@Test
	void testHistogram() {
		assertThrows(IllegalArgumentException.class, () -> new Histogram(new double[] {0.0}));
	}

	@Test
	void testGetRange() {
		Histogram histogram = new Histogram(new double[] {0.3, 0.0, 1.0});
		assertEquals(1.0, histogram.getRange(), Double.MIN_VALUE);
	}

	@Test
	void testGetBinSize() {
		Histogram histogram = new Histogram(new double[] {0.0,1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0});
		assertEquals(3.0, histogram.getBinSize(), Double.MIN_VALUE);
		

		Histogram histogram1 = new Histogram(new double[] {5.0,1.0,8.0,5.0,4.0,5.0,0.0,7.0,8.0,9.0});
		assertEquals(3.0, histogram1.getBinSize(), Double.MIN_VALUE);
	}

	@Test
	void testSetBinSize() {
		Histogram histogramBin = new Histogram(new double[] {0.0,1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0});
		
		histogramBin.setBinSize(2.0);
		assertEquals(4, histogramBin.getBins().length);

		histogramBin.setBinSize(3.0);
		assertEquals(3, histogramBin.getBins().length);
		
		Histogram histogram1Bin = new Histogram(new double[] {1.0,1.0,1.0,1.0});
		histogram1Bin.setBinSize(0.05);
		assertEquals(1, histogram1Bin.getBins().length);
		
	}

	@Test
	void testGetMode() {
		Histogram histogram = new Histogram(new double[] {0.0,1.0,2.0,3.0,1.0,1.0,6.0,1.0,8.0,9.0});
		assertEquals(0.0, histogram.getMode(), Double.MIN_VALUE);
	}

	@Test
	void testGetAmplitudeMode() {
		Histogram histogram = new Histogram(new double[] {0.0,1.0,2.0,3.0,1.0,1.0,6.0,1.0,8.0,9.0});
		assertEquals(6, histogram.getAmplitudeMode());
	}
}
