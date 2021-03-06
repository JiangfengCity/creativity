package com.yutian.fw;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yutian.util.ProjectConfigUtil;

@SuppressWarnings( "serial" )
public abstract class ActionWrapper extends ActionSupport
{

	public String	type;

	public abstract Integer getType();

	protected int		pageNo	= 1;
	protected int		rows	= 20;
	protected String	referer;

	private String fileBasePath;
	
	public int getPageNo()
	{
		return pageNo;
	}

	public void setPageNo( int pageNo )
	{
		this.pageNo = pageNo;
	}

	public int getRows()
	{
		return rows;
	}

	public void setRows( int rows )
	{
		this.rows = rows;
	}

	public String getFileBasePath()
	{
		if ( StringUtils.isBlank( fileBasePath ) )
		{
			fileBasePath = ProjectConfigUtil.getInstance().getFileBasePath();
		}
		return fileBasePath;
	}

	public void setFileBasePath( String fileBasePath )
	{
		this.fileBasePath = fileBasePath;
	}

	public HttpSession getSession()
	{
		return ServletActionContext.getRequest().getSession();
	}

	public HttpServletResponse getResponse()
	{
		return ServletActionContext.getResponse();
	}

	public HttpServletRequest getRequest()
	{
		try
		{
			ServletActionContext.getRequest().setCharacterEncoding( "utf-8" );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return ServletActionContext.getRequest();
	}


	protected static void put( String key, Object value )
	{
		ActionContext.getContext().put( key, value );
	}

	public String getBasePath()
	{

		return getRequest().getContextPath();
	}

	public PrintWriter getOut() throws IOException
	{
		return this.getResponse().getWriter();
	}

	protected void ajax( String value )
	{

		try
		{
			getResponse().setCharacterEncoding( "utf-8" );
			System.out.println( value );
			getOut().write( value );
			getOut().flush();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}


	public void setReferer( String referer )
	{
		this.referer = referer;
	}

	public String getReferer()
	{
		return getRequest().getHeader( "referer" );
	}

	protected void sendReferer()
	{
		try
		{
			getResponse().sendRedirect( getReferer() );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}

	protected void sendByReferer()
	{
		try
		{
			getResponse().sendRedirect( referer );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}

}
