package com.chinesedreamer.sso.session.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.sso.base.jpa.model.BaseEntity;

@Entity
@Table(name = "sso_session")
public class SsoSession extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2817528038466549640L;
	
	@Column
	private String username;//登陆用户

	@Column
	private String token;//唯一token

	public String getUsername() {
		return username;
	}

	public String getToken() {
		return token;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
