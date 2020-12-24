package com.ali.leb.bomd.designpattern.responseandstrategy.res.message;

import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseOutMessage;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午4:55
 **/
@Data
public class ResOutMessage extends BaseOutMessage {

    private String messageCode;
    private String messageBody;

    @Override
    public String printMessageInfo() {
        return JSONObject.toJSON(this).toString();
    }
}
