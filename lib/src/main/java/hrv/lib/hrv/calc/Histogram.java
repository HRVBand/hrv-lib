package hrv.lib.hrv.calc;

public class Histogram {

	private int numberOfBins;
	private final double[] observations;
	private int[] bins;
	private double max;
	private double min;

	/**
	 * Creates a new histogram from the given observations
	 * @param observations Observations with more than 1 entry
	 */
	public Histogram(double[] observations)
	{
		if(observations.length < 2)
			throw new IllegalArgumentException("Observations has to contain more than 1 Element!");
		
		this.observations = observations;
		
		searchMin();
		searchMax();		
		numberOfBins = (int) Math.round(Math.sqrt(observations.length));		
		createBins();
	}
	
	private void searchMin() {
		min = observations[0];
		
		for(int i = 1; i < observations.length; i++) {
			if(observations[i] < min)
				min = observations[i];
		}
	}
	
	private void searchMax() {
		max = observations[0];
		
		for(int i = 1; i < observations.length; i++) {
			if(observations[i] > max)
				max = observations[i];
		}
	}
	
	/** 
	* Returns the range of the given observation data.
	* @return the range of the given observation data.
	*/
	public double getRange()
	{
		return max - min;
	}
	
	public double getBinSize() {
		return getRange() / numberOfBins;
	}
	
	/**
	 * Sets the bin-size of the histogram. The bin-size has to be > 0.
	 * @param binSize Bin-Size > 0
	 */
	public void setBinSize(double binSize) {
		if(binSize <= 0)
			throw new IllegalArgumentException("Bin-Size has to be > 0");
		
		int tempBinNum = (int) (getRange() / binSize); 
		numberOfBins = tempBinNum == 0 ? 1 : tempBinNum;
		createBins();
	}
	
	private void createBins() {
		bins = new int[numberOfBins];

		for (double observation : observations) {

			//The max value always falls into a bin that is non existent and therefore belongs to
			//the last available bin.
			if (max - observation < Double.MIN_VALUE) {
				bins[bins.length - 1]++;
				continue;
			}

			//Calculate bin
			int bin = (int) ((observation - min) / getBinSize());
			bins[bin]++;
		}
	}
	
	public double getMode() {
		int maxBinValue = bins[0];
		int maxBinIndex = 0;
		
		for(int i = 1; i < bins.length; i++) {
			if(bins[i] > maxBinValue) {
				maxBinValue = bins[i];
				maxBinIndex = i;
			}
				
		}
		
		return min + maxBinIndex * getBinSize();
	}
	
	/**
	 * Number of the occurrences of the value that occurs most often.
	 * @return Number of the occurrences of the value that occurs most often.
	 */
	public int getAmplitudeMode() {
		int maxBinIndex = 0;
		
		for(int i = 1; i < bins.length; i++) {
			if(bins[i] > bins[maxBinIndex])
				maxBinIndex = i;
		}
		
		return bins[maxBinIndex];
	}

	public int[] getBins() {
		return bins;
	}
}
