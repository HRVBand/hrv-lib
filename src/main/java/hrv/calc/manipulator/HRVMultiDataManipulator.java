package hrv.calc.manipulator;

import java.util.ArrayList;
import java.util.List;

import hrv.RRData;

public class HRVMultiDataManipulator implements HRVDataManipulator {

	List<HRVDataManipulator> manipulators = new ArrayList<>();
	
	@Override
	public void manipulate(RRData data) {
		for(HRVDataManipulator mani : manipulators) {
			mani.manipulate(data);
		}
	}
	
	public void addManipulator(HRVDataManipulator manipulator) {
		manipulators.add(manipulator);
	}
}
