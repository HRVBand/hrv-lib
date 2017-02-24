package hrv.calc.manipulator;

import java.util.ArrayList;
import java.util.List;

import common.ArrayUtils;
import hrv.RRData;

public class HRVCleanRRDataByLimits implements HRVDataManipulator {

	private double lowerRRLimit = 0.2; // = 300 beats per minute
	private double upperRRLimit = 3.0; // = 20 beats per minute
	
	
	@Override
	public RRData manipulate(RRData data) {

		double[] oldRRY = data.getValueAxis();
		
		List<Integer> indicesToRemove = getIndicesToRemoveByLimit(oldRRY);
				
		double[] newRRY = removeIndices(oldRRY, indicesToRemove);
		
		return RRData.createFromRRInterval(newRRY, data.getValueAxisUnit());
	}
	
	private List<Integer> getIndicesToRemoveByLimit(double[] data) {
		List<Integer> indicesToRemove = new ArrayList<>();
		
		for(int i = 0; i < data.length; i++) {
			if(data[i] < lowerRRLimit || data[i] > upperRRLimit) {
				indicesToRemove.add(i);
			}
		}
		
		return indicesToRemove;
	}

	private double[] removeIndices(double[] data, List<Integer> indicesToRemove) {
		List<Double> newData = new ArrayList<>();
		
		for(int i = 0; i < data.length; i++) {
			if(!indicesToRemove.contains(i)) {
				newData.add(data[i]);	
			}			
		}
		
		return ArrayUtils.toPrimitive(newData.toArray(new Double[0]));
	}
}
