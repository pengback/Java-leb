package com.ali.leb.sso.server.utils;

import com.ali.leb.base.utils.MD5Utils;
import com.ali.leb.base.utils.PDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring_core/applicationContext.xml")
public class Md5Test {

    @Test
    public void test(){
        String passwd = "123456";
        String p1 = MD5Utils.MD5Encode(passwd, "");
        String p2 = MD5Utils.MD5Encode(passwd+"salt", "");
        String p3 = MD5Utils.MD5Encode(passwd+"common", "");
        String p4 = MD5Utils.MD5Encode(passwd, "");

        String pp1 = MD5Utils.MD5Encode(p1, "");

        System.out.println("p1--------"+p1);
        System.out.println("p2--------"+p2);
        System.out.println("p3--------"+p3);
        System.out.println("p4--------"+p4);
        System.out.println("pp1--------"+pp1);
    }

    @Test
    public void passwdTest(){
        String passwd = "123456";
        String origin1 = "2b6ed6d0b5374f95b006adc221c39d81";
        String o2 = "dd1776e95b93d50a6a29cf5fb396748a";
        String o3 = "9e3b2114cffc3e6fcd5b230979d089f9";
        String oo1 = "4a92c7472f5165cd6cf77ae3d43fb34d";
        System.out.println(PDUtils.comparePasswd(origin1, passwd, ""));
        System.out.println(PDUtils.comparePasswd(o3, passwd, "salt", ""));
        System.out.println(PDUtils.comparePasswd(o3, passwd, "common", ""));
        System.out.println(PDUtils.comparePasswd(oo1, passwd, ""));
        System.out.println(PDUtils.comparePasswd(oo1, PDUtils.encodePasswd(passwd, ""), ""));
    }

}
