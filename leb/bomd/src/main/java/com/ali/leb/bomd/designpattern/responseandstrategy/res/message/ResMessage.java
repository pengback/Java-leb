package com.ali.leb.bomd.designpattern.responseandstrategy.res.message;

import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseMessage;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午5:14
 **/
@Data
public class ResMessage extends BaseMessage {
    String messageCode;
    String messageState;
    String messageBody;

    @Override
    public String getMatchCode() {
        return this.messageCode;
    }

    @Override
    public String printMessageInfo() {
        return JSONObject.toJSON(this).toString();
    }
}
