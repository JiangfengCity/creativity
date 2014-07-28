package com.yutian.entity;

/**
 * EntryScore entity. @author MyEclipse Persistence Tools
 */

public class EntryScore extends EntityBase implements java.io.Serializable {

	private static final long serialVersionUID = 171077027812146931L;
	// Fields

	private Integer id;
	private Integer entryId;
	private Float score;
	private String reviewerIp;

	// Constructors

	/** default constructor */
	public EntryScore() {
	}

	/** full constructor */
	public EntryScore(Integer entryId, Float score, String reviewerIp) {
		this.entryId = entryId;
		this.score = score;
		this.reviewerIp = reviewerIp;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEntryId() {
		return this.entryId;
	}

	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}

	public Float getScore() {
		return this.score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getReviewerIp() {
		return this.reviewerIp;
	}

	public void setReviewerIp(String reviewerIp) {
		this.reviewerIp = reviewerIp;
	}

}