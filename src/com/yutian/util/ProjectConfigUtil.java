package com.yutian.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

/**
 * @author 沈锋 email: shenfeng@yutian.com.cn
 * @Description: project.properties文件读取工具类
 * @date 2013-7-16
 */
public final class ProjectConfigUtil
{

	/**
	 * 媒体文件（包括图片、视频等）获取基路径
	 */
	public static final String		FILE_BASE_PATH		= "file_base_path";

	/**
	 * URL路径分隔符 - /
	 */
	public static final String		URL_SEPRATOR		= "/";

	private static final Logger		LOG					= Logger.getLogger( ProjectConfigUtil.class );

	private static ResourceBundle	projectCfg			= null;

	static
	{
		projectCfg = ResourceBundle.getBundle( "project" );
	}

	private ProjectConfigUtil()
	{
		// 禁止在外new出实例，本类单例
	}

	public static ProjectConfigUtil getInstance()
	{
		return ProjectConfigHandler.instance;
	}

	public String getString( String key )
	{
		if( StringUtils.isBlank( key ) ) { return null; }

		return projectCfg.getString( key );
	}

	/**
	 * 获取媒体文件（包括图片、视频等）获取基路径
	 * 
	 * @return
	 */
	public String getFileBasePath()
	{
		String fileBasePath = projectCfg.getString( FILE_BASE_PATH );

		if( StringUtils.isBlank( fileBasePath ) )
		{
			LOG.warn( "The file base path in the project.properties is empty," 
					+ " return default value [http://192.168.100.63/]!" );
			return "http://192.168.100.63/";
		}

		if( !fileBasePath.endsWith( URL_SEPRATOR ) )
		{
			fileBasePath += URL_SEPRATOR;
		}

		return fileBasePath;
	}
	
	private static class ProjectConfigHandler
	{
		public static ProjectConfigUtil	instance	= new ProjectConfigUtil();
	}

}
