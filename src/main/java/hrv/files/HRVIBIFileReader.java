package hrv.files;

import java.io.*;
import java.util.*;

import common.ArrayUtils;
import hrv.RRData;
import units.TimeUnitConverter.TimeUnit;

public class HRVIBIFileReader {

	/**
	 * Reads the .ibi file that is specified in {@code filePath} and
	 * returns the data in form of a {@code List<Double>}
	 * @param filePath File to read
	 * @return Read data.
	 * @throws IOException
	 */
	public RRData readIBIFile(String filePath, TimeUnit unit) throws IOException {
		List<Double> rr = new ArrayList<>();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			for(String line; (line = reader.readLine()) != null; ) {
				rr.add(Double.parseDouble(line));
			}
		}
		
		return createFromRRInterval(ArrayUtils.listToArray(rr), unit);
	}
	
	private RRData createFromRRInterval(double[] rrInterval, TimeUnit unit) {
		double[] rrTimeAxis = new double[rrInterval.length];
		for (int i = 1; i < rrInterval.length; i++) {
			rrTimeAxis[i] = rrTimeAxis[i - 1] + rrInterval[i - 1];
		}

		return new RRData(rrTimeAxis, unit, rrInterval, unit);
	}
}
