package hrv.lib.hrv.calc.manipulator;

import hrv.lib.hrv.RRData;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

/**
 * Interpolates given RR-Data with a given sampling-rate. The given data must at least contain 3 data-points.
 * @author Julian
 *
 */
public class HRVSplineInterpolator implements HRVDataManipulator {

	private final double samplingRate;

	public HRVSplineInterpolator(double samplingRate) {
		this.samplingRate = samplingRate;
	}
	
	@Override
	public RRData manipulate(RRData data) {

		double[] y = data.getValueAxis();
		double[] x = data.getTimeAxis();

		// Interpolate data
		SplineInterpolator interpolator = new SplineInterpolator();

		PolynomialSplineFunction interpolFunction = interpolator.interpolate(x, y);

		// Calculate number of data points to be sampled for the given sampling
		// rate
		double biggestXValue = x[x.length - 1];
		int numInterpolVals = (int) (biggestXValue * samplingRate);

		// Sample interpolated data
		double[] xInterpolated = new double[numInterpolVals];
		double[] yInterpolated = new double[numInterpolVals];

		// Sampling Step Size in seconds
		double stepSize = 1 / samplingRate;

		for (int i = 0; i < numInterpolVals; i++) {
			xInterpolated[i] = stepSize * i;
			yInterpolated[i] = interpolFunction.value(xInterpolated[i]);
		}

		return new RRData(xInterpolated, data.getTimeAxisUnit(), yInterpolated, data.getValueAxisUnit());
	}
}
