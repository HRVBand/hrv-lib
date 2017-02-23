package hrv.calc;

/**
 * Listener for Inter-Beat-Interval-Data
 * 
 * @author Julian
 *
 */
@FunctionalInterface
public interface HRVIBIListener {

	public void ibiMeasured(double ibitime);
}
