package hrv.lib.hrv.files;

import hrv.lib.common.ArrayUtils;
import hrv.lib.hrv.RRData;
import hrv.lib.units.TimeUnit;

import java.io.*;
import java.util.*;

public class HRVIBIFileReader {

	/**
	 * Reads the .ibi file that is specified in {@code filePath} and
	 * returns the data in form of a {@code List<Double>}
	 * @param filePath File to read
	 * @return Read data.
	 * @throws IOException throws IOException
	 */
	public RRData readIBIFile(String filePath, TimeUnit unit) throws IOException {
		List<Double> rr = new ArrayList<>();
		
		try(var reader = new BufferedReader(new FileReader(filePath))) {
			for(String line; (line = reader.readLine()) != null; ) {
				rr.add(Double.parseDouble(line));
			}
		}
		
		return RRData.createFromRRInterval(ArrayUtils.toPrimitive(rr, 0.0), unit);
	}
}
