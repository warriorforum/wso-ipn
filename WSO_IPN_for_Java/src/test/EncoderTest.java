package test;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import ipn.Encoder;

public class EncoderTest {
	
	@Test
	public void testEncodeString() {
		String sampleString = "lorem ipsum";
		String expected = "lorem+ipsum";
		String actual = Encoder.urlEncodeUTF8(sampleString);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEncodeSpecialChar(){
		String sampleString = "<>@#$%^&()~;\"&?/";
		String expected = "%3C%3E%40%23%24%25%5E%26%28%29%7E%3B%22%26%3F%2F";
		String actual = Encoder.urlEncodeUTF8(sampleString);
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testEncodeMap(){
		Map<String, String> sampleMap = new TreeMap<String,String>();
		sampleMap.put("Lorem ipsum", "dolor sit amet,");
		sampleMap.put("consectetur adipiscing elit.", "Curabitur et pulvinar augue.");
		String expected = "Lorem+ipsum=dolor+sit+amet%2C&consectetur+adipiscing+elit.=Curabitur+et+pulvinar+augue.";
		String actual = Encoder.urlEncodeUTF8(sampleMap);
		assertEquals(expected, actual);
	}

}
