package com.chinesedreamer.sso.authorization.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.sso.authorization.model.ApplicationGroup;
import com.chinesedreamer.sso.authorization.model.ApplicationKey;
import com.chinesedreamer.sso.authorization.repository.ApplicationGroupRepository;
import com.chinesedreamer.sso.authorization.repository.ApplicationKeyRepository;
import com.chinesedreamer.sso.util.EncryptionUtil;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{
	private Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);
	
	@Resource
	private ApplicationKeyRepository applicationKeyRepository;
	@Resource
	private ApplicationGroupRepository applicationGroupRepository;

	@Override
	public boolean applicationAuthorized(String applicationCode, String key) {
		ApplicationKey applicationKey = this.applicationKeyRepository.findByApplicationCode(applicationCode);
		if (null == applicationCode) {
			logger.info("ApplicationCode:{} not exist.", applicationCode);
			return false;
		}
		String encryptionKey = EncryptionUtil.md5L32(applicationCode + applicationKey.getApplicationKey());
		if (!key.equalsIgnoreCase(encryptionKey)) {
			logger.info("ApplicationCode:{} with incorrect key:{}.", applicationCode, key);
			return false;
		}
		return true;
	}

	@Override
	public ApplicationKey authorize(String applicationCode, String applicationName) {
		ApplicationKey ak = new ApplicationKey();
		ak.setApplicationCode(applicationCode);
		ak.setApplicationName(applicationName);
		ak.setApplicationKey(EncryptionUtil.md5L32(applicationCode + System.currentTimeMillis()));
		return this.applicationKeyRepository.save(ak);
	}

	@Override
	public ApplicationGroup groupApplication(String applicationCode, String applicationGroupCode) {
		ApplicationGroup ag = new ApplicationGroup();
		ag.setApplicationCode(applicationCode);
		ag.setGroupCode(applicationGroupCode);
		return this.applicationGroupRepository.save(ag);
	}

}
