package com.ali.leb.bomd.string;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: aliber
 * @Date: 2020/12/31 下午4:15
 **/
public class StringSplit {

    public static void main(String[] args) {
        String s = "/werwer/lwekrj/ertqw/";
        System.out.println(JSONObject.toJSON(s.split("/")));
        System.out.println(s.substring(1, s.length()));
        System.out.println(s.substring(0, s.length() - 1));

        System.out.println(s.replace("/werwer/", "http://www.geeshu.com/"));
    }

}
