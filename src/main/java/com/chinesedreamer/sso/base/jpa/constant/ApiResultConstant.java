package com.chinesedreamer.sso.base.jpa.constant;

public class ApiResultConstant {
	public static class USER_ERROR {
		public static String USER_NOT_EXIST_CODE = "ERR_USER_001";
		public static String USER_NOT_EXIST_MSG = "用户不存在";
		
		public static String USERNAME_PASS_NOT_MATCH_CODE = "ERR_USER_002";
		public static String USERNAME_PASS_NOT_MATCH_MSG = "用户名或密码错误";
		
		public static String USER_NOT_ACTIVE_CODE = "ERR_USER_003";
		public static String USER_NOT_ACTIVE_MSG = "用户处于非激活状态";
	}
	
	public static class SESSION_ERROR {
		public static String SESSION_OVERDUE_CODE = "ERR_SESSION_001";
		public static String SESSION_OVERDUE_MSG = "会话已经过期";
	}
}
