package com.ali.leb.base.utils;

import java.util.Map;

/**
 * httpPostParam发送
 * 
 * @author xu.wei
 * @version 1.0 2013-7-31 改订
 * @since 1.0
 */
public class HttpPostParamSender {

	/**
	 * 默认的 超时时间
	 */
	public static int DEFULT_TIMEOUT = 60000;

	/**
	 * 默认的 编码格式
	 */
	public static String DEFULT_ENCODING = "UTF-8";

	/**
	 * 自定义转换器发�? .
	 * 
	 * @param url
	 *            请求url
	 * @param encoding
	 *            编码
	 * @param timeout
	 *            超时时间
	 * @param params
	 *            请求对象
	 * @return 响应对象
	 */
	public static String send(String url, String encoding, int timeout,
                              Map<String, String> params) {
		return send(url, encoding, timeout, params, null);
	}

	/**
	 * 发送XML数据信息 . 发送XML数据信息 .
	 * 
	 * @param url
	 *            .
	 * @param encoding
	 *            .
	 * @param timeout
	 *            .
	 * @param params
	 *            .
	 * @return String .
	 */
	public String sendXML(String url, String encoding, int timeout,
                          String params) {
		return this.sendXML(url, encoding, timeout, params, null);
	}

	/**
	 * 自定义转换器发�? .
	 * 
	 * @param url
	 *            请求url
	 * @param encoding
	 *            编码
	 * @param timeout
	 *            超时时间
	 * @param params
	 *            请求对象
	 * @param httpHeader
	 *            http�?
	 * @return 响应对象
	 */
	public static String send(String url, String encoding, int timeout,
                              Map<String, String> params, Map<String, String> httpHeader) {
		SimpleHttpRequest simpleHttpRequest = new SimpleHttpRequest(url,
				params, encoding, timeout, httpHeader);
		SimpleHttpResponse simpleHttpResponse = SimpleHttpUtil
				.post(simpleHttpRequest);
		if (SimpleHttpResponse.STATUS_SUCCESS == simpleHttpResponse.getStatus()) {
			return simpleHttpResponse.getResponseBody();
		}
		throw new RuntimeException(String.format(
				"Http status error, httpStatus = %s",
				simpleHttpResponse.getStatus()));
	}

	/**
	 * 发送XML数据信息 .
	 * 
	 * @param url
	 *            .
	 * @param encoding
	 *            .
	 * @param timeout
	 *            .
	 * @param params
	 *            .
	 * @param httpHeader
	 *            .
	 * @return String .
	 */
	public String sendXML(String url, String encoding, int timeout,
                          String params, Map<String, String> httpHeader) {
		SimpleHttpRequest simpleHttpRequest = new SimpleHttpRequest(url,
				params, encoding, timeout, httpHeader);
		SimpleHttpResponse simpleHttpResponse = SimpleHttpUtil
				.postXml(simpleHttpRequest);
		if (SimpleHttpResponse.STATUS_SUCCESS == simpleHttpResponse.getStatus()) {
			return simpleHttpResponse.getResponseBody();
		}
		throw new RuntimeException(String.format(
				"Http status error, httpStatus = %s",
				simpleHttpResponse.getStatus()));
	}
}
