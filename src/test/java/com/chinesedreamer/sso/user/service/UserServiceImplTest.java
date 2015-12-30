package com.chinesedreamer.sso.user.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import com.chinesedreamer.sso.test.base.BaseTest;
import com.chinesedreamer.sso.user.constant.UserStatus;
import com.chinesedreamer.sso.user.model.User;

public class UserServiceImplTest extends BaseTest{
	
	@Resource
	private UserService userService;

	@Test
	@Rollback(value = false)
	public void testRegister() {
		User user = this.userService.register(this.mockUser());
		Assert.notNull(user);
		System.out.println(user);
	}
	
	private User mockUser(){
		User user = new User();
		user.setUsername("test2");
		user.setName("测试2");
		user.setStatus(UserStatus.ACTIVE);
		user.setEmail("test2@test.com");
		user.setPassword("123456");
		return user;
	}

}
