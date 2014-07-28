package com.yutian.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Parter entity. @author MyEclipse Persistence Tools
 */

public class Parter extends EntityBase implements java.io.Serializable {

	private static final long serialVersionUID = 5979810534926655030L;
	// Fields
	private Integer id;
	private String name;
	private String department;
	private String password;
	private Timestamp createTime;
	private Set<?> terms = new HashSet<Object>(0);

	// Constructors

	/** default constructor */
	public Parter() {
	}

	/** full constructor */
	public Parter(String name, String department, String password,
			Timestamp createTime, Set<?> terms) {
		this.name = name;
		this.department = department;
		this.password = password;
		this.createTime = createTime;
		this.terms = terms;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Set<?> getTerms() {
		return this.terms;
	}

	public void setTerms(Set<?> terms) {
		this.terms = terms;
	}

}