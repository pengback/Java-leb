/**
 * 项目名称(upChannel)
 * Copyright (c) 2013 ChinaPay Ltd. All Rights Reserved.
 */
package com.ali.leb.base.utils;

/**
 * �?��http响应�?
 * 
 * @author xu.wei
 * @version 1.0 2013-7-31 改订
 * @since 1.0
 */
public class SimpleHttpResponse {
    /**
     * http状�?成功.
     */
    public final static int STATUS_SUCCESS = 200;
    /**
     * http状�?.
     */
    private int status;
    /**
     * http响应�?
     */
    private String responseBody;

    /**
     * 构�?函数 .
     * 
     * @param status
     *            状�?
     * @param responseBody
     *            响应�?
     */
    public SimpleHttpResponse(int status, String responseBody) {
        this.status = status;
        this.responseBody = responseBody;
    }

    /**
     * 获取http状�?.
     * @return http状�?. 
     */
    public int getStatus() {
        return status;
    }

    /**
     * 获取http响应�?
     * @return http响应�? 
     */
    public String getResponseBody() {
        return responseBody;
    }

}
