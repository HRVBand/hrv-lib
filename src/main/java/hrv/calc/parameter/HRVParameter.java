package hrv.calc.parameter;

public class HRVParameter {

	private HRVParameterEnum type;
	private String unit;
	private double value;
	
	/**
	 * Creates a new HRV-Parameter
	 */
	public HRVParameter() { 
		// Just empty constructor
	}

	/**
	 * Creates a new HRV-Parameter
	 * @param name Name of the parameter
	 * @param value Value of the parameter
	 * @param unit Unit of the parameter
	 */
	public HRVParameter(HRVParameterEnum type, double value, String unit) {
		this.type = type;
		this.value = value;
		this.setUnit(unit);
	}
	
	public String getName() {
		return type.toString();
	}
	
	public HRVParameterEnum getType() {
		return type;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * Returns a string with the following format:
	 * <Name>: <Value> <Unit>
	 */
	@Override
	public String toString() {
		return type.toString() + ": " + value + " " + unit;
	}
}
