package hrv;
import static org.junit.Assert.*;

import java.util.EnumSet;
import java.util.List;

import org.junit.Test;

import hrv.HRVLibFacade;
import hrv.RRData;
import hrv.calc.parameter.HRVParameter;
import hrv.calc.parameter.HRVParameterEnum;
import units.TimeUnit;

public class HRVLibFacadeTest {

	@Test
	public void testDefaultParamCalculation() {
		RRData data = RRData.createFromRRInterval(new double[] {1,1,1,1,1,1,1,1}, TimeUnit.SECOND);
		
		HRVLibFacade facade = new HRVLibFacade(data);
		List<HRVParameter> result = facade.calculateParameters();
		
		assertEquals(12, result.size());
	}
	
	@Test
	public void testParamCalculation() {
		RRData data = RRData.createFromRRInterval(new double[] {1,1,1,1,1,1,1,1}, TimeUnit.SECOND);
		
		HRVLibFacade facade = new HRVLibFacade(data);
		facade.setParameters(EnumSet.of(HRVParameterEnum.HF));
		List<HRVParameter> result = facade.calculateParameters();
		assertEquals(1, result.size());
		assertEquals(HRVParameterEnum.HF, result.get(0).getType());
		
		facade.setParameters(EnumSet.of(HRVParameterEnum.RMSSD));
		List<HRVParameter> result2 = facade.calculateParameters();
		assertEquals(1, result2.size());
		assertEquals(HRVParameterEnum.RMSSD, result2.get(0).getType());		
		

		facade.setParameters(EnumSet.of(HRVParameterEnum.HF, HRVParameterEnum.LF));
		List<HRVParameter> result3 = facade.calculateParameters();
		assertEquals(2, result3.size());
		assertEquals(HRVParameterEnum.LF, result3.get(0).getType());
		assertEquals(HRVParameterEnum.HF, result3.get(1).getType());

		facade.setParameters(EnumSet.of(HRVParameterEnum.HF, HRVParameterEnum.LF, HRVParameterEnum.VLF));
		List<HRVParameter> result4 = facade.calculateParameters();
		assertEquals(3, result4.size());
		assertEquals(HRVParameterEnum.HF, result4.get(0).getType());
		assertEquals(HRVParameterEnum.LF, result4.get(1).getType());
		assertEquals(HRVParameterEnum.VLF, result4.get(2).getType());
	}
}
