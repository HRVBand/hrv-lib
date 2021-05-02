package hrv.lib.hrv;

import hrv.lib.units.TimeUnit;

/**
 * Holds RR-Data and the corresponding X-Axis Values
 * 
 * @author Julian
 *
 */
public class RRData {

	private final TimeUnit rrValueAxisUnit;
	private final /*@ spec_public */ TimeUnit rrTimeAxisUnit;

	private final double[] rrValuesAxis;
	private final double[] rrTimeAxis;

	public RRData(double[] timeAxis, TimeUnit timeAxisUnit, double[] valueAxis, TimeUnit valueAxisUnit) {
		this.rrValuesAxis = valueAxis;
		this.rrValueAxisUnit = valueAxisUnit;

		this.rrTimeAxis = timeAxis;
		this.rrTimeAxisUnit = timeAxisUnit;
	}

	/**
	 * Creates a new RRData object from an RR-Interval
	 * 
	 * @param rrInterval
	 *            RR-Interval
	 * @param unit
	 *            of the given RR-Data.
	 * @return new RRData object with given RR-Interval data.
	 */
	public static RRData createFromRRInterval(double[] rrInterval, TimeUnit unit) {
		var rrTimeAxis = new double[rrInterval.length];
		for (var i = 1; i < rrInterval.length; i++) {
			rrTimeAxis[i] = rrTimeAxis[i - 1] + rrInterval[i - 1];
		}

		return new RRData(rrTimeAxis, unit, rrInterval, unit);
	}

	public double[] getValueAxis() {
		return rrValuesAxis;
	}

	public double[] getTimeAxis() {
		return rrTimeAxis;
	}
	
	public TimeUnit getValueAxisUnit() {
		return rrValueAxisUnit;
	}

	public TimeUnit getTimeAxisUnit() {
		return rrTimeAxisUnit;
	}
}
