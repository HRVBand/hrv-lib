package hrv.calc.parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		List<HRVParameter> result = this.stream().filter(p -> p.getType() == e).collect(Collectors.toList());

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}
}
