package hrv.calc.manipulator;

import hrv.RRData;

@FunctionalInterface
public interface HRVDataManipulator {

	/**
	 * Manipulates the given data and returns the manipulated data. The given data must at least contain three data-points.
	 * @param data data to manipulate.
	 * @return Manipulated data.
	 */
	public RRData manipulate(RRData data);
}
