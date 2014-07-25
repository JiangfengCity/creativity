package com.yutian.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.FutureTask;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author 沈锋 email: shenfeng@yutian.com.cn
 * @Description: Ehcache缓存工具类
 * @date 2013-7-5
 */
public final class CacheUtils
{

	public static final String	CACHE_POLICY_ETERNITY	= "cache_policy_eternity";
	public static final String	CACHE_POLICY_HALF_HOUR	= "cache_policy_half_hour";
	public static final String	CACHE_POLICY_1_HOUR		= "cache_policy_1_hour";
	public static final String	CACHE_POLICY_24_HOURS	= "cache_policy_24_hours";

	private static final ConcurrentMap<String, FutureTask<Object>>	CACHE_TASKS	= 
			new ConcurrentHashMap<String, FutureTask<Object>>();
	
	private static CacheManager	manager;

	private CacheUtils()
	{

	}

	static
	{
		if( manager == null )
		{
			manager = CacheManager.create();
		}
	}

	/**
	 * 高并发下缓存失效时从数据库读取数据方式，采用该方式可以防止，失效时刻，
	 * 并发量过大而多次读取数据库，减少数据库读取开销。
	 * 
	 * @param cacheKey
	 * @param caller
	 * @return
	 */
	public static Object getInTask( String cacheKey, Callable<Object> caller )
	{
		Object result = null;
		FutureTask<Object> futureTask = CACHE_TASKS.get( cacheKey );
		
		if( futureTask == null )
		{
			// 缓存未命中，从数据库获取数据
			FutureTask<Object> furureTaskTemp = new FutureTask<Object>( caller );
			futureTask = CACHE_TASKS.putIfAbsent( cacheKey, furureTaskTemp );
			if( futureTask == null )
			{
				futureTask = furureTaskTemp;
				futureTask.run();
			}
		}
		
		try
		{
			result = futureTask.get();
			return result;
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			// 最后将计算任务去掉,虽然已经移除任务对象，但其他线程仍然能够获取到计算的结果，直到所有引用都失效，被垃圾回收掉
			CACHE_TASKS.remove( cacheKey, futureTask );
		}
		
		return result;
	}
	
	public static void setCache( String cacheName, String key, Object value )
	{
		Cache cache = manager.getCache( cacheName );
		Element element = new Element( key, value );
		cache.put( element );
	}

	public static void replaceCache( String cacheName, String key, Object value )
	{
		Cache cache = manager.getCache( cacheName );
		Element element = new Element( key, value );
		cache.replace( element );
	}

	public static Object getCache( String cacheName, String key )
	{
		Cache cache = manager.getCache( cacheName );
		if( cache.get( key ) == null ) { return null; }
		return cache.get( key ).getValue();
	}

	public static void clearCache()
	{
		manager.clearAll();
	}

	public static void shutDown()
	{
		manager.shutdown();
	}

}
