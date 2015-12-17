package com.chinesedreamer.sso.user.repository;

import com.chinesedreamer.sso.base.jpa.repository.BaseRepository;
import com.chinesedreamer.sso.user.model.User;

public interface UserRepository extends BaseRepository<User, Long>{
	public User findByUsername(String username);
}
