package com.yutian.fw;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yutian.dao.CommonDao;
import com.yutian.entity.EntityBase;

public class CommonService
{

	@Autowired
	protected CommonDao			dao;

	public void saveOrUpdate( EntityBase o )
	{
		dao.saveOrUpdate( o );
	}
	
	public void save( EntityBase o )
	{
		dao.save( o );
	}

	public void modify( EntityBase o )
	{
		dao.update( o );
	}

	public void modifyNoTime( EntityBase o )
	{
		dao.updateNoTime( o );
	}

	public void delete( EntityBase o )
	{
		dao.delete( o );
	}

	@SuppressWarnings("unchecked")
	public <T> T load( Class<T> clazz, Integer id )
	{
		return ( T )dao.getObject( clazz, id );
	}
	
	public Object loadAll( Class<?> clazz )
	{
		return dao.loadAll( clazz );
	}

	public List<Map<String, ?>> executeSQLSelect( final String sql, final Object[] values )
	{
		return dao.executeSQLSelect( sql, values );
	}

	public int count( final String hql, final Object[] values )
	{
		return dao.getCountByHql( hql, values );
	}
	public void executeSql( String sql )
	{
		dao.executeSql( sql );
	}
}
