package com.ali.leb.bomd.designpattern.responseandstrategy.res.err;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午2:03
 **/
public enum ResErr {

    ERR_1("error_1", "错误1"),
    ERR_2("error_2", "错误2"),
    ERR_3("error_3", "错误3");

    private String code;
    private String desc;

    ResErr(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
