package com.ali.leb.base.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;


/**
 *  处理BigDecimal类型json，当为空的时候返回空，不返回0
 */
public class JsonBigDecimalValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object value, JsonConfig jsonconfig) {
		return process(value);
	}

	@Override
	public Object processObjectValue(String s, Object value, JsonConfig jsonconfig) {
		 return process(value);
	}
	
	private Object process(Object value) {
	      return value == null ? "" : value.toString();
	}
	  
}

