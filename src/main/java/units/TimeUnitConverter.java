package units;

import java.util.HashMap;

public class TimeUnitConverter implements UnitConverter {

	/**
	 * Contains unit conversion factors relative to convert a unit to the unit s.
	 */
	HashMap<String, Double> unitKonvertions = new HashMap<>();
	
	public TimeUnitConverter() {
		unitKonvertions.put("d", 60.0 * 60 * 24);
		unitKonvertions.put("h", 60.0 * 60);
		unitKonvertions.put("min", 60.0);
		unitKonvertions.put("s", 1.0);
		unitKonvertions.put("ms", 0.001);
	}
	
	@Override
	public double convert(double value, String from, String to) {
		
		double valInS = value * unitKonvertions.get(from);
		return valInS / unitKonvertions.get(to);
	}

	@Override
	public void convert(double[] values, String from, String to) {
		
		for(int i = 0; i < values.length; i++) {
			values[i] = convert(values[i], from, to);
		}
	}

}
