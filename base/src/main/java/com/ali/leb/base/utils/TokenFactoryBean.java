package com.ali.leb.base.utils;


import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class TokenFactoryBean {

    private static Logger logger = Logger.getLogger(TokenFactoryBean.class);

    private static final TokenFactoryBean instance = new TokenFactoryBean();

    private TokenFactoryBean(){

    }

    public static TokenFactoryBean getInstance(){
        return instance;
    }

    /**
     * 生成token值
     * @return
     */
    public String makeToken(){
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + ""; ;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            token = encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            logger.error("makeToken error");
            e.printStackTrace();
        } finally {
            return token;
        }
    }


}
