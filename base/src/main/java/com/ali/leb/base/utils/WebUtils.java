package com.ali.leb.base.utils;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public final class WebUtils {

	private static Logger logger = Logger.getLogger(WebUtils.class);

	/** */
	public static final String CONTENTTYPE_TEXTHTML = "text/html";

	/** */
	public static final String CONTENTTYPE_TEXTJSON = "text/json";

	/** */
	public static final String CONTENTTYPE_TEXTXML = "text/xml";

	/** */
	public static final String CONTENT_CHARSET_GBK = "GBK";

	/** */
	public static final String CONTENT_CHARSET_GB2312 = "GB2312";

	/** */
	public static final String CONTENT_CHARSET_UTF8 = "UTF-8";

	/**
	 * 
	 */
	private WebUtils() {
	}

	/**
	 * 重载Spring WebUtils中的函数,作用如函数名所示 加入泛型转换,改变输入参数为request 而不是session
	 *
	 * @param <T>
	 * @param request
	 * @param name
	 *            session中变量名称
	 * @param clazz
	 *            session中变量的类型
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getOrCreateSessionAttribute(HttpServletRequest request,
                                                    String name, Class<T> clazz) {
		return (T) org.springframework.web.util.WebUtils
				.getOrCreateSessionAttribute(request.getSession(), name, clazz);
	}

	/**
	 * 直接通过<b>{@link HttpServletResponse}写数据到客户端</b>,可以指定编码,文件类型
	 * 
	 * @param response
	 *            {@link HttpServletResponse}
	 * @param contentType
	 *            文件类型
	 * @param charset
	 *            编码集
	 * @param s
	 *            数据
	 */
	public static void sendDirectToClient(HttpServletResponse response,
                                          String contentType, String charset, String s) {
		Assert.notNull(response);

		String charsetPrefix = org.springframework.web.util.WebUtils.CONTENT_TYPE_CHARSET_PREFIX;
		String contentHead = contentType + charsetPrefix + charset;
		response.setContentType(contentHead);
		try {
			PrintWriter writer = response.getWriter();
			writer.write(s == null ? "null" : s);
			writer.flush();
		} catch (IOException e) {
			logger.error(" 直接通过<b>{@link HttpServletResponse}写数据到客户端</b>错误-异常信息：",e);
		}
	}
}
