package units;

import java.util.EnumMap;

public class TimeUnitConverter implements UnitConverter<TimeUnitConverter.TimeUnit> {

	/**
	 * Contains unit conversion factors relative to convert a unit to the unit s.
	 */
	EnumMap<TimeUnit, Double> unitKonvertions = new EnumMap<>(TimeUnit.class);
	
	public TimeUnitConverter() {
		unitKonvertions.put(TimeUnit.DAY, 60.0 * 60 * 24);
		unitKonvertions.put(TimeUnit.HOUR, 60.0 * 60);
		unitKonvertions.put(TimeUnit.MINUTE, 60.0);
		unitKonvertions.put(TimeUnit.SECOND, 1.0);
		unitKonvertions.put(TimeUnit.MILLISECOND, 0.001);
	}
	
	@Override
	public double convert(double value, TimeUnit from, TimeUnit to) {
		
		double valInS = value * unitKonvertions.get(from);
		return valInS / unitKonvertions.get(to);
	}

	@Override
	public void convert(double[] values, TimeUnit from, TimeUnit to) {
		
		for(int i = 0; i < values.length; i++) {
			values[i] = convert(values[i], from, to);
		}
	}

	public enum TimeUnit {
		DAY,
		HOUR,
		MINUTE,
		SECOND,
		MILLISECOND
	}
}