package com.chinesedreamer.sso.session.repository;

import java.util.List;

import com.chinesedreamer.sso.base.jpa.repository.BaseRepository;
import com.chinesedreamer.sso.session.model.SsoSession;

public interface SsoSessionRepository extends BaseRepository<SsoSession, Long>{
	public SsoSession findByApplicationCodeAndUsername(String applicationCode, String username);
	public List<SsoSession> findByApplicationGroupAndUsername(String applicationGroup, String username);
}
