package com.chinesedreamer.sso.exception.authorization;

import com.chinesedreamer.sso.exception.BaseException;

public abstract class BaseAuthException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6329768919601795827L;
	
	public BaseAuthException(String message, Throwable cause){
		super(message, cause);
	}
	
	public BaseAuthException(String message){
		super(message);
	}

}
