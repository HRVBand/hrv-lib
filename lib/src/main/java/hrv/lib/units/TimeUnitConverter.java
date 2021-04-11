package hrv.lib.units;

import java.util.EnumMap;

public class TimeUnitConverter implements UnitConverter<TimeUnit> {

	/**
	 * Contains unit conversion factors relative to convert a unit to the unit s.
	 */
	private EnumMap<TimeUnit, Double> unitConversions = new EnumMap<>(TimeUnit.class);
	
	public TimeUnitConverter() {
		unitConversions.put(TimeUnit.DAY, 60.0 * 60 * 24);
		unitConversions.put(TimeUnit.HOUR, 60.0 * 60);
		unitConversions.put(TimeUnit.MINUTE, 60.0);
		unitConversions.put(TimeUnit.SECOND, 1.0);
		unitConversions.put(TimeUnit.MILLISECOND, 0.001);
	}
	
	@Override
	public double convert(double value, TimeUnit from, TimeUnit to) {
		
		double valInS = value * unitConversions.get(from);
		return valInS / unitConversions.get(to);
	}

	@Override
	public void convert(double[] values, TimeUnit from, TimeUnit to) {
		
		for(int i = 0; i < values.length; i++) {
			values[i] = convert(values[i], from, to);
		}
	}

	
}
