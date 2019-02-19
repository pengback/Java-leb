/**
 * 项目名称(upChannel)
 * Copyright (c) 2013 ChinaPay Ltd. All Rights Reserved.
 */
package com.ali.leb.base.utils;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.IOException;
import java.util.Map;

/**
 * http�?��工具�?
 * 
 * @author xu.wei
 * @version 1.0 2013-7-31 改订
 * @since 1.0
 */
public class SimpleHttpUtil {
    /**
     * 参数方式post发�? .
     * 
     * @param simpleHttpRequest
     *            请求
     * @return 响应
     */
    public static SimpleHttpResponse post(SimpleHttpRequest simpleHttpRequest) {
        try {
            HttpClient httpClient = buildHttpClient(simpleHttpRequest);

            PostMethod method = new PostMethod(simpleHttpRequest.getUrl());
            method.addRequestHeader("Connection", "close"); // 服务端应答完毕自动关闭连�?
            if (simpleHttpRequest.getHttpHeader() != null) {
                for (Map.Entry<String, String> entry : simpleHttpRequest
                        .getHttpHeader().entrySet()) {
                    method.addRequestHeader(entry.getKey(), entry.getValue());
                }
            }

            if (simpleHttpRequest.getParams() != null
                    && simpleHttpRequest.getParams().size() > 0) {
                NameValuePair[] nameValuePairs = new NameValuePair[simpleHttpRequest
                        .getParams().size()];
                int i = 0;
                for (Map.Entry<String, String> entry : simpleHttpRequest
                        .getParams().entrySet()) {
                    nameValuePairs[i++] = new NameValuePair(entry.getKey(),
                            entry.getValue());
                }
                method.addParameters(nameValuePairs);
            }

            int status = httpClient.executeMethod(method);
            String response = null;
            if (status == HttpStatus.SC_OK) {
                response = method.getResponseBodyAsString();
            }

            return new SimpleHttpResponse(status, response);
        } catch (HttpException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    /**
     * xml方式post提交 .
     * 
     * @param simpleHttpRequest
     *            请求
     * @return 响应
     */
    public static SimpleHttpResponse postXml(SimpleHttpRequest simpleHttpRequest) {
        try {
            HttpClient httpClient = buildHttpClient(simpleHttpRequest);

            PostMethod method = new PostMethod(simpleHttpRequest.getUrl());
            method.addRequestHeader("Connection", "close"); // 服务端应答完毕自动关闭连�?
            if (simpleHttpRequest.getHttpHeader() != null) {
                for (Map.Entry<String, String> entry : simpleHttpRequest
                        .getHttpHeader().entrySet()) {
                    method.addRequestHeader(entry.getKey(), entry.getValue());
                }
            }

            if (simpleHttpRequest.getRequestBody() != null
                    && simpleHttpRequest.getRequestBody().length() > 0) {
                method.setRequestEntity(new StringRequestEntity(
                        simpleHttpRequest.getRequestBody(), "text/xml",
                        simpleHttpRequest.getEncoding()));
            }

            int status = httpClient.executeMethod(method);
            String response = null;
            if (status == HttpStatus.SC_OK) {
                response = method.getResponseBodyAsString();
            }

            return new SimpleHttpResponse(status, response);
        } catch (HttpException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    /**
     * 构�?httpcient .
     * 
     * @param simpleHttpRequest
     *            http请求参数
     * @return 返回httpclient
     */
    private static HttpClient buildHttpClient(
            SimpleHttpRequest simpleHttpRequest) {
        MultiThreadedHttpConnectionManager connectionmanager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = new HttpClient(connectionmanager);
        httpClient.getParams().setContentCharset(
                simpleHttpRequest.getEncoding());
        connectionmanager.getParams().setConnectionTimeout(
                simpleHttpRequest.getTimeout()); // 设置连接超时时间(单位毫秒)
        connectionmanager.getParams().setSoTimeout(
                simpleHttpRequest.getTimeout()); // 设置读数据超时时�?单位毫秒)
        return httpClient;
    }

}
