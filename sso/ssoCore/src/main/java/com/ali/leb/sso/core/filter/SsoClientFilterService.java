package com.ali.leb.sso.core.filter;


import com.ali.leb.base.cache.RedisCacheHandle;
import com.ali.leb.base.utils.*;
import com.ali.leb.base.utils.dto.ResultDto;
import com.ali.leb.sso.core.service.SsoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class SsoClientFilterService {

    private Logger logger = Logger.getLogger(SsoClientFilterService.class);

    private RedisCacheHandle redisCacheHandle;

    public RedisCacheHandle getRedisCacheHandle() {
        return redisCacheHandle;
    }

    public void setRedisCacheHandle(RedisCacheHandle redisCacheHandle) {
        this.redisCacheHandle = redisCacheHandle;
    }

    private static final SsoClientFilterService filterService = new SsoClientFilterService();

    public static SsoClientFilterService getInstance() {
        return filterService;
    }

    /**
     * 当前本地登录检测
     *
     * @param jsessionid
     * @param m
     * @return
     */
    public boolean checkLocalLogin(String jsessionid, Map m) {
        String contextPath = StringUtils.toString(m.get(SsoService.WEB_SERVICE_CONTEXTPATH));
        Object o = redisCacheHandle.getString(contextPath + SsoService.ADD_STR + SsoService.IS_LOGIN + jsessionid);
//        Object o = redisTemplate.opsForValue().get(IS_LOGIN + jsessionid);
        if (StringUtils.equals(ContextStaticUtils.FLAG.TRUE_BOOLEAN_STRING, o)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * sso token 合法性验证
     * 使用http请求sso-server服务器进行验证
     *
     * @param jsessionid
     * @param token
     * @param config
     * @return
     */
    public boolean checkToken(String jsessionid, String token, SsoFilterConfig config) {
        Map param = new HashMap();
        param.put("token", token);
        param.put("JSESSIONID", jsessionid);

        String s = HttpPostParamSender.send(config.ssoWebServerPrefix() + config.ssoServerCheckTokenUrl, HttpPostParamSender.DEFULT_ENCODING,
                HttpPostParamSender.DEFULT_TIMEOUT, param);
        ResultDto r = JSON.parseObject(s, ResultDto.class);
        if(StringUtil.equals(ResultUtil.TRUE, r.getFlag())){
            return true;
        } else {
            return false;
        }
    }


    /**
     * 当前本地系统登录
     * 记录 jsessionid 和 token 以便再次请求,不用sso端验证
     *
     * @param jsessionid
     * @param token
     */
    public void doLocalLogin(String jsessionid, String token, SsoFilterConfig config, Map m) {
        String contextPath = StringUtils.toString(m.get(SsoService.WEB_SERVICE_CONTEXTPATH));
        redisCacheHandle.putString(contextPath + SsoService.ADD_STR + SsoService.IS_LOGIN + jsessionid,
                ContextStaticUtils.FLAG.TRUE_BOOLEAN_STRING, config.SSO_WEB_JSESSIONID_LIMIT_TIME, config
                        .SSO_WEB_JSEESIONID_LIMIT_TIME_UNIT);
    }
}
