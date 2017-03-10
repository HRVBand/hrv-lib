package hrv.calc.manipulator;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

import hrv.RRData;

public class HRVSplineInterpolator implements HRVDataManipulator {

	private double samplingRate;

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
