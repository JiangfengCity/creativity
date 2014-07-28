package com.yutian.entity;

/**
 * TermParter entity. @author MyEclipse Persistence Tools
 */

public class TermParter extends EntityBase implements java.io.Serializable {

	private static final long serialVersionUID = 1138335286906862227L;
	// Fields

	private Integer id;
	private Integer termId;
	private Integer parterId;

	// Constructors

	/** default constructor */
	public TermParter() {
	}

	/** full constructor */
	public TermParter(Integer termId, Integer parterId) {
		this.termId = termId;
		this.parterId = parterId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTermId() {
		return this.termId;
	}

	public void setTermId(Integer termId) {
		this.termId = termId;
	}

	public Integer getParterId() {
		return this.parterId;
	}

	public void setParterId(Integer parterId) {
		this.parterId = parterId;
	}

}