package com.ali.leb.sso.core.filter;

import com.ali.leb.base.cache.RedisCacheHandle;

import java.util.concurrent.TimeUnit;

public class SsoFilterConfig {

    private String ssoServerHost;

    private String ssoServerPort;

    private String ssoServerName;

    private RedisCacheHandle redisCacheHandle;

    public final String ssoServerCheckLoginUrl = "/sso/checkLogin";

    public final String ssoServerCheckTokenUrl = "/sso/checkToken";

    public final String ssoServerLoginUrl = "/login";

    public final long SSO_WEB_JSESSIONID_LIMIT_TIME = 2 * 60 * 60;

    public final TimeUnit SSO_WEB_JSEESIONID_LIMIT_TIME_UNIT = TimeUnit.SECONDS;

    public SsoFilterConfig() {
    }

    public String getSsoServerHost() {
        return ssoServerHost;
    }

    public void setSsoServerHost(String ssoServerHost) {
        this.ssoServerHost = ssoServerHost;
    }

    public String getSsoServerPort() {
        return ssoServerPort;
    }

    public void setSsoServerPort(String ssoServerPort) {
        this.ssoServerPort = ssoServerPort;
    }

    public String getSsoServerCheckLoginUrl() {
        return ssoServerCheckLoginUrl;
    }

    public RedisCacheHandle getRedisCacheHandle() {
        return redisCacheHandle;
    }

    public void setRedisCacheHandle(RedisCacheHandle redisCacheHandle) {
        this.redisCacheHandle = redisCacheHandle;
    }

    public String getSsoServerName() {
        return ssoServerName;
    }

    public void setSsoServerName(String ssoServerName) {
        this.ssoServerName = ssoServerName;
    }

    public String ssoWebServerPrefix() {
        return this.getSsoServerHost() + ":" + this.getSsoServerPort() + "/" + this.getSsoServerName();
    }
}
