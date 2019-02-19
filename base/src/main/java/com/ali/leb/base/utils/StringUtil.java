
package com.ali.leb.base.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 字符处理类
 * 
 */

public class StringUtil extends StringUtils {

	/**
	 * 一位空格字符
	 * 
	 * @return
	 */
	public static final String BLANK = " ";
	/**
	 * 无空格字符
	 * 
	 * @return
	 */
	public static final String REALBLANK = "";

	/**
	 * 
	 * 替换非法字符
	 * 
	 */
	public static String replace(String str) {
		if (StringUtils.isNotEmpty(str)) {
			/*
			 * str = str.replace("'", "''").replace("]", "]]").replace("&",
			 * "chr(38)").replace("%", "chr(37)").replace("\\", "chr(92)")
			 * .replace("\"", "chr(34)").replace("_", "chr(95)");
			 */
			str = str.replace("'", "''").replace("%", "\\%")
					.replace("\\", "\\\\").replace("_", "\\_");
		}
		return str;
	}

	/**
	 * 
	 * 去掉尾部全部特定字符串
	 * 
	 */
	public static String removeEndStr(String str, String removeStr) {
		String str2 = StringUtils.removeEnd(str, removeStr);
		while (!str2.equals(str)) {
			str = str2;
			str2 = StringUtils.removeEnd(str, removeStr);
		}
		return str2;
	}

	/**
	 * 去零
	 * 
	 */
	public static String toNewsCode(String code, int length) {
		if (StringUtils.isNotEmpty(code)) {
			boolean temp = false;
			code = code.replace("\r", "");
			code = code.replace("\n", "");
			String template = String.format("%0" + length + "d", 0);
			while (!temp) {
				if (code.length() > length) {
					String newstr = code.substring(code.length() - length);
					if (template.equals(newstr))
						code = code.substring(0, code.length() - length);
					else
						temp = true;
				} else {
					temp = true;
				}
			}
		}
		return code;
	}

	/**
	 * 根绝关键词数组和需要查询表的列名数组拼接查询的sql条件
	 * 
	 * @param value
	 *            存放参数的map
	 * @param searchKeys
	 *            关键词数组
	 * @param columNames
	 *            列名数组
	 * @return 查询条件的sql语句
	 */
	public static String makeSearchSql(Map<String, Object> value,
                                       String[] searchKeys, String[] columNames) {
		if (searchKeys == null || columNames == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer(100);
		for (int index = 0; index < searchKeys.length; index++) {
			if (isBlank(searchKeys[index])) {
				continue;
			}
			sb.append(" and (");
			for (String col : columNames) {
				sb.append(col).append(" like :key").append(index)
						.append(" or ");
			}
			// 去除最后一个“or”，如果有的话
			int last = sb.lastIndexOf("or");
			if (last > 0) {
				sb.delete(last, last + 2);
			}
			sb.append(")");
			value.put("key" + index, "%" + searchKeys[index] + "%");
		}
		return sb.toString();
	}

	/**
	 * 把数字字符串小数点后面多余的零删掉，小数点全是零时，小数点也删掉 例如:100.00 => 100, 100.10 = 100.1
	 * 
	 * @param value
	 *            待转换的字符串
	 * @return value 转换后的字符串
	 */
	public static String formatStringForDelRedundant(String value) {
		if (StringUtils.isNotEmpty(value)) {
			if (value.indexOf(".") > 0) {
				value = value.replaceAll("0+?$", "");
				value = value.replaceAll("[.]$", "");
			}
		}
		return value;
	}

	/**
	 * ｛多个字符串验证是否存在字符串为空，存在返回true，不存在返回false｝
	 * 
	 * @param str
	 *            待验证数组字符串
	 * @return boolean 存在返回true，不存在返回false
	 */
	public static boolean strsIsEmpty(String... str) {
		if (null == str) {
			return true;
		}
		for (String s : str) {
			if (StringUtils.isEmpty(s)) {
				return true;
			}
		}
		return false;
	}

	public static String toString(Object target) {
		return target == null ? null : target.toString();
	}
}
