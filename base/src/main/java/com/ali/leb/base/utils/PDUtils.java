package com.ali.leb.base.utils;

import org.apache.log4j.Logger;

public class PDUtils {

    private static Logger logger = Logger.getLogger(PDUtils.class);

    /**
     * 无salt加密
     *
     * @param passwd      密码
     * @param charsetname 字符编码名称, 可为空
     * @return 返回加密后的密文
     */
    public static String encodePasswd(String passwd, String charsetname) {
        return encodePasswd(passwd, "", charsetname);
    }

    /**
     * 有salt加密
     *
     * @param passwd      密码
     * @param salt        salt
     * @param charsetname 字符编码名称, 可为空
     * @return 返回加密后的密文
     */
    public static String encodePasswd(String passwd, String salt, String charsetname) {
        if (StringUtil.isNotBlank(salt)) {
            passwd = passwd + salt;
        }
        return MD5Utils.MD5Encode(passwd, charsetname);
    }

    /**
     * 密码验证,相同返回true,不同返回false
     *
     * @param origin      密文
     * @param passwd      明文
     * @param charsetname 字符编码名称, 可为空
     * @return 相同返回true, 不同返回false
     */
    public static boolean comparePasswd(String origin, String passwd, String charsetname) {
        return StringUtil.equals(origin, encodePasswd(passwd, charsetname));
    }

    /**
     * 密码验证,相同返回true,不同返回false
     *
     * @param origin      密文
     * @param passwd      明文
     * @param salt
     * @param charsetname 字符编码名称, 可为空
     * @return 相同返回true, 不同返回false
     */
    public static boolean comparePasswd(String origin, String passwd, String salt, String charsetname) {
        return StringUtil.equals(origin, encodePasswd(passwd, salt, charsetname));
    }
}
