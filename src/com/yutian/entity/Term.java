package com.yutian.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Term entity. @author MyEclipse Persistence Tools
 */

public class Term implements java.io.Serializable {

	// Fields

	private Integer id;
	private Parter parter;
	private String termName;
	private String termSlogan;
	private Short termCapacity;
	private String termLogo;
	private Set entries = new HashSet(0);

	// Constructors

	/** default constructor */
	public Term() {
	}

	/** minimal constructor */
	public Term(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Term(Integer id, Parter parter, String termName, String termSlogan,
			Short termCapacity, String termLogo, Set entries) {
		this.id = id;
		this.parter = parter;
		this.termName = termName;
		this.termSlogan = termSlogan;
		this.termCapacity = termCapacity;
		this.termLogo = termLogo;
		this.entries = entries;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Parter getParter() {
		return this.parter;
	}

	public void setParter(Parter parter) {
		this.parter = parter;
	}

	public String getTermName() {
		return this.termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getTermSlogan() {
		return this.termSlogan;
	}

	public void setTermSlogan(String termSlogan) {
		this.termSlogan = termSlogan;
	}

	public Short getTermCapacity() {
		return this.termCapacity;
	}

	public void setTermCapacity(Short termCapacity) {
		this.termCapacity = termCapacity;
	}

	public String getTermLogo() {
		return this.termLogo;
	}

	public void setTermLogo(String termLogo) {
		this.termLogo = termLogo;
	}

	public Set getEntries() {
		return this.entries;
	}

	public void setEntries(Set entries) {
		this.entries = entries;
	}

}