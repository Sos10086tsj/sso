package com.chinesedreamer.sso.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.sso.base.jpa.model.BaseEntity;

@Entity
@Table(name = "application_group")
public class ApplicationGroup extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6237880406078094081L;

	@Column(name = "group_code")
	private String groupCode;
	
	@Column(name = "application_code")
	private String applicationCode;

	public String getGroupCode() {
		return groupCode;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	
	
}
