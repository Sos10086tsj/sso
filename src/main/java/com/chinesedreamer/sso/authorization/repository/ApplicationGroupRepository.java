package com.chinesedreamer.sso.authorization.repository;

import java.util.List;

import com.chinesedreamer.sso.authorization.model.ApplicationGroup;
import com.chinesedreamer.sso.base.jpa.repository.BaseRepository;

public interface ApplicationGroupRepository extends BaseRepository<ApplicationGroup, Long>{
	public ApplicationGroup findByApplicationCode(String applicationCode);
	public List<ApplicationGroup> findByGroupCode(String groupCode);
}
