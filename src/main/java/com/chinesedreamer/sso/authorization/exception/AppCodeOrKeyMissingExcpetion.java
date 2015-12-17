package com.chinesedreamer.sso.authorization.exception;

/**
 * 请求访问缺少appCode或者appKey
 * @author Paris
 *
 */
public class AppCodeOrKeyMissingExcpetion extends BaseAuthException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3683140724376373233L;

	public AppCodeOrKeyMissingExcpetion(String message) {
		super(message);
	}

}
