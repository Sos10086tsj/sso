package com.chinesedreamer.sso.session.repository;

import com.chinesedreamer.sso.base.jpa.repository.BaseRepository;
import com.chinesedreamer.sso.session.model.SsoSession;

public interface SsoSessionRepository extends BaseRepository<SsoSession, Long>{
	public SsoSession findByToken(String token);
}
