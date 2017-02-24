package hrv.calc.manipulator;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import hrv.RRData;
import hrv.files.HRVIBIFileReader;
import units.TimeUnitConverter.TimeUnit;

public class HRVCleanRRDataByLimitTest {

	private RRData dataWithDefects;
	private RRData cleanedData;
	
	@Before
	public void loadData() throws IOException {
		HRVIBIFileReader reader = new HRVIBIFileReader();
		
		Path currentDirectory = Paths.get(System.getProperty("user.dir"));
		Path sampleDataFolderPath = Paths.get(currentDirectory.toString(), "SampleData");
		
		Path dataWithDefectsPath = Paths.get(sampleDataFolderPath.toString(), "RR1_With_Artifacts.ibi");
		Path cleandDataPath = Paths.get(sampleDataFolderPath.toString(), "RR1_Cleaned.ibi");
		
		dataWithDefects = reader.readIBIFile(dataWithDefectsPath.toString(), TimeUnit.SECOND);
		cleanedData = reader.readIBIFile(cleandDataPath.toString(), TimeUnit.SECOND);
	}
	
	@Test
	public void testFunction() {
		
		HRVCleanRRDataByLimits cleaner = new HRVCleanRRDataByLimits();
		RRData hereCleanedData = cleaner.manipulate(dataWithDefects);
		
		assertEquals(cleanedData.getTimeAxis().length, hereCleanedData.getTimeAxis().length);
		
		if(cleanedData.getTimeAxis().length == hereCleanedData.getTimeAxis().length) {
			return;
		}			
		
		for(int i = 0; i < hereCleanedData.getValueAxis().length; i++) {
			assertEquals(hereCleanedData.getValueAxis()[i], cleanedData.getValueAxis()[i], 0.000001);
		}
	}
}
