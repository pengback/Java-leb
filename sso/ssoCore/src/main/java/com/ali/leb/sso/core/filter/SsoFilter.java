package com.ali.leb.sso.core.filter;

import com.ali.leb.base.utils.StringUtil;
import com.ali.leb.sso.core.service.SsoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.thymeleaf.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SsoFilter implements Filter {

    private static Logger logger = Logger.getLogger(SsoFilter.class);

    private String excludedPages;

    private String[] excludedPagesArray;

    private SsoClientFilterService filterService = SsoClientFilterService.getInstance();

    private SsoFilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("---------------------- SSO Filter start init ----------------------");
        excludedPages = filterConfig.getInitParameter("excludedPages");
        if (!StringUtils.isEmpty(excludedPages)) {
            excludedPagesArray = excludedPages.split(",");
        }

        WebApplicationContext wac = (WebApplicationContext) filterConfig.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//        filterService.setRedisCacheHandle(wac.getBean(RedisCacheHandle.class));
        config = wac.getBean(SsoFilterConfig.class);
        filterService.setRedisCacheHandle(config.getRedisCacheHandle());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("---------------------- begin SSO doFilter ----------------------");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

//        resp.setHeader("Access-Control-Allow-Origin", "*");
//        resp.addHeader("Access-Control-Allow-Credentials", "true");
//        resp.addHeader("Access-Control-Allow-Methods", "HEAD,POST,GET,PUT,DELETE,OPTIONS");
//        resp.setHeader("Access-Control-Allow-Headers",
//                "Authentication, Authorization, content-type, Accept, x-requested-with, Cache-Control");

        // 判断是否为当前系统不需要登录即可访问的页面
        if (isExcludePages(req.getRequestURI(), req.getContextPath())) {
            chain.doFilter(req, resp);
            return;
        }

        /**
         * 判断当前系统是否登录
         * 若当前系统未登录,则调用sso-server服务判断是否登录 sso (利用url重定向访问 ssoServer/checkLogin接口进行判断)
         * 若sso未登录,则跳转至 统一登录界面,登录后进行后续访问
         * 若sso登录,则将登录信息传递至本系统,进行后续访问
         */
//        String jsessionid = StringUtils.toString(req.getAttribute("JSESSIONID"));
        String jsessionid = session.getId();
        String token = StringUtil.toString(req.getParameter("token"));
        Map m = new HashMap();
        m.put(SsoService.WEB_SERVICE_CONTEXTPATH, req.getContextPath());
        if (filterService.checkLocalLogin(jsessionid, m) && jsessionid != null) {
            // 本系统以及登录
            chain.doFilter(req, resp);
            return;
        } else {
            // 是否有token, 无token说明sso未登录,需要跳转到sso登录界面
            if(StringUtil.isNotBlank(token)){
                // 有token说明可能是已经进行sso全局登录, 需要验证token的合法性
                String ssoJsessionid = StringUtil.toString(req.getParameter("JSESSIONID"));

                if(filterService.checkToken(ssoJsessionid, token, config)){
                    // token合法
                    filterService.doLocalLogin(jsessionid, token, config, m);
                    logger.info("token 验证合法: ssoJSESSIONID----" + ssoJsessionid + "---token----" + token);
                    chain.doFilter(req, resp);
                } else {
                    // token不合法,跳转至login界面
                    redirectSsoCheckLoginUrl(req, resp, config.ssoServerLoginUrl);
                }
            } else {
                // 未全局登录, url重定向至登录界面
                redirectSsoCheckLoginUrl(req, resp, config.getSsoServerCheckLoginUrl());
            }
        }

//        resp.sendRedirect(req.getContextPath() + "/login");
        logger.info("---------------------- end SSO doFilter ----------------------");
    }

    private void redirectSsoCheckLoginUrl(HttpServletRequest req, HttpServletResponse resp, String path) throws IOException {
        StringBuffer url = new StringBuffer(config.ssoWebServerPrefix() + path);
        url.append("?redirectUrl=");
        url.append(req.getRequestURL());
        resp.sendRedirect(url.toString());
    }

    @Override
    public void destroy() {
        logger.info("---------------------- SSO Filter destory ----------------------");
    }

    private boolean isExcludePages(String uri, String contextPath) {
        String[] a = uri.split(contextPath);
        AntPathMatcher matcher = new AntPathMatcher();
        for (String s : excludedPagesArray) {
            if (matcher.match(s, a[1]))
                return true;
        }
        return false;
    }


}

