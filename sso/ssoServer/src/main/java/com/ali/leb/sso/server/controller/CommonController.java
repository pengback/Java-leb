package com.ali.leb.sso.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommonController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welcomeIndex(){
        return "index/index";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        String redirectUrl = request.getParameter("redirectUrl");
        model.addAttribute("redirectUrl", redirectUrl);
        return "index/login";
    }

}
