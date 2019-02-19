package com.ali.leb.sso.core.service;


import java.util.Map;

public interface SsoService {

    public final static String ADD_STR = "_";

    public final static String IS_LOGIN = "sso_is_login_";

    public final static String USER_TOKEN = "sso_token_";

    public final static String WEB_SERVICE_CONTEXTPATH = "web_service_contextpath";

    /**
     * 检测本地系统是否登录
     *
     * @param jsessionid
     * @param m
     * @return
     */
    public boolean checkLogin(String jsessionid, Map m);

    /**
     * 检验token的有效性
     *
     * @return
     */
    public boolean checkToken(String token, String jsessionid, Map p);

    /**
     * 登录操作
     *
     * @param jsessionid
     * @param p
     * @return
     */
    public String doLogin(String jsessionid, Map p);

    /**
     * 根据JSESSIONID 从cache中获取已经登录的token
     * @param jsessionid
     * @return
     */
    String getTokenFromCache(String jsessionid, Map m);
}
