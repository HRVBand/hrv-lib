package hrv.calc.continous;

import java.util.ArrayList;
import java.util.List;

import common.ArrayUtils;
import common.RotatingMaxSizeList;
import hrv.HRVParameter;
import hrv.RRData;
import hrv.calc.HRVDataProcessor;
import units.TimeUnitConverter.TimeUnit;

public abstract class HRVContinousParameterCalculator implements HRVRRIntervalListener, HRVDataProcessor{

	List<HRVParameterChangedListener> listeners = new ArrayList<>();
	RotatingMaxSizeList<Double> ibis;
	
	public HRVContinousParameterCalculator(int size) {
		ibis = new RotatingMaxSizeList<>(new Double[size]);
	}
	
	public void addHRVParameterChangedListener(HRVParameterChangedListener listener) {
		listeners.add(listener);
	}
	
	public void removeHRVParameterChangedListener(HRVParameterChangedListener listener) {
		if(listeners.contains(listener)) {
			listeners.remove(listener);	
		}
	}

	/**
	 * Takes a new Inter-Beat-Interval, adds it to existing data, calculates the new parameter and informs
	 * all listeners.
	 * The given {@code ibi} must be in seconds.
	 */
	@Override
	public void newRRInterval(HRVRRIntervalEvent eventArgs) {
		ibis.add(eventArgs.getRr());
		Double[] ibisArray = ibis.getArray();
		double[] ibisArrayPrimitive = ArrayUtils.toPrimitive(ibisArray);
		RRData rrData = RRData.createFromRRInterval(ibisArrayPrimitive, TimeUnit.SECOND);
		notifyAll(process(rrData));
	}
	
	private void notifyAll(HRVParameter param) {
		for(HRVParameterChangedListener listener : listeners) {
			listener.parameterChanged(param);
		}
	}
}
