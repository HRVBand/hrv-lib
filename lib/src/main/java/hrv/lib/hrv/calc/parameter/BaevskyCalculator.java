package hrv.lib.hrv.calc.parameter;

import hrv.lib.hrv.RRData;
import hrv.lib.hrv.calc.Histogram;

/**
 * Calculates the Baevsky Stress Index corresponding to http://rainer-bartosch.de/HRV/Fachseminar_IMD_HRV.pdf
 * Normal values should be between 50 and 150. The value has no unit and is often just called SI.
 *
 * @author Julian
 */
public class BaevskyCalculator implements HRVDataProcessor {

    @Override
    public HRVParameter process(RRData rrInterval) {

        var histogram = new Histogram(rrInterval.getValueAxis());

        return new HRVParameter(HRVParameterEnum.BAEVSKY,
                histogram.getAmplitudeMode() / (2 * histogram.getMode() * histogram.getRange()),
                "non");
    }


    @Override
    public boolean validData(RRData data) {
        return data.getValueAxis().length > 0;
    }
}
