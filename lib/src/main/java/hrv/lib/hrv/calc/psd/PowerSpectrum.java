package hrv.lib.hrv.calc.psd;

import hrv.lib.units.TimeUnit;

public class PowerSpectrum {

	private double[] power;
	private double[] frequency;
	private TimeUnit unit;
	
	public PowerSpectrum(double[] power, double[] frequency) {
		this.power = power;
		this.frequency = frequency;
	}
	
	public double[] getPower() {
		return power;
	}
	public void setPower(double[] power) {
		this.power = power;
	}
	public double[] getFrequency() {
		return frequency;
	}
	public void setFrequency(double[] frequency) {
		this.frequency = frequency;
	}
	public TimeUnit getUnit() {
		return unit;
	}
	public void setIncomingUnit(TimeUnit unit) {
		this.unit = unit;
	}
	
	/**
	 * Adds the power of the given {@code PowerSpectrum} to this {@code PowerSpectrum}.
	 * If one of the {@code PowerSpectrums} has more entries summation stops when the shorter
	 * {@code PowerSpectrum} reached its end. It is assumed, that the frequencies start at 
	 * the same values and that they increase with the same rate. 
	 * @param ps {@code PowerSpectrum} to add.
	 */
	public void add(PowerSpectrum ps) {		
		//Use the shorter length to avoid array out of bounds.
		int shorterLength = ps.getPower().length > this.power.length ? this.power.length : ps.getPower().length;
		
		for(int i = 0; i < shorterLength; i++) {
			this.power[i] += ps.getPower()[i];
		}
	}
}
