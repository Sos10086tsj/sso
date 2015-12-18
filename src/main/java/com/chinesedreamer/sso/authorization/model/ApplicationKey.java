package com.chinesedreamer.sso.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.sso.base.jpa.model.BaseEntity;

@Entity
@Table(name = "application_key")
public class ApplicationKey extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5457621483817671117L;

	@Column(name = "application_code")
	private String applicationCode;
	
	@Column(name = "application_key")
	private String applicationKey;

	public String getApplicationCode() {
		return applicationCode;
	}

	public String getApplicationKey() {
		return applicationKey;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public void setApplicationKey(String applicationKey) {
		this.applicationKey = applicationKey;
	}
	
	
}
