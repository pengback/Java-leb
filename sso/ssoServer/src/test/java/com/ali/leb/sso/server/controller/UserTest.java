package com.ali.leb.sso.server.controller;

import com.ali.leb.sso.core.entity.User;
import com.ali.leb.sso.core.mapper.UserMapper;
import com.ali.leb.sso.core.service.SsoService;
import com.ali.leb.sso.server.service.UserService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring_core/applicationContext.xml")
public class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private SsoService ssoService;


    @Test
    public void Test(){
        try{
            //创建出SQLSession对象
            String id = "2d00d11c10a411e9ba0ea94e89c540f1";
            User user = userService.getUserById(id);
            System.out.println(JSON.toJSONString(user));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void TestRedis(){
        try{
            String jsessionid = "48ey2c73li57u0im265p5g1t";
            Map par = new HashMap();
            String token = ssoService.doLogin(jsessionid, par);
            System.out.println("token: ------------------ " + token);
            System.out.println(ssoService.checkLogin(jsessionid, par));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void UserTest(){
        User user = userService.getUserInfoByLoginName("abcd");
        System.out.println(JSON.toJSONString(user));
    }


}
