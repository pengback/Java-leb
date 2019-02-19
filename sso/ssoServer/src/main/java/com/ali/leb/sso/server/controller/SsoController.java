package com.ali.leb.sso.server.controller;

import com.ali.leb.base.utils.JSONUtils;
import com.ali.leb.base.utils.ResultUtil;
import com.ali.leb.base.utils.StringUtil;
import com.ali.leb.base.utils.dto.ResultDto;
import com.ali.leb.sso.core.service.SsoService;
import com.ali.leb.sso.server.service.LoginService;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sso/")
public class SsoController {

    private Logger logger = Logger.getLogger(SsoController.class);

    @Autowired
    private SsoService ssoService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "checkLogin")
    public void checkLogin(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
        String redirectUrl = request.getParameter("redirectUrl");
        logger.info("来自" + redirectUrl + "的全局登录校验请求");
//        String jsessionid = (String)request.getAttribute("JSESSIONID");
        String jsessionid = request.getSession().getId();
        Map m = new HashMap();
        m.put(SsoService.WEB_SERVICE_CONTEXTPATH, request.getContextPath());
        if (ssoService.checkLogin(jsessionid, m)) {
            StringBuffer s = new StringBuffer(redirectUrl);
            s.append("?JSESSIONID=").append(jsessionid);
            s.append("&token=").append(ssoService.getTokenFromCache(jsessionid, m));
            response.sendRedirect(s.toString());
//            return "";
        } else {
//            StringBuffer url = new StringBuffer();
//            url.append("index/login?");
            response.sendRedirect(request.getContextPath() + "/login?redirectUrl=" + redirectUrl + "&JSESSIONID=" +
                    jsessionid);
//            model.addAttribute("redirectUrl", redirectUrl);
//            return "index/login";
//            response.sendRedirect();
        }
    }

    @RequestMapping(value = "checkToken", method = RequestMethod.POST)
    public void checkToken(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam("token") String token, @RequestParam("JSESSIONID") String jsessionId) {
        Map p = new HashMap();
        p.put(SsoService.WEB_SERVICE_CONTEXTPATH, request.getContextPath());
        ResultDto d = new ResultDto();
        if (ssoService.checkToken(token, jsessionId, p)) {
            d.setFlag(ResultUtil.TRUE);
            d.setResult("token is valid!");
        } else {
            d.setFlag(ResultUtil.FALSE);
            d.setResult("token is invalid!");
        }
        ResultUtil.sendObject(response, d);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
//    @ResponseBody
    public void doLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam("loginName") String
            loginName,
                        @RequestParam("passwd") String passwd) throws IOException {
//        String jsessionid = (String)request.getAttribute("JSESSIONID");
        String jsessionid = request.getSession().getId();
        String redirectUrl = StringUtils.toString(request.getParameter("redirectUrl"));
        // 进行用户登录时的密码验证
        ResultDto resultDto = loginService.doLogin(loginName, passwd);
        logger.info(JSON.toJSON(resultDto));
        if (!StringUtil.equals(resultDto.getFlag(), ResultUtil.TRUE)) {
            // 登录不成功,页面给出响应的提示
            ResultUtil.sendMsg(response, resultDto);

        } else {
            // sso记录相关信息到cache中
            Map p = new HashMap();
            p.put(SsoService.WEB_SERVICE_CONTEXTPATH, request.getContextPath());
            String token = ssoService.doLogin(jsessionid, p);
            // 登录成功页面重定向到需要访问的页面
            StringBuffer url = new StringBuffer();
            if (StringUtil.isNotBlank(redirectUrl)) {
                url.append(redirectUrl);
            }
            url.append("?token=").append(token);
            url.append("&JSESSIONID=").append(jsessionid);

            String origin = StringUtil.toString(request.getRequestURL()).split(request.getContextPath())[0];

            //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理
//            if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //告诉ajax我是重定向

            response.setHeader("REDIRECT", "REDIRECT");
            //告诉ajax我重定向的路径
            response.setHeader("CONTEXTPATH", url.toString());
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            }else{
//                response.sendRedirect(url.toString());
//            }
            ResultUtil.sendMsg(response, resultDto);
        }
    }

}
