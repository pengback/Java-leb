
package com.ali.leb.base.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Set;

/**
 * {读取配置信息的类}
 * 
 */
public class GlobalConfig {

	private PropertyPlaceholderConfigurer configure;

	private Properties properties = new Properties();

	private static GlobalConfig instance = new GlobalConfig();
	
	private static String ywqAppConfig = "{}";

	private GlobalConfig() {
		// 初始化，配置属性到本地properties中区
		try {
			//读取applicationCotext对象
			WebApplicationContext wapp = ContextLoader
					.getCurrentWebApplicationContext();
			
			configure = (PropertyPlaceholderConfigurer) wapp.getBean("propertyPlaceHolder");
			
			//通过mergeProperties 方法读取所有的配置属性信息
			PropertyResourceConfigurer propertyResourceConfigurer = (PropertyResourceConfigurer) configure;
			Method mergeProperties = PropertiesLoaderSupport.class
					.getDeclaredMethod("mergeProperties");

			mergeProperties.setAccessible(true);
			Properties props = (Properties) mergeProperties
					.invoke(propertyResourceConfigurer);

			Method convertProperties = PropertyResourceConfigurer.class
					.getDeclaredMethod("convertProperties", Properties.class);
			convertProperties.setAccessible(true);
			convertProperties.invoke(propertyResourceConfigurer, props);

			properties.putAll(props);
			doParseConfig(properties);
		} catch (Exception e) {
			// 初始化属性读取失败
			e.printStackTrace();
		}
	}

	public static GlobalConfig getInstance() {
		return instance;
	}

	public String getString(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 解析固定配置项用于存放到前端配置信息JSON中
	 */
	private void doParseConfig(Properties allConfigItem){
		Properties appConfig=new Properties();
		Set<String> allKeys=allConfigItem.stringPropertyNames();
		for(String key : allKeys){
			if(key.contains("ywq.config.")){
				appConfig.put(key, allConfigItem.get(key));
			}
		}
		
		ywqAppConfig=JSONObject.toJSONString(appConfig);
	}
	
	public static String getAppConfig() {
		return ywqAppConfig;
	}
}
