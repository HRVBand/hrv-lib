package hrv;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import hrv.calc.manipulator.HRVCleanRRDataByLimits;
import hrv.calc.manipulator.HRVCutToPowerTwoDataManipulator;
import hrv.calc.manipulator.HRVMultiDataManipulator;
import hrv.calc.manipulator.HRVSplineInterpolator;
import hrv.calc.manipulator.HRVSubstractMeanManipulator;
import hrv.calc.parameter.AmplitudeModeCalculator;
import hrv.calc.parameter.BaevskyCalculator;
import hrv.calc.parameter.HFCalculator;
import hrv.calc.parameter.HRVDataProcessor;
import hrv.calc.parameter.HRVParameter;
import hrv.calc.parameter.HRVParameterEnum;
import hrv.calc.parameter.LFCalculator;
import hrv.calc.parameter.MeanCaclulator;
import hrv.calc.parameter.ModeCalculator;
import hrv.calc.parameter.MxDMnCalculator;
import hrv.calc.parameter.NN50Calculator;
import hrv.calc.parameter.PNN50Calculator;
import hrv.calc.parameter.RMSSDCalculator;
import hrv.calc.parameter.SD1Calculator;
import hrv.calc.parameter.SD1SD2Calculator;
import hrv.calc.parameter.SD2Calculator;
import hrv.calc.parameter.SDNNCalculator;
import hrv.calc.psd.PowerSpectrum;
import hrv.calc.psd.PowerSpectrumIntegralCalculator;
import hrv.calc.psd.StandardPowerSpectralDensityEstimator;

public class HRVLibFacade {

	private EnumSet<HRVParameterEnum> frequencyParams = EnumSet.of(HRVParameterEnum.LFHF, HRVParameterEnum.LF,
			HRVParameterEnum.HF);

	private double lfLowerBound = 0.04;
	private double lfUpperBound = 0.15;
	private double hfLowerBound = 0.15;
	private double hfUpperBound = 0.4;

	public void setLfLowerBound(double value) {
		this.lfLowerBound = value;
	}

	public void setLfUpperBound(double value) {
		this.lfUpperBound = value;
	}

	public void setHfLowerBound(double value) {
		this.hfLowerBound = value;
	}

	public void setHfUpperBound(double value) {
		this.hfUpperBound = value;
	}

	public List<HRVParameter> calculate(Set<HRVParameterEnum> parameters, RRData data) {

		List<HRVParameter> allParams = new ArrayList<>();
		List<HRVDataProcessor> allCalculators = getAllHRVDataProcessors(parameters);

		for (HRVDataProcessor p : allCalculators) {
			allParams.add(p.process(data));
		}

		if (containsOne(frequencyParams, parameters)) {
			allParams.addAll(calculateFrequencyParams(parameters, data));
		}

		return allParams;
	}

	private List<HRVParameter> calculateFrequencyParams(Set<HRVParameterEnum> params, RRData data) {

		String unit = data.getTimeAxisUnit().toString() + "*" + data.getTimeAxisUnit().toString();

		List<HRVParameter> allParameters = new ArrayList<>();

		HRVParameter lf = null;
		HRVParameter hf = null;
		PowerSpectrum ps = getPowerSpectrum(data);

		if (params.contains(HRVParameterEnum.LF) || params.contains(HRVParameterEnum.LFHF)) {
			LFCalculator calcLF = new LFCalculator();
			lf = calcLF.process(ps);
			allParameters.add(lf);
		}

		if (params.contains(HRVParameterEnum.HF) || params.contains(HRVParameterEnum.LFHF)) {
			HFCalculator calcHF = new HFCalculator();
			hf = calcHF.process(ps);
			allParameters.add(hf);
		}

		if (params.contains(HRVParameterEnum.LFHF) && lf != null && hf != null) {
			allParameters.add(new HRVParameter(HRVParameterEnum.LFHF, lf.getValue() / hf.getValue(), ""));
		}

		return allParameters;
	}

	public PowerSpectrum getPowerSpectrum(RRData data) {

		HRVMultiDataManipulator mani = new HRVMultiDataManipulator();
		mani.addManipulator(new HRVCleanRRDataByLimits());
		mani.addManipulator(new HRVSplineInterpolator(4));
		mani.addManipulator(new HRVCutToPowerTwoDataManipulator());
		mani.addManipulator(new HRVSubstractMeanManipulator());
		RRData manipulatedData = mani.manipulate(data);

		StandardPowerSpectralDensityEstimator estimator = new StandardPowerSpectralDensityEstimator();
		return estimator.calculateEstimate(manipulatedData);
	}

	private <T extends Enum<T>> boolean containsOne(Set<T> setToTest, Set<T> set) {

		for (Object e : set) {
			if (setToTest.contains(e)) {
				return true;
			}
		}

		return false;
	}

	private List<HRVDataProcessor> getAllHRVDataProcessors(Set<HRVParameterEnum> params) {
		List<HRVDataProcessor> processors = new ArrayList<>();

		for (HRVParameterEnum param : params) {
			HRVDataProcessor processor = getHRVDataProcessor(param);
			if (processor != null) {
				processors.add(processor);
			}
		}

		return processors;
	}

	private HRVDataProcessor getHRVDataProcessor(HRVParameterEnum e) {
		switch (e) {
		case AMPLITUDEMODE:
			return new AmplitudeModeCalculator();
		case BAEVSKY:
			return new BaevskyCalculator();
		case MEAN:
			return new MeanCaclulator();
		case MODE:
			return new ModeCalculator();
		case MXDMN:
			return new MxDMnCalculator();
		case NN50:
			return new NN50Calculator();
		case PNN50:
			return new PNN50Calculator();
		case RMSSD:
			return new RMSSDCalculator();
		case SDNN:
			return new SDNNCalculator();
		case SD1:
			return new SD1Calculator();
		case SD2:
			return new SD2Calculator();
		case SDSD:
			return new SD1SD2Calculator();
		default:
			return null;
		}
	}
}
