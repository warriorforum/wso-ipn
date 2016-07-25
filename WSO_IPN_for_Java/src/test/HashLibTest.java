package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ipn.HashLib;

public class HashLibTest {

	@Test
	public void testSha1Hash() {
		String sampleString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Curabitur et pulvinar augue. Curabitur vestibulum lectus urna, vel congue nisl ";
		String expected = "19a44adb2133ceb1e58eb130b94468e86f16ea08";
		String actual = HashLib.sha1Hash(sampleString);
		assertEquals(expected,actual);
	}

}
