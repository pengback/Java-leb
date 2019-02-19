package com.ali.leb.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * JSON工具
 * 
 */
public class JSONUtils {

	/**
	 * 默认特征
	 */
	private static final SerializerFeature[] defaultFeatures = { SerializerFeature.WriteMapNullValue };

	/**
	 * 默认日期格式
	 */
	private static final String defaultPattern = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获取JSON，JSON中包含includes数组中包含的属性.
	 * 
	 */
	public static String toJSONInclude(Object source, final String... includes) {
		return toJSONInclude(source, defaultPattern, includes);
	}

	/**
	 * 获取JSON，JSON中包含includes数组中包含的属性.
	 * 
	 */
	public static String toJSONInclude(Object source,
                                       final String dateFormatPattern, final String[] includes) {
		if (source == null) {
			return null;
		}
		PropertyPreFilter filter = new PropertyPreFilter() {

			@Override
			public boolean apply(JSONSerializer serializer, Object source,
					String name) {
				if (dateFormatPattern != null) {
					serializer.setDateFormat(dateFormatPattern);
				}
				if (includes == null) {
					return false;
				}
				if (hasProperty(includes, name)) {
					return true;
				} else {
					return false;
				}
			}
		};
		return JSON.toJSONString(source, filter, defaultFeatures);
	}

	/**
	 * 获取JSON，JSON中不包含excludes数组中包含的属性.
	 * 
	 */
	public static String toJSONExclude(Object source, final String... excludes) {
		return toJSONExclude(source, defaultPattern, excludes);
	}

	/**
	 * 获取JSON，JSON中不包含excludes数组中包含的属性.
	 * 
	 */
	public static String toJSONExclude(Object source,
                                       final String dateFormatPattern, final String[] excludes) {
		if (source == null) {
			return null;
		}
		PropertyPreFilter filter = new PropertyPreFilter() {

			@Override
			public boolean apply(JSONSerializer serializer, Object source,
					String name) {
				if (dateFormatPattern != null) {
					serializer.setDateFormat(dateFormatPattern);
				}
				if (excludes == null) {
					return true;
				}
				if (hasProperty(excludes, name)) {
					return false;
				} else {
					return true;
				}
			}
		};
		return JSON.toJSONString(source, filter, defaultFeatures);
	}

	/**
	 * 判断includes数组中是否包含name.
	 * 
	 */
	private static boolean hasProperty(String[] includes, String name) {
		if (includes == null) {
			return false;
		}
		for (String property : includes) {
			if (property != null && property.equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static <T> T fromJson(String json, Class<T> c){
		return JSON.parseObject(json, c);
	}


}
