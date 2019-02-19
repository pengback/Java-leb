/**
 * 项目名称(upChannel)
 * Copyright (c) 2013 ChinaPay Ltd. All Rights Reserved.
 */
package com.ali.leb.base.utils;

import java.util.Map;

/**
 * �?��http请求�?
 * 
 * @author xu.wei
 * @version 1.0 2013-7-31 改订
 * @since 1.0
 */
public class SimpleHttpRequest {
    /**
     * 请求地址.
     */
    private String url;
    /**
     * 请求参数 post方法使用.
     */
    private Map<String, String> params;
    /**
     * 请求报文�?postXml方法使用.
     */
    private String requestBody;
    /**
     * 编码.
     */
    private String encoding;
    /**
     * 超时.
     */
    private int timeout;
    /**
     * http�?
     */
    private Map<String, String> httpHeader;

    /**
     * 构�?函数,调用post方法时使�?.
     * 
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @param encoding
     *            编码
     * @param timeout
     *            超时
     */
    public SimpleHttpRequest(String url, Map<String, String> params,
                             String encoding, int timeout) {
        this(url, params, encoding, timeout, null);
    }

    /**
     * 构�?函数,调用post方法时使�?.
     * 
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @param encoding
     *            编码
     * @param timeout
     *            超时
     * @param httpHeader
     *            http�?
     */
    public SimpleHttpRequest(String url, Map<String, String> params,
                             String encoding, int timeout, Map<String, String> httpHeader) {
        this.url = url;
        this.params = params;
        this.encoding = encoding;
        this.timeout = timeout;
        this.httpHeader = httpHeader;
    }

    /**
     * 构�?函数,调用postXml方法时使�?.
     * 
     * @param url
     *            请求地址
     * @param requestBody
     *            请求�?
     * @param encoding
     *            编码
     * @param timeout
     *            超时
     */
    public SimpleHttpRequest(String url, String requestBody, String encoding,
                             int timeout) {
        this(url, requestBody, encoding, timeout, null);
    }

    /**
     * 构�?函数,调用postXml方法时使�?.
     * 
     * @param url
     *            请求地址
     * @param requestBody
     *            请求�?
     * @param encoding
     *            编码
     * @param timeout
     *            超时
     * @param httpHeader
     *            http�?
     */
    public SimpleHttpRequest(String url, String requestBody, String encoding,
                             int timeout, Map<String, String> httpHeader) {
        this.url = url;
        this.requestBody = requestBody;
        this.encoding = encoding;
        this.timeout = timeout;
        this.httpHeader = httpHeader;
    }

    /**
     * 获取请求地址.
     * @return 请求地址. 
     */
    public String getUrl() {
        return url;
    }

    /**
     * 获取请求参数 post方法使用.
     * @return 请求参数 post方法使用. 
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * 获取请求报文�?postXml方法使用.
     * @return 请求报文�?postXml方法使用. 
     */
    public String getRequestBody() {
        return requestBody;
    }

    /**
     * 获取编码.
     * @return 编码. 
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * 获取超时.
     * @return 超时. 
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * 获取http�?
     * @return http�? 
     */
    public Map<String, String> getHttpHeader() {
        return httpHeader;
    }

}