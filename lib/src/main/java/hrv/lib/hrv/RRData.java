package hrv.lib.hrv;

import hrv.lib.units.TimeUnit;
import hrv.lib.units.TimeUnitConverter;

/**
 * Holds RR-Data and the corresponding X-Axis Values
 * 
 * @author Julian
 *
 */
public class RRData {

	private TimeUnit rrValueAxisUnit;
	private /*@ spec_public */ TimeUnit rrTimeAxisUnit; 

	private double[] rrValuesAxis;
	private double[] rrTimeAxis;

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
	
	public void setValueAxis(double[] values) {
		this.rrValuesAxis = values;
	}

	public double[] getTimeAxis() {
		return rrTimeAxis;
	}

	public void setTimeAxis(double[] values) {
		this.rrTimeAxis = values;
	}
	
	public TimeUnit getValueAxisUnit() {
		return rrValueAxisUnit;
	}

	public TimeUnit getTimeAxisUnit() {
		return rrTimeAxisUnit;
	}
	
	public void changeValuesAxisUnit(TimeUnit newRRValueUnit) {
		var converter = new TimeUnitConverter();
		converter.convert(this.rrValuesAxis, this.rrValueAxisUnit, newRRValueUnit);
		this.rrValueAxisUnit = newRRValueUnit;
	}

	public void changeTimeAxisUnit(TimeUnit newTimeAxisUnit) {
		var converter = new TimeUnitConverter();
		converter.convert(this.rrTimeAxis, this.rrTimeAxisUnit, newTimeAxisUnit);
		this.rrTimeAxisUnit = newTimeAxisUnit;
	}
}
