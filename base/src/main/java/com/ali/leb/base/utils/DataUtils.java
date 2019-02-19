package com.ali.leb.base.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DataUtils {

	/**
	 * 整数不限，补两位小数
	 */
	public static final String INTEGER_2_DECIMALS = "#.00";

	public static final String SPECIAL_NULL = "";

	/**
	 * bigDecimal数字格式化为字符串
	 * 
	 * @param bigDecimal
	 *            数字
	 * @param format
	 *            数字格式
	 * @return
	 */
	public static String bigDecimal2Str(BigDecimal bigDecimal, String format) {
		if (null == bigDecimal) {
			return SPECIAL_NULL;
		}
		DecimalFormat df = new DecimalFormat(format);
		return df.format(bigDecimal);
	}

}
