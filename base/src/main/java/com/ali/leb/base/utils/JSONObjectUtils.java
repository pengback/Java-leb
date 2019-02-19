package com.ali.leb.base.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.math.BigDecimal;


/**
 *  net.sf.json.JSONObject  工具类，后续这个组件的json都在这个里面扩展
 *  @author xxhu
 *  @created 2015年4月8日 下午3:04:50
 *  @lastModified       
 *  @history           
 */

public class JSONObjectUtils {
	
	/**
	 * JsonBigDecimalValueProcessor 的json转换类
	 * @param object
	 * @return
	 */
	public static String fromObjectForBigDecimal(Object object){
		JsonConfig jsonConfig = new JsonConfig(); 
		jsonConfig.registerJsonValueProcessor(BigDecimal.class, new JsonBigDecimalValueProcessor());
		return JSONObject.fromObject(object,jsonConfig).toString();
	}
	
	/**
	 * JsonBigDecimalValueProcessor 的json转换类
	 * @param object
	 * @return
	 */
	public static String fromArrayObjectForBigDecimal(Object object){
		JsonConfig jsonConfig = new JsonConfig(); 
		jsonConfig.registerJsonValueProcessor(BigDecimal.class, new JsonBigDecimalValueProcessor());
		return JSONArray.fromObject(object,jsonConfig).toString();
	}
}
