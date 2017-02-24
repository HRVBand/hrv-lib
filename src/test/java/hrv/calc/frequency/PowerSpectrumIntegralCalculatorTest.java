package hrv.calc.frequency;

import static org.junit.Assert.*;

import org.junit.Test;

import hrv.calc.frequency.psd.PowerSpectrum;

public class PowerSpectrumIntegralCalculatorTest {

	@Test
	public void testOneFunction() {
		int onesLength = 10;
		double[] ones = new double[onesLength];
		double[] onesX = new double[onesLength];
		for(int i = 0; i < ones.length; i++) {
			ones[i] = 1.0;
			onesX[i] = i;
		}
		
		PowerSpectrum ps = new PowerSpectrum(ones, onesX);
		
		PowerSpectrumIntegralCalculator calc = new PowerSpectrumIntegralCalculator(0, 5);
		assertEquals(5, calc.process(ps), 0.01);

		PowerSpectrumIntegralCalculator calc2 = new PowerSpectrumIntegralCalculator(0.04, 0.15);
		assertEquals(0.11, calc2.process(ps), 0.0001);
	}
}
