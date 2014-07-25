package com.yutian.entity;

/**
 * Entry entity. @author MyEclipse Persistence Tools
 */

public class Entry implements java.io.Serializable {

	// Fields

	private Integer id;
	private Term term;
	private String picture;
	private String name;
	private String description;

	// Constructors

	/** default constructor */
	public Entry() {
	}

	/** full constructor */
	public Entry(Term term, String picture, String name, String description) {
		this.term = term;
		this.picture = picture;
		this.name = name;
		this.description = description;
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

}