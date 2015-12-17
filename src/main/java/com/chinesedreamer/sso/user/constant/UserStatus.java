package com.chinesedreamer.sso.user.constant;

public enum UserStatus {
	DEFAULT(0),
	ACTIVE(1),
	INACTIVE(2);
	
	private final Integer value;
	
	private UserStatus(Integer value){
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	
}
