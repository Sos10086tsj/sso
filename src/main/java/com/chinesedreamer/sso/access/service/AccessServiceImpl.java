package com.chinesedreamer.sso.access.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.chinesedreamer.sso.api.ApiResult;
import com.chinesedreamer.sso.api.SsoUserSession;
import com.chinesedreamer.sso.authorization.repository.ApplicationGroupRepository;
import com.chinesedreamer.sso.base.cache.BaseCacheAspect;
import com.chinesedreamer.sso.base.jpa.constant.ApiResultConstant;
import com.chinesedreamer.sso.session.constant.SsoLoginType;
import com.chinesedreamer.sso.session.exception.SsoSessionOverdueException;
import com.chinesedreamer.sso.session.model.SsoSession;
import com.chinesedreamer.sso.session.repository.SsoSessionRepository;
import com.chinesedreamer.sso.user.constant.UserStatus;
import com.chinesedreamer.sso.user.exception.UserNotActiveException;
import com.chinesedreamer.sso.user.exception.UserNotExistException;
import com.chinesedreamer.sso.user.exception.UsernamePassNotMatchException;
import com.chinesedreamer.sso.user.model.User;
import com.chinesedreamer.sso.user.repository.UserRepository;
import com.chinesedreamer.sso.util.EncryptionUtil;

@Service
public class AccessServiceImpl extends BaseCacheAspect implements AccessService{
	
	private Logger logger = LoggerFactory.getLogger(AccessServiceImpl.class);
	
	@Resource
	private UserRepository userRepository;
	@Resource
	private SsoSessionRepository ssoSessionRepository;
	@Resource
	private ApplicationGroupRepository applicationGroupRepository;
	
	private final String USER_SESSION_NAME = "ssoSessionCache";
	private final String USER_SESSION_CACHE_KEY = "ssoSessionKey";
	private final String USER_SESSION_CACHE_DELIMITER = "-";
	
	public AccessServiceImpl(){
		setCacheName(USER_SESSION_NAME);
	}

	@Override
	public String login(String applicationCode, String username, String password, String applicationSessionId, String ip) {
		ApiResult apiResult = this.login(username, password);
		if (apiResult.getSuccess()) {
			SsoSession ssoSession = this.saveSessionAfterLogin(username, applicationCode, applicationSessionId, ip);
			apiResult.setData(this.convert2UserSession(ssoSession));
			
			this.save2Cache(ssoSession.getApplicationSessionId(), apiResult);
		}
		return JSON.toJSONString(apiResult);
	}

	@Override
	public ApiResult loginSso(String applicationCode, String username, String password, String applicationSessionId, String ip) {
		ApiResult apiResult = this.login(username, password);
		if (apiResult.getSuccess()) {
			SsoSession ssoSession = this.saveSessionAfterLogin(username, applicationCode, applicationSessionId, ip);
			apiResult.setData(this.convert2UserSession(ssoSession));
			
			this.save2Cache(ssoSession.getApplicationSessionId(), apiResult);
		}
		return apiResult;
	}
	
	private void save2Cache(String sessionId,ApiResult apiResult){
		this.put(USER_SESSION_CACHE_KEY + USER_SESSION_CACHE_DELIMITER + sessionId, (SsoUserSession)apiResult.getData());
	}
	
	/**
	 * 用户登陆
	 * @param username
	 * @param password
	 * @return
	 */
	private ApiResult login(String username, String password){
		ApiResult apiResult = new ApiResult();
		User user = this.userRepository.findByUsername(username);
		if (null == user) {
			logger.error("", new UserNotExistException("User:" + username + " is not exist."));
			apiResult.setErrorCode(ApiResultConstant.USER_ERROR.USER_NOT_EXIST_CODE);
			apiResult.setErrorMessage(ApiResultConstant.USER_ERROR.USER_NOT_EXIST_MSG);
			return apiResult;
		}
		String encryptionPass = EncryptionUtil.md5L32(password + user.getSalt());
		if (!encryptionPass.equals(user.getPassword())) {
			logger.error("", new UsernamePassNotMatchException("User:" + username + " or password:" + password + " not match."));
			apiResult.setErrorCode(ApiResultConstant.USER_ERROR.USERNAME_PASS_NOT_MATCH_CODE);
			apiResult.setErrorMessage(ApiResultConstant.USER_ERROR.USERNAME_PASS_NOT_MATCH_MSG);
			return apiResult;
		}
		
		if (!user.getStatus().equals(UserStatus.ACTIVE)) {
			logger.error("", new UserNotActiveException("User:" + username + " is not active."));
			apiResult.setErrorCode(ApiResultConstant.USER_ERROR.USER_NOT_ACTIVE_CODE);
			apiResult.setErrorMessage(ApiResultConstant.USER_ERROR.USER_NOT_ACTIVE_MSG);
			return apiResult;
		}
		
		apiResult.setSuccess(Boolean.TRUE);
		return apiResult;
	}
	
	/**
	 * 登陆后保存共享session
	 * @param user
	 * @param applicationCode
	 * @param sessionId
	 */
	private SsoSession saveSessionAfterLogin(String username,String applicationCode,String applicationSessionId, String ip){
		SsoSession ssoSession = this.ssoSessionRepository.findByApplicationCodeAndUsername(applicationCode,username);
		if (null == ssoSession) {
			ssoSession = new SsoSession();
		}
		ssoSession.setApplicationCode(applicationCode);
		ssoSession.setApplicationGroup(this.applicationGroupRepository.findByApplicationCode(applicationCode).getGroupCode());
		ssoSession.setApplicationSessionId(applicationSessionId);
		ssoSession.setIp(ip);
		ssoSession.setLoginData(new Date());
		ssoSession.setUsername(username);
		ssoSession.setLoginType(SsoLoginType.USER_LOGIN);
		return this.ssoSessionRepository.save(ssoSession);
	}

	@Override
	public String logout(String applicationCode, String username) {
		ApiResult apiResult = this.userLogout(applicationCode, username);
		this.clearCacheAfterLogout((String)apiResult.getData());
		return JSON.toJSONString(apiResult);
	}

	@Override
	public ApiResult logoutSso(String applicationCode, String username) {
		ApiResult apiResult = this.userLogout(applicationCode, username);
		this.clearCacheAfterLogout((String)apiResult.getData());
		return apiResult;
	}

	
	/**
	 * 登出时清理缓存
	 * @param applicationCode
	 * @param username
	 */
	private void clearCacheAfterLogout(String sessionId){
		if (StringUtils.isNotEmpty(sessionId)) {
			this.evict(USER_SESSION_CACHE_KEY + USER_SESSION_CACHE_DELIMITER + sessionId);
		}
	}
	
	/**
	 * 登出操作
	 * @param applicationCode
	 * @param username
	 * @return
	 */
	private ApiResult userLogout(String applicationCode, String username){
		ApiResult apiResult = new ApiResult();
		SsoSession ssoSession = this.ssoSessionRepository.findByApplicationCodeAndUsername(applicationCode,username);
		if (null != ssoSession) {
			apiResult.setData(ssoSession.getApplicationSessionId());
			this.ssoSessionRepository.delete(ssoSession);
		}
		apiResult.setSuccess(Boolean.TRUE);
		return apiResult;
	}

	@Override
	public boolean checkSsoLogined(String applicationCode, String username) {
		List<SsoSession> ssoSessions = this.ssoSessionRepository.findByApplicationGroupAndUsername(this.applicationGroupRepository.findByApplicationCode(applicationCode).getGroupCode(), username);
		if (null == ssoSessions || ssoSessions.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public String getUserSession(String applicationCode, String username, String applicationSessionId, String ip) {
		ApiResult apiResult = this.getUserSessionApiResult(applicationCode, username, applicationCode, ip);
		return JSON.toJSONString(apiResult);
	}

	@Override
	public ApiResult getSsoUserSession(String applicationCode, String username, String applicationSessionId, String ip) {
		ApiResult apiResult = this.getUserSessionApiResult(applicationCode, username, applicationCode, ip);
		return apiResult;
	}
	
	private ApiResult getUserSessionApiResult(String applicationCode, String username, String applicationSessionId, String ip) {
		ApiResult apiResult = new ApiResult();
		List<SsoSession> ssoSessions = this.ssoSessionRepository.findByApplicationGroupAndUsername(this.applicationGroupRepository.findByApplicationCode(applicationCode).getGroupCode(), username);
		if (null == ssoSessions || ssoSessions.size() == 0) {
			logger.error("", new SsoSessionOverdueException("Session of user:" + username + " is overdue or missing, please relogin."));
			apiResult.setErrorCode(ApiResultConstant.SESSION_ERROR.SESSION_OVERDUE_CODE);
			apiResult.setErrorMessage(ApiResultConstant.SESSION_ERROR.SESSION_OVERDUE_MSG);
			return apiResult;
		}
		SsoSession template = ssoSessions.get(0);
		apiResult.setData(this.convert2UserSession(template));
		
		//模拟登陆
		this.mockUserLogin(applicationCode, username, applicationCode, ip);
		apiResult.setSuccess(Boolean.TRUE);
		return apiResult;
	}
	
	/**
	 * 模拟用户登陆
	 * @param applicationCode
	 * @param username
	 */
	private void mockUserLogin(String applicationCode, String username, String applicationSessionId, String ip){
		SsoSession ssoSession = this.ssoSessionRepository.findByApplicationCodeAndUsername(applicationCode,username);
		if (null == ssoSession) {
			ssoSession = new SsoSession();
		}
		ssoSession.setApplicationCode(applicationCode);
		String groupCode = this.applicationGroupRepository.findByApplicationCode(applicationCode).getGroupCode();
		ssoSession.setApplicationGroup(groupCode);
		ssoSession.setLoginData(new Date());
		ssoSession.setUsername(username);
		ssoSession.setLoginType(SsoLoginType.MOCK_LOGIN);
		ssoSession.setApplicationSessionId(applicationSessionId);
		ssoSession.setIp(ip);
		ssoSession = this.ssoSessionRepository.save(ssoSession);
		this.put(USER_SESSION_CACHE_KEY + USER_SESSION_CACHE_DELIMITER + applicationSessionId, this.convert2UserSession(ssoSession));
	}
	
	private SsoUserSession convert2UserSession(SsoSession ssoSession) {
		SsoUserSession session = new SsoUserSession();
		session.setUsername(ssoSession.getUsername());
		User user = this.userRepository.findByUsername(ssoSession.getUsername());
		session.setName(user.getName());
		return session;
	}
}
