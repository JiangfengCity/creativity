package com.yutian.util;

import com.alibaba.fastjson.JSONObject;

public class LocalCommonUtil {
	/** 拼接异步返回的json字符串 */
	public static String assembleCommonMsg(String status,String msg){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("msg", msg);
		return json.toJSONString();
	}
	
	public static String assembleCommonMsg(String status,Object msg){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("msg", msg);
		return json.toJSONString();
	}
	
	public static String assembleCommonMsg(String status,String msg,String extend){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("msg", msg);
		json.put("extend", extend);
		return json.toJSONString();
	}
	
	public static JSONObject assembleMsg(String status,String msg){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("msg", msg);
		return json;
	}
	
	public static JSONObject assembleMsg(String status,Object msg){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("msg", msg);
		return json;
	}
	
	public static JSONObject assembleMsg(String status,String msg,String extend){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("msg", msg);
		json.put("extend", extend);
		return json;
	}
}
