package com.chinesedreamer.sso.authorization.service;

import com.chinesedreamer.sso.authorization.model.ApplicationGroup;
import com.chinesedreamer.sso.authorization.model.ApplicationKey;

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
	
	/**
	 * 增加应用
	 * @param applicationCode
	 * @param applicationName
	 * @return
	 */
	public ApplicationKey authorize(String applicationCode, String applicationName);
	
	/**
	 * 应用分组
	 * @param applicationCode
	 * @param applicationGroupCode
	 * @return
	 */
	public ApplicationGroup groupApplication(String applicationCode, String applicationGroupCode);
}
