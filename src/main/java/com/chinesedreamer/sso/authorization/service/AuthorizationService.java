package com.chinesedreamer.sso.authorization.service;

/**
 * 授权外接Applicatiion
 * 每个外接的application都会有唯一的applicaitonCode和密文key，系统访问时需要提供指定方式加密的key来通过验证
 * @author Paris
 *
 */
public interface AuthorizationService {
	/**
	 * 检查application是否被授权访问系统
	 * @param applicationCode
	 * @param key
	 * @return
	 */
	public boolean applicationAuthorized(String applicationCode, String key);
}
