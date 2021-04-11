package hrv.lib.units;

public interface UnitConverter<T extends Enum<T>> {

	/**
	 * Converts the given {@code value} from the given unit {@code from} to the other give unit {@code to}
	 * @param value Value to convert
	 * @param from Unit to convert from
	 * @param to Unit to convert to
	 * @return Converted value
	 */
	double convert(double value, T from, T to);
	

	/**
	 * Converts the given {@code values} from the given unit {@code from} to the other give unit {@code to}
	 * @param values Values to convert
	 * @param from Unit to convert from
	 * @param to Unit to convert to
	 */
	void convert(double[] values, T from, T to);
}
