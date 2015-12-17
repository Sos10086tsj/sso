package com.chinesedreamer.sso.authorization.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.sso.authorization.model.ApplicationKey;
import com.chinesedreamer.sso.authorization.repository.ApplicationKeyRepository;
import com.chinesedreamer.sso.util.EncryptionUtil;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{
	private Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);
	
	@Resource
	private ApplicationKeyRepository repository;

	@Override
	public boolean applicationAuthorized(String applicationCode, String key) {
		ApplicationKey applicationKey = this.repository.findByApplicationCode(applicationCode);
		if (null == applicationCode) {
			logger.info("ApplicationCode:{} not exist.", applicationCode);
			return false;
		}
		String encryptionKey = EncryptionUtil.md5L32(applicationCode + key);
		if (!applicationKey.getApplicationKey().equalsIgnoreCase(encryptionKey)) {
			logger.info("ApplicationCode:{} with incorrect key:{}.", applicationCode, key);
			return false;
		}
		return true;
	}

}
