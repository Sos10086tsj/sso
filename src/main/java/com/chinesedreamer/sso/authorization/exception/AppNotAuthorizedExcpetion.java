package com.chinesedreamer.sso.authorization.exception;

/**
 * 请求访问缺少appCode或者appKey
 * @author Paris
 *
 */
public class AppNotAuthorizedExcpetion extends BaseAuthException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1075247954678502011L;

	public AppNotAuthorizedExcpetion(String message) {
		super(message);
	}

}
