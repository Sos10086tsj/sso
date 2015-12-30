package com.chinesedreamer.sso.access.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinesedreamer.sso.test.base.BaseTest;

public class AccessServiceImplTest extends BaseTest{

	@Resource
	private AccessService accessService;

	@Test
	public void testGetSsoUserSession(){
		this.accessService.getSsoUserSession("TEST1", "test1", "1234-5678-9012-3456", "127.0.0.1");
	}
}
