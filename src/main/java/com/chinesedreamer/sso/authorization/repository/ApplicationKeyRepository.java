package com.chinesedreamer.sso.authorization.repository;

import com.chinesedreamer.sso.authorization.model.ApplicationKey;
import com.chinesedreamer.sso.base.jpa.repository.BaseRepository;

public interface ApplicationKeyRepository extends BaseRepository<ApplicationKey, Long>{
	
	public ApplicationKey findByApplicationCode(String applicationCode);
}
