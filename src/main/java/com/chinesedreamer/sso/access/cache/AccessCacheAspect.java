package com.chinesedreamer.sso.access.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.chinesedreamer.sso.api.ApiResult;
import com.chinesedreamer.sso.base.cache.BaseCacheAspect;

@Component
@Aspect
public class AccessCacheAspect extends BaseCacheAspect{
	
	private Logger logger = LoggerFactory.getLogger(AccessCacheAspect.class);

	private final String USER_SESSION_NAME = "ssoSessionCache";
	private final String USER_SESSION_CACHE_KEY = "ssoSessionKey";
	private final String USER_SESSION_CACHE_DELIMITER = "-";
	
	public AccessCacheAspect(){
		setCacheName(USER_SESSION_NAME);
	}
	
	@Pointcut(
			value ="execution(* com.chinesedreamer.sso.access.service.AccessServiceImpl.getSsoUserSession(java.lang.String,java.lang.String,java.lang.String,java.lang.String)) "
					+ "&& args(applicationCode,username,applicationSessionId,ip)",
			argNames = "applicationCode,username,applicationSessionId,ip")
	public void getUserSessionPointcut(String applicationCode,String username, String applicationSessionId, String ip){};
	
	@Around(
			value = "getUserSessionPointcut(applicationCode,username,applicationSessionId,ip)) ",
			argNames = "applicationCode,username,applicationSessionId,ip"
			)
	public ApiResult getUserSessionFromCache(ProceedingJoinPoint pjp, String applicationCode,String username, String applicationSessionId, String ip){		
		Object object = this.get(USER_SESSION_CACHE_KEY + USER_SESSION_CACHE_DELIMITER + applicationSessionId);
		if (null != object) {
			return (ApiResult)object;
		}
		try {
			return (ApiResult)pjp.proceed();
		} catch (Throwable e) {
			logger.error("{}",e);
			return null;
		}
	}
}
