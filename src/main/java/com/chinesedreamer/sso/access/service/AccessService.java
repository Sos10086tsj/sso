package com.chinesedreamer.sso.access.service;

import com.chinesedreamer.sso.api.ApiResult;

/**
 * application登陆、登出SSO接口类
 * @author Paris
 *
 */
public interface AccessService {
	/**
	 * 用户登陆
	 * @param applicationCode
	 * @param username
	 * @param password
	 * @return JSON string
	 */
	public String login(String applicationCode, String username, String password);
	
	/**
	 * 用户登陆
	 * @param applicationCode
	 * @param username
	 * @param password
	 * @return
	 */
	public ApiResult loginSso(String applicationCode, String username, String password);
	
	/**
	 * 用户登出
	 * @param applicationCode
	 * @param username
	 * @return
	 */
	public String logout(String applicationCode, String username);
	
	/**
	 * 用户登出
	 * @param applicationCode
	 * @param username
	 * @return JSON string
	 */
	public ApiResult logoutSso(String applicationCode, String username);
	
	/**
	 * 判断是否已经SSO登录
	 * @param applicationCode
	 * @param username
	 * @return
	 */
	public boolean checkSsoLogined(String applicationCode, String username);
	
	/**
	 * 获取用户已登录信息
	 * @param applicationCode
	 * @param username
	 * @return
	 */
	public String getUserSession(String applicationCode, String username, String applicationSessionId, String ip);
	
	/**
	 * 获取用户已登录信息
	 * @param applicationCode
	 * @param username
	 * @return
	 */
	public ApiResult getSsoUserSession(String applicationCode, String username, String applicationSessionId, String ip);
}
