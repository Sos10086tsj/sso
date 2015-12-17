package com.chinesedreamer.sso.exception;

public abstract class BaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3790448691790595113L;

	private String code;
	
	private String message;
	
	public BaseException(String message, Throwable cause){
		super(message, cause);
		this.message = message;
	}
	
	public BaseException(String message){
		super(message);
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
