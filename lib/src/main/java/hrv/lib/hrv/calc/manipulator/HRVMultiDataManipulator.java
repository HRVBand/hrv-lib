package hrv.lib.hrv.calc.manipulator;

import hrv.lib.hrv.RRData;

import java.util.ArrayList;
import java.util.List;

public class HRVMultiDataManipulator implements HRVDataManipulator {

	List<HRVDataManipulator> manipulators = new ArrayList<>();
	
	@Override
	public RRData manipulate(RRData data) {
		
		RRData currentData = data;		
		for(HRVDataManipulator mani : manipulators) {
			currentData = mani.manipulate(currentData);
		}

		return currentData;
	}
	
	public void addManipulator(HRVDataManipulator manipulator) {
		manipulators.add(manipulator);
	}
}
