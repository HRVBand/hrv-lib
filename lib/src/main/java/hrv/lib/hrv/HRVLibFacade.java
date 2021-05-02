package hrv.lib.hrv;

import hrv.lib.hrv.calc.manipulator.*;
import hrv.lib.hrv.calc.parameter.*;
import hrv.lib.hrv.calc.psd.PowerSpectrum;
import hrv.lib.hrv.calc.psd.StandardPowerSpectralDensityEstimator;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Facade for the HRV-Lib Library.
 * 
 * The Method {@code calculateParameters} calculates a set of parameters defined
 * in the {@code parameters} field. By default, the following Parameters are
 * calculated: Baevsky, HF, HFnu, LF, LFnu, VLF, NN50, PNN50, RMSSD, SD1, SD2,
 * SD1SD2, SDNN, SDSD. Use {@code setParameters} to change the calculated
 * HRV-Parameters
 * 
 * The method {@code calculatePowerSpecturm} calculates the power spectrum of
 * the given RR-Data. Before that some preprocessing of the data is done.
 * 
 * @author Julian
 *
 */
public class HRVLibFacade {

	private final EnumSet<HRVParameterEnum> frequencyParams = EnumSet.of(HRVParameterEnum.LFHF, HRVParameterEnum.LF,
			HRVParameterEnum.HF, HRVParameterEnum.VLF);

	private Set<HRVParameterEnum> parameters = EnumSet.of(HRVParameterEnum.BAEVSKY, HRVParameterEnum.HF,
			HRVParameterEnum.LF, HRVParameterEnum.NN50, HRVParameterEnum.PNN50, HRVParameterEnum.RMSSD,
			HRVParameterEnum.SD1, HRVParameterEnum.SD2, HRVParameterEnum.SD1SD2, HRVParameterEnum.SDNN,
			HRVParameterEnum.SDSD, HRVParameterEnum.VLF);

	private final RRData data;
	private final HRVMultiDataManipulator frequencyDataManipulator = new HRVMultiDataManipulator();
	private final HRVMultiDataManipulator filters = new HRVMultiDataManipulator();

	public HRVLibFacade(RRData data) {
		this.data = data;

		frequencyDataManipulator.addManipulator(new HRVSplineInterpolator(4));
		frequencyDataManipulator.addManipulator(new HRVCutToPowerTwoDataManipulator());
		frequencyDataManipulator.addManipulator(new HRVSubtractMeanManipulator());
	}

	public void setParameters(Set<HRVParameterEnum> parameters) {
		this.parameters = parameters;
	}
	
	
	public void addDataFilter(HRVDataManipulator filter) {
		this.filters.addManipulator(filter);
	}

	/**
	 * Returns whether the RRData is valid.
	 * @return whether the RRData is valid.
	 */
	public boolean validData() {
		return data.getValueAxis().length > 2;
	}
	
	/**
	 * Calculates the HRV-Parameters specified in {@code parameters} for the
	 * given RR-Data.
	 * 
	 * @return List of HRV-Parameters
	 */
	public List<HRVParameter> calculateParameters() {

		RRData filteredData = filters.manipulate(data);
		
		List<HRVParameter> allParams = new ArrayList<>();
		List<HRVDataProcessor> allCalculators = getAllHRVDataProcessors();

		for (HRVDataProcessor p : allCalculators) {
			allParams.add(p.process(filteredData));
		}

		if (containsOne(frequencyParams, parameters)) {
			allParams.addAll(calculateFrequencyParams(filteredData));
		}

		return allParams;
	}

	/**
	 * Calculates the power spectrum of the given data.
	 * 
	 * @param data
	 *            Data to calculate the power spectrum from
	 * @return power spectrum
	 */
	public PowerSpectrum getPowerSpectrum(RRData data) {
		RRData manipulatedData = frequencyDataManipulator.manipulate(data);
		StandardPowerSpectralDensityEstimator estimator = new StandardPowerSpectralDensityEstimator();
		return estimator.calculateEstimate(manipulatedData);
	}

	private List<HRVParameter> calculateFrequencyParams(RRData data) {

		List<HRVParameter> allParameters = new ArrayList<>();

		HRVParameter lf = null;
		HRVParameter hf = null;
		HRVParameter vlf;
		PowerSpectrum ps = getPowerSpectrum(data);

		if (parameters.contains(HRVParameterEnum.LF) || parameters.contains(HRVParameterEnum.LFHF)) {
			LFCalculator calcLF = new LFCalculator();
			lf = calcLF.process(ps);
			allParameters.add(lf);
		}

		if (parameters.contains(HRVParameterEnum.HF) || parameters.contains(HRVParameterEnum.LFHF)) {
			HFCalculator calcHF = new HFCalculator();
			hf = calcHF.process(ps);
			allParameters.add(hf);
		}

		if (parameters.contains(HRVParameterEnum.VLF)) {
			VLFCalculator calcVLF = new VLFCalculator();
			vlf = calcVLF.process(ps);
			allParameters.add(vlf);
		}

		if (parameters.contains(HRVParameterEnum.LFHF) && lf != null && hf != null) {
			allParameters.add(new HRVParameter(HRVParameterEnum.LFHF, lf.getValue() / hf.getValue(), ""));
		}

		return allParameters;
	}

	private static <T extends Enum<T>> boolean containsOne(Set<T> setToTest, Set<T> set) {

		for (Object e : set) {
			if (setToTest.contains(e)) {
				return true;
			}
		}

		return false;
	}

	private List<HRVDataProcessor> getAllHRVDataProcessors() {
		List<HRVDataProcessor> processors = new ArrayList<>();

		for (HRVParameterEnum param : parameters) {
			var processor = getHRVDataProcessor(param);
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
				return new MeanCalculator();
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
				return new SDSDCalculator();
			case SD1SD2:
				return new SD1SD2Calculator();
			default:
				return null;
		}
	}
}
