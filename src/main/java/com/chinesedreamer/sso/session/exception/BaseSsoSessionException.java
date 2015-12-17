package com.chinesedreamer.sso.session.exception;

import com.chinesedreamer.sso.exception.BaseException;

public abstract class BaseSsoSessionException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8919015948823953952L;

	public BaseSsoSessionException(String message) {
		super(message);
	}

}
