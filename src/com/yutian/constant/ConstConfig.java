package com.yutian.constant;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ConstConfig 定义对应配置文件的变量
 *
 * @author zjf 
 */
public class ConstConfig {
    private transient final static Logger log = LoggerFactory.getLogger(ConstConfig.class);
    static {
        try {
            init();
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    /** 用户密码加密后缀 */
    public static String MD5_PASSWORD_DIGEST;
    
    static public void init() throws Exception {
        final Properties pro = new Properties();
        try {
            pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        ConstConfig.MD5_PASSWORD_DIGEST				= pro.getProperty("md5_password_digest");
    }
    
    static String parseAndGet(String key,Properties p){
    	String value = p.getProperty(key);
    	if(value.contains("${catalina.base}")){
    		value = value.replace("${catalina.base}",  System.getProperty("catalina.base"));
    	}
    	return value;
    }
}