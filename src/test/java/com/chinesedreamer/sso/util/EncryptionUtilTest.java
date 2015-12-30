package com.chinesedreamer.sso.util;

import org.junit.Test;

public class EncryptionUtilTest {

	@Test
	public void testMd5L32() {
		System.out.println(EncryptionUtil.md5L32("TEST2" + "9a52ef91554d162d437d637bbae6b72d"));
	}

//	@Test
//	public void testGenerateSalt() {
//		fail("Not yet implemented");
//	}

}
