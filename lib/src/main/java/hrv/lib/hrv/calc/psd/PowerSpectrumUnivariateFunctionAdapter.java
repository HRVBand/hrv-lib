package hrv.lib.hrv.calc.psd;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class PowerSpectrumUnivariateFunctionAdapter implements UnivariateFunction {

	private PolynomialSplineFunction interpolFunction;
	
	public PowerSpectrumUnivariateFunctionAdapter(PowerSpectrum ps) {
		
		SplineInterpolator interpolator = new SplineInterpolator();	
		interpolFunction = interpolator.interpolate(ps.getFrequency(), ps.getPower());
	}
	
	@Override
	public double value(double x) {
		return interpolFunction.value(x);		
	}

}
