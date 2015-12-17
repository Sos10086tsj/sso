package com.chinesedreamer.sso.session.service;

import com.chinesedreamer.sso.api.vo.ApiResult;

/**
 * application session存储service
 * @author Paris
 *
 */
public interface SessionService {
	
	/**
	 * 保存session信息
	 * @param appSessionId
	 * @param appCode
	 * @param username
	 * @param name
	 * @param currentSession
	 * @return JSON string
	 */
	public String saveSession(String appSessionId, String appCode, String username, String name, String currentSession);
	
	/**
	 * 保存session信息
	 * @param appSessionId
	 * @param appCode
	 * @param username
	 * @param name
	 * @param currentSession
	 * @return
	 */
	public ApiResult saveSsoSession(String appSessionId, String appCode, String username, String name,  String currentSession);
	
	/**
	 * 获取application已登录session
	 * @param appSessionId
	 * @param appCode
	 * @return
	 */
	public String getCurrentSession(String appSessionId, String appCode);
	
	/**
	 * 获取application已登录session
	 * @param appSessionId
	 * @param appCode
	 * @return
	 */
	public ApiResult getCurrentSsoSession(String appSessionId, String appCode);
}
