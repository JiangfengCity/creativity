package com.yutian.entity;

import java.sql.Timestamp;

/**
 * Entry entity. @author MyEclipse Persistence Tools
 */

public class Entry extends EntityBase implements java.io.Serializable {

	private static final long serialVersionUID = 5352102149640779054L;
	// Fields

	private Integer id;
	private Term term;
	private String picture;
	private String name;
	private String description;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public Entry() {
	}

	/** full constructor */
	public Entry(Term term, String picture, String name, String description,
			Timestamp createTime) {
		this.term = term;
		this.picture = picture;
		this.name = name;
		this.description = description;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Term getTerm() {
		return this.term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}