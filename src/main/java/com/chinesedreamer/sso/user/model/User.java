package com.chinesedreamer.sso.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.chinesedreamer.sso.base.jpa.model.BaseEntity;
import com.chinesedreamer.sso.user.constant.UserStatus;

@Entity
@Table(name = "user")
public class User extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1803910410706664646L;

	@Column
	private String username;
	
	@Column
	private String name;
	
	@Column(name ="status")
	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;
	
	@Column
	private String email;
	
	@Column
	private String salt;
	
	@Column
	private String password;

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public UserStatus getStatus() {
		return status;
	}

	public String getEmail() {
		return email;
	}

	public String getSalt() {
		return salt;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
