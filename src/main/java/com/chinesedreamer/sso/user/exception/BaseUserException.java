package com.chinesedreamer.sso.user.exception;

import com.chinesedreamer.sso.exception.BaseException;

public abstract class BaseUserException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7212035792027275930L;

	public BaseUserException(String message) {
		super(message);
	}

}
