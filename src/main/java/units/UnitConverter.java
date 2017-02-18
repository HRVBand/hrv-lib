package units;

public interface UnitConverter {

	/**
	 * Converts the given {@code value} from the given unit {@code from} to the other give unit {@code to}
	 * @param value Value to convert
	 * @param from Unit to convert from
	 * @param to Unit to convert to
	 * @return Converted value
	 */
	public double convert(double value, String from, String to);
	

	/**
	 * Converts the given {@code values} from the given unit {@code from} to the other give unit {@code to}
	 * @param value Value to convert
	 * @param from Unit to convert from
	 * @param to Unit to convert to
	 */
	public void convert(double[] values, String from, String to);
}
