package com.ali.leb.sso.core.service;


import com.ali.leb.base.cache.RedisCacheHandle;
import com.ali.leb.base.utils.ContextStaticUtils;
import com.ali.leb.base.utils.StringUtil;
import com.ali.leb.base.utils.TokenFactoryBean;
import com.ali.leb.sso.core.filter.SsoFilterConfig;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Map;

@Service("ssoService")
public class SsoServiceImpl implements SsoService {

    private static Logger logger = Logger.getLogger(SsoServiceImpl.class);

    @Autowired
    private RedisCacheHandle redisCacheHandle;

    @Autowired
    private SsoFilterConfig ssoFilterConfig;

    /**
     * 检测本地系统是否登录
     *
     * @param jsessionid
     * @param m
     * @return
     */
    @Override
    public boolean checkLogin(String jsessionid, Map m) {
        String contextPath = StringUtils.toString(m.get(WEB_SERVICE_CONTEXTPATH));
        Object o = redisCacheHandle.getString(contextPath + ADD_STR + IS_LOGIN + jsessionid);
//        Object o = jedisCacheHandle.getJedis().get(contextPath + ADD_STR + IS_LOGIN + jsessionid);
//        Object o = redisTemplate.opsForValue().get(IS_LOGIN + jsessionid);
        if (StringUtils.equals(ContextStaticUtils.FLAG.TRUE_BOOLEAN_STRING, o)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检验token的有效性
     *
     * @return
     */
    @Override
    public boolean checkToken(String token, String jsessionid, Map p) {
        String contextPath = StringUtils.toString(p.get(WEB_SERVICE_CONTEXTPATH));
        Assert.notNull(contextPath, "contextPath is not empty");
        return StringUtil.equals(token, StringUtil.toString(redisCacheHandle.getString(contextPath + ADD_STR +
                USER_TOKEN + jsessionid)));
    }

    /**
     * 登录操作
     *
     * @param jsessionid
     * @param p
     * @return
     */
    @Override
    public String doLogin(String jsessionid, Map p) {
        // 将浏览器的jsessionid生成isLogin标识存入Redis.
        String contextPath = StringUtils.toString(p.get(WEB_SERVICE_CONTEXTPATH));
        redisCacheHandle.putString(contextPath + ADD_STR + IS_LOGIN + jsessionid, ContextStaticUtils.FLAG
                .TRUE_BOOLEAN_STRING, ssoFilterConfig.SSO_WEB_JSESSIONID_LIMIT_TIME, ssoFilterConfig
                .SSO_WEB_JSEESIONID_LIMIT_TIME_UNIT);
        // 生成token
        String token = TokenFactoryBean.getInstance().makeToken();
        redisCacheHandle.putString(contextPath + ADD_STR + USER_TOKEN + jsessionid, token, ssoFilterConfig
                .SSO_WEB_JSESSIONID_LIMIT_TIME, ssoFilterConfig.SSO_WEB_JSEESIONID_LIMIT_TIME_UNIT);
        return token;
    }

    @Override
    public String getTokenFromCache(String jsessionid, Map m) {
        return StringUtil.toString(redisCacheHandle.getString(StringUtil.toString(m.get(WEB_SERVICE_CONTEXTPATH) +
                ADD_STR + USER_TOKEN + jsessionid)));
    }


}
