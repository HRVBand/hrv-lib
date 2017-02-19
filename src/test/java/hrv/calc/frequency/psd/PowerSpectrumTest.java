package hrv.calc.frequency.psd;

import static org.junit.Assert.*;

import org.junit.Test;

public class PowerSpectrumTest {

	@Test
	public void testAdd() {
		double[] power1 = new double[] { 1.0, 2.0, 3.0 };
		double[] power2 = new double[] { 3.0, 2.0, 1.0, 1.0 };
		
		double[] frequencies1 = new double[] { 1.0, 2.0, 3.0 };
		double[] frequencies2 = new double[] { 1.0, 2.0, 3.0, 4.0 };
		
		PowerSpectrum ps1 = new PowerSpectrum(power1, frequencies1);
		PowerSpectrum ps2 = new PowerSpectrum(power1, frequencies1);
		ps2.setPower(power2);
		ps2.setFrequency(frequencies2);
		
		ps2.add(ps1);
		assertEquals(4.0, ps2.getPower()[0], 0.0000001);
		assertEquals(4.0, ps2.getPower()[1], 0.0000001);
		assertEquals(4.0, ps2.getPower()[2], 0.0000001);
		assertEquals(1.0, ps2.getPower()[3], 0.0000001);
	}
}
