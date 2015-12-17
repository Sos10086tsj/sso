package com.chinesedreamer.sso.session.model;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

import com.chinesedreamer.sso.base.jpa.model.BaseEntity;

public class SsoSession extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2817528038466549640L;

	@Column(name = "session_id")
	private String sessionId;//SSO系统sessionId
	
	@Column(name = "application_group")
	private String applicationGroup;//共享session的app group。group中只要有一个已经登录，即免登陆
	
	@Column(name = "application_code")
	private String applicationCode;//客户端唯一标识code
	
	@Column
	private String ip;//客户端登陆ip
	
	@Column
	private String username;//登陆用户
	
	@Column(name = "application_session_id")
	private String applicationSessionId;//客户端sessionId，并非一定是web session
	
	@Column(name = "login_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginData;//登陆时间

	public String getSessionId() {
		return sessionId;
	}

	public String getApplicationGroup() {
		return applicationGroup;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public String getIp() {
		return ip;
	}

	public String getUsername() {
		return username;
	}

	public String getApplicationSessionId() {
		return applicationSessionId;
	}

	public Date getLoginData() {
		return loginData;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setApplicationGroup(String applicationGroup) {
		this.applicationGroup = applicationGroup;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setApplicationSessionId(String applicationSessionId) {
		this.applicationSessionId = applicationSessionId;
	}

	public void setLoginData(Date loginData) {
		this.loginData = loginData;
	}
	
	
}
