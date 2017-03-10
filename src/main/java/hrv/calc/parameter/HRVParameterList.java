package hrv.calc.parameter;

import java.util.ArrayList;

public class HRVParameterList extends ArrayList<HRVParameter> {

	/**
	 * Returns the first found HRV-Parameter of the given type, null if there is
	 * no such HRV-Parameter
	 * 
	 * @param e
	 * @return the first found HRV-Parameter of the given type, null if there is
	 * no such HRV-Parameter
	 */
	public HRVParameter getHRVParameter(HRVParameterEnum e) {
		
		for(HRVParameter p : this) {
			if(p.getType() == e) {
				return p;
			}
		}
		
		return null;
	}
}
