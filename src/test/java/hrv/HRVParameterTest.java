package hrv;

import static org.junit.Assert.*;

import org.junit.Test;

public class HRVParameterTest {

	@Test
	public void creationTests() {
		
		HRVParameter param1 = new HRVParameter();		
		assertEquals(0.0, param1.getValue(), 0.000001);
		assertEquals(null, param1.getUnit());
		assertEquals(null, param1.getName());		
		
		param1.setName("Param1");
		assertEquals("Param1", param1.getName());	
		param1.setUnit("ms");
		assertEquals("ms", param1.getUnit());
		param1.setValue(1.0);
		assertEquals(1.0, param1.getValue(), 0.000001);
		
		HRVParameter param2 = new HRVParameter("Param2", 2.0, "s");		
		assertEquals(2.0, param2.getValue(), 0.000001);
		assertEquals("s", param2.getUnit());
		assertEquals("Param2", param2.getName());		
	}
}
