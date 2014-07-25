package com.yutian.entity;

import java.util.Date;

import com.yutian.util.DateUtil;

public class EntityBase
{

	protected Integer	id;
	private Date		createTime;
	private Date		modifyTime;

	public Date getCreateTime() {
		if ( null == createTime )
		{
			createTime = DateUtil.currentDate();
		}
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		if ( null == modifyTime )
		{
			modifyTime = DateUtil.currentDate();
		}
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}
}
