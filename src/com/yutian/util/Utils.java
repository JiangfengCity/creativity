  package com.yutian.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

public class Utils {

	@SuppressWarnings("deprecation")
	public static void setCookie(HttpServletResponse response,String key,String value){
		Cookie cookie=new Cookie(key,URLEncoder.encode(value));
		cookie.setMaxAge(3600*24*30);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public static void setCookie(HttpServletResponse response,String key,Integer value){
		Cookie cookie=new Cookie(key,String.valueOf(value));
		cookie.setMaxAge(3600*24*30);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	@SuppressWarnings("deprecation")
	public static String getCookie(HttpServletRequest request,String key){
		Cookie[] cookies = request.getCookies();
		if(cookies==null)return "";
		for(Cookie ct : cookies){
			if(ct.getName().equals(key)){
				return (ct.getValue()==null||ct.getValue().equals(""))?"":URLDecoder.decode(ct.getValue());
			}
		}
		return "";
	}
	
	public static Integer getCookieInt(HttpServletRequest request,String key){
		try{
			Cookie[] cookies = request.getCookies();
			if(cookies==null)return 0;
			for(Cookie ct : cookies){
				if(ct.getName().equals(key)){
					return (ct.getValue()==null||ct.getValue().equals(""))?0:Integer.parseInt(ct.getValue());
				}
			}
		}
		catch(Exception e){
			return 0;
		}
		return 0;
	}
	
	public static void removeCookie(HttpServletResponse response,String key){
		Cookie cookie = new Cookie(key,null);
		cookie.setMaxAge(0); 
		response.addCookie(cookie); 
		return;
	}
	
	public static long date2MysqlDate(Date date) {
		return date.getTime() / 1000;
	}

	public static int date2MysqlDateInt(Date date) {
		long l = date2MysqlDate(date);
		return Integer.parseInt(l + "");
	}
	
	public static String dec(String s){
		try{
			return java.net.URLDecoder.decode(s,"iso8859-1");
		}
		catch(Exception e){
			return "";
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String enc(String s){
		try{
			return java.net.URLEncoder.encode(s);
		}
		catch(Exception e){
			return "";
		}
	}
	
	
	public static Date StringToDate(String dateStr,String formatStr){
		return StringToDate(dateStr,formatStr, new Date());
	}
	
	public static Date StringToDate(String dateStr,String formatStr,Date defDate){
		
		if(StringUtils.isBlank(dateStr)){
			return defDate;
		}
		
		DateFormat dd=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			return defDate;
		}
		return date;
	}
	
	public static String decode(String param){
		if(null != param){
			try {
				return URLDecoder.decode(param, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 查询时将实体的中文参数转码
	 * @param obj 某实体
	 * @return obj 转码后的该实体
	 * @throws Exception
	 */
	public static Object decodeEntry(Object obj) throws Exception{
		Class cls = obj.getClass();
		System.out.println(cls);
		String className = cls.getName();
		
		Field[] fieldlist = cls.getDeclaredFields();
		 for (int i = 0; i < fieldlist.length; i++) {
             Field field = fieldlist[i];
             if("java.lang.String".equals(field.getType().getName())){
            	 String fieldName = field.getName();  
                 String firstLetter = fieldName.substring(0,1).toUpperCase();  
                 //获得和属性对应的getXXX（）方法的名字  
                 String getMethodName = "get" + firstLetter + fieldName.substring(1);  
                 //获得和属性对应的setXXX()方法的名字  
                 String setMethodName = "set" + firstLetter + fieldName.substring(1);  
                   
                 //获得和属性对应的getXXX()方法  
                 Method getMethod = cls.getMethod(getMethodName, new Class[]{});  
                 //获得和属性对应的setXXX()方法  
                 Method setMethod = cls.getMethod(setMethodName, new Class[]{field.getType()});  
                   
                 //调用原对象的getXXX()方法  
                 String value = (String) getMethod.invoke(obj, new Object[]{});  
                 if(null != value){
//                	 System.out.println(fieldName + ":" + value);  
//                     System.out.println(fieldName + ":" + decode(value)); 
                     //调用复制对象的setXXX()方法  
                     setMethod.invoke(obj, new Object[]{decode(value)}); 
                 }
                 
             }
             
          }
		 
		 return obj;
	}
}

