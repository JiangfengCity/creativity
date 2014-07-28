package com.yutian.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.yutian.entity.EntityBase;
import com.yutian.util.DateUtil;
import com.yutian.util.Pagination;

public class CommonDao extends HibernateDaoSupport
{

	private Session loadSession()
	{
		return getHibernateTemplate().getSessionFactory().openSession();
	}

	public List<?> getListByHql( String hql )
	{
		List<?> list = getHibernateTemplate().find( hql );
		return list;
	}

	@SuppressWarnings( "unchecked" )
	public List<Double> getListByHql( String hql, Object[] o )
	{
		List<Double> list = getHibernateTemplate().find( hql, o );
		return list;
	}

	@SuppressWarnings( "unchecked" )
	public final List<Object> getListByHql( final String hql, final Object[] args, final int pageNo, final int rows )
	{
		Session session = loadSession();
		final int startRow = ( pageNo - 1 ) * rows;
		Query query = session.createQuery( hql );
		if( args != null && args.length > 0 )
		{
			int l = args.length;
			for( int i = 0; i < l; i++ )
			{
				query.setParameter( i, args[ i ] );
			}
		}
		query.setFirstResult( startRow );
		query.setMaxResults( rows );
		// getHibernateTemplate().getSessionFactory().close();
		List<Object> list = query.list();
		session.close();
		return list;
	}

	public void executeSql( String sql )
	{
		Session session = loadSession();
		session.createSQLQuery( sql ).executeUpdate();
		session.flush();
		session.clear();
		session.close();
	}

	public <T extends EntityBase> void save( T entity )
	{
		Date date = DateUtil.currentDate();
		if ( null == entity.getCreateTime() )
		{
			entity.setCreateTime( date );
		}
		if ( null == entity.getModifyTime() )
		{
			entity.setModifyTime( date );
		}
		getHibernateTemplate().save( entity );
	}
	
	public <T extends EntityBase> void saveOrUpdate( T entity )
	{
		Date date = DateUtil.currentDate();
		
		if ( null != entity.getId() )
		{
			entity.setModifyTime( date );
		}
		else
		{
			entity.setCreateTime( date );
			entity.setModifyTime( date );
		}
		getHibernateTemplate().saveOrUpdate( entity );
	}

	public <T extends EntityBase> void update( T entity )
	{
		Date date = DateUtil.currentDate();
		entity.setModifyTime( date );
		getHibernateTemplate().update( entity );
	}

	public <T extends EntityBase> void updateNoTime( T entity )
	{
		getHibernateTemplate().update( entity );
	}

	public Object getObject( Class<?> clazz, Serializable id )
	{
		return getHibernateTemplate().get( clazz, id );
	}

	public int getCountByHql( String hql, Object[] o )
	{
		@SuppressWarnings("unchecked")
		List<Double> list = getHibernateTemplate().find( hql, o );
		if( list.size() == 0 || list.get( 0 ) == null )
		{
			return 0;
		}
		else
		{
			return Integer.parseInt( list.get( 0 ) + "" );
		}

	}

	@SuppressWarnings("unchecked")
	public double getCountByHqlDouble( String hql, Object[] o )
	{
		List<Double> list = getHibernateTemplate().find( hql, o );
		if( list == null || list.size() == 0 )
		{
			return 0d;
		}
		else
		{
			return list.get( 0 ) == null ? 0d : list.get( 0 );
		}
	}

	@SuppressWarnings( "unchecked" )
	public Object getObjectSingle( String hql, Object[] o )
	{
		List<Double> list = getHibernateTemplate().find( hql, o );
		if( list.size() > 0 ) { return list.get( 0 ); }
		return null;
	}

	public void delete( Object entity )
	{
		getHibernateTemplate().delete( entity );
	}

	public final Pagination getPageByHql( final String hql, final Object[] args, int pageNo, final int rows )
	{
		if( pageNo <= 0 )
		{
			pageNo = 1;
		}

		List<Object> list = getListByHql( hql, args, pageNo, rows );

		// 解析hql
		String countHql = "";
		if( hql.trim().startsWith( "select" ) )
		{

			countHql = hql;
			countHql = countHql.replaceAll( "select.*?from", "select count(*) from " );
		}
		else
		{
			countHql = "select count(*)  " + hql;
		}

		int count = getCountByHql( countHql, args );
		Pagination page = new Pagination( count, ( pageNo - 1 ) * rows, rows, "", "", list );
		return page;
	}

	public List<?> loadAll( Class<?> entityClass )
	{
		return getHibernateTemplate().loadAll( entityClass );
	}

	/**
	 * 以 JDBC 参数(?)的形式执行SQL查询语句（针对有多个参数的情况）。
	 * 
	 * @param sql
	 *            要执行的SQL语句，不能为 null 。
	 * @param values
	 *            参数值数组。
	 * @return 结果集List，每个元素是一行记录，每行记录使用一个Map表示记录各字段的名和值。
	 */
	@SuppressWarnings( "unchecked" )
	public List<Map<String, ?>> executeSQLSelect( final String sql, final Object[] values )
	{

		// 检查参数
		Assert.hasText( sql );

		// 执行查询
		try
		{

			List<Map<String, ?>> result = ( List<Map<String, ?>> )this.getHibernateTemplate().execute( new HibernateCallback() {
				@SuppressWarnings("deprecation")
				public Object doInHibernate( Session session ) throws HibernateException, SQLException
				{

					// 获得JDBC连接并查询。
					PreparedStatement statement = null;
					ResultSet resultSet = null;
					try
					{
						Connection conn = session.connection();
						statement = conn.prepareStatement( sql );
						// 设置JDBC参数。
						if( values != null && values.length > 0 )
						{
							for( int i = 0; i < values.length; i++ )
							{
								statement.setObject( i + 1, values[ i ] );
							}
						}
						resultSet = statement.executeQuery();
						// 构造结果集Map。
						ResultSetMetaData meta = resultSet.getMetaData();
						List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
						while( resultSet.next() )
						{
							Map<String, Object> row = new HashMap<String, Object>();
							for( int i = 1; i <= meta.getColumnCount(); i++ )
							{
								row.put( meta.getColumnLabel( i ), resultSet.getObject( i ) );
							}
							result.add( row );
						}
						return result;
					}
					finally
					{
						try
						{
							resultSet.close();
						}
						finally
						{
							statement.close();
						}
					}

				}
			} );
			return result;

		}
		catch( DataAccessException e )
		{
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public int executeSqltoInt( String sql )
	{
		Session session = loadSession();
		List<Object> list = session.createSQLQuery( sql ).list();
		int number = 0;
		if( list != null && list.size() > 0 )
		{
			if( list.get( 0 ) != null )
			{
				number = Integer.parseInt( list.get( 0 ).toString() );
			}
		}
		session.flush();
		session.clear();
		session.close();
		return number;
	}

}
