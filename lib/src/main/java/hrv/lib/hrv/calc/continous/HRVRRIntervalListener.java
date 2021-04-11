package hrv.lib.hrv.calc.continous;

/**
 * Listener for Inter-Beat-Interval-Data
 * Copyright (c) 2017
 * @author Julian
 *
 * Created by Julian Martin on 03.01.2016.
 */
@FunctionalInterface
public interface HRVRRIntervalListener {

    void newRRInterval(HRVRRIntervalEvent event);
}
