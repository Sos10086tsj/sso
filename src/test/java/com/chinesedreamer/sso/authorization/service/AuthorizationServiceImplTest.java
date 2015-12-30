package com.chinesedreamer.sso.authorization.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.chinesedreamer.sso.authorization.model.ApplicationGroup;
import com.chinesedreamer.sso.authorization.model.ApplicationKey;
import com.chinesedreamer.sso.test.base.BaseTest;

public class AuthorizationServiceImplTest extends BaseTest{
	
	@Resource
	private AuthorizationService authorizationService;

//	@Test
//	public void testApplicationAuthorized() {
//		fail("Not yet implemented");
//	}

	@Test
	@Rollback(value = false)
	public void testAuthorize() {
		ApplicationKey ak = this.authorizationService.authorize("TEST2", "测试应用2");
		assertNotNull(ak);
		System.out.println(ak);
	}
	
	@Test
	@Rollback(value = false)
	public void testGroupApplication() {
		ApplicationGroup ag = this.authorizationService.groupApplication("TEST2", "GROUP1");
		assertNotNull(ag);
		System.out.println(ag);
	}

}
