package hrv.calc.continous;

import java.util.ArrayList;
import java.util.List;

import common.ArrayUtils;
import common.RotatingMaxSizeList;
import hrv.HRVParameter;
import hrv.RRData;
import hrv.calc.HRVDataProcessor;
import hrv.calc.HRVIBIListener;
import units.TimeUnitConverter.TimeUnit;

public abstract class HRVContinousParameterCalculator implements HRVIBIListener, HRVDataProcessor{

	List<HRVParameterChangedListener> listeners = new ArrayList<>();
	RotatingMaxSizeList<Double> ibis;
	
	public HRVContinousParameterCalculator(int size) {
		ibis = new RotatingMaxSizeList<>(new Double[size]);
	}
	
	void addHRVParameterChangedListener(HRVParameterChangedListener listener) {
		listeners.add(listener);
	}
	
	void removeHRVParameterChangedListener(HRVParameterChangedListener listener) {
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
	public void ibiMeasured(double ibi) {
		ibis.add(ibi);
		
		RRData rrData = RRData.createFromRRInterval(ArrayUtils.toPrimitive(ibis.getArray()), TimeUnit.SECOND);
		notifyAll(process(rrData));
	}
	
	private void notifyAll(HRVParameter param) {
		for(HRVParameterChangedListener listener : listeners) {
			listener.parameterChanged(param);
		}
	}
}
