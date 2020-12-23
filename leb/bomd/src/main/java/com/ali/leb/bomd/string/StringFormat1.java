
package com.ali.leb.bomd.string;

/**
 * @Author: aliber
 * @Date: 2020/12/22 上午10:48
 **/
public class StringFormat1 {

    private static final String LOCK_KEY_TPL = "%s:lock:%s:";


    public static void main(String[] args) {
        String appId = "wx525b4502f57c89a9";
        System.out.println(String.format(LOCK_KEY_TPL, "appId", appId));
    }
}
