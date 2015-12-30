package com.chinesedreamer.sso.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.sso.user.model.User;
import com.chinesedreamer.sso.user.repository.UserRepository;
import com.chinesedreamer.sso.util.EncryptionUtil;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserRepository repository;

	@Override
	public User register(User user) {
		String salt = EncryptionUtil.generateSalt(6);
		user.setSalt(salt);
		user.setPassword(EncryptionUtil.md5L32(user.getPassword() + salt));
		return this.repository.save(user);
	}

}
