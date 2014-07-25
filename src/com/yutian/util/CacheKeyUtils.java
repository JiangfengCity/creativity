package com.yutian.util;

import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

/**
 * @author 沈锋
 *         email: shenfeng@yutian.com.cn
 * @Description: Ehcache缓存键值管理工具类
 * @date 2013-5-21
 */
public final class CacheKeyUtils
{
	
	public static final String KEY_SEPARATOR = "_";
	
	public static final String CHANNEL_LIST_BASE_KEY = "_channel_list";
	public static final String ROLLING_NEWS_BASE_KEY = "_rolling_news";
	public static final String CONTENT_BASE_KEY = "_content";
	public static final String IMAGE_BASE_KEY = "_iamge";
	public static final String INSERT_PLAY_LIST_BASE_KEY = "_insert_play";
	public static final String COMMENT_PAGINATION_BASE_KEY = "_comment_pagination";
	
	private CacheKeyUtils ()
	{
		
	}
	
	public static String getChannelListCacheKey ( String parentId )
	{
		String channelListCacheKey = CHANNEL_LIST_BASE_KEY;
		
		if ( !StringUtils.isBlank( parentId ) )
		{
			channelListCacheKey += KEY_SEPARATOR + parentId;
		}
		
		return channelListCacheKey;
	}

	public static String getRollingNewsCacheKey( String channelId )
	{
		String rollingNewsCacheKey = ROLLING_NEWS_BASE_KEY;
		
		if ( !StringUtils.isBlank( channelId ) )
		{
			rollingNewsCacheKey += KEY_SEPARATOR + channelId;
		}
		
		return rollingNewsCacheKey;
	}

	public static String getContentCacheKey( String contentId )
	{
		String contentCacheKey = CONTENT_BASE_KEY;
		
		if ( !StringUtils.isBlank( contentId ))
		{
			contentCacheKey += KEY_SEPARATOR + contentId;
		}
		
		return contentCacheKey;
	}

	public static String getImageCaheKey( String filePath, String paramWidth )
	{
		String imageCacheKey = IMAGE_BASE_KEY;
		
		if ( !StringUtils.isBlank( filePath ) )
		{
			imageCacheKey += KEY_SEPARATOR + filePath;
		}
		
		if ( !StringUtils.isBlank( paramWidth ) )
		{
			imageCacheKey += KEY_SEPARATOR + paramWidth;
		}
		
		return imageCacheKey;
	}

	public static String getInsertPlayListCacheKey()
	{
		return INSERT_PLAY_LIST_BASE_KEY + KEY_SEPARATOR + 
				DateUtil.format( DateUtil.currentDate(), DateUtil.PATNER_ISO9985_2 );
	}

	public static String getCommentPaginationCacheKey( String contentId, 
			int pageNo, int pageSize )
	{
		String commentPaginationCacheKey = COMMENT_PAGINATION_BASE_KEY;
		
		if ( null != contentId )
		{
			commentPaginationCacheKey += KEY_SEPARATOR + contentId;
		}
		
		return commentPaginationCacheKey + KEY_SEPARATOR + pageSize + KEY_SEPARATOR + pageNo;
	}
	
}
