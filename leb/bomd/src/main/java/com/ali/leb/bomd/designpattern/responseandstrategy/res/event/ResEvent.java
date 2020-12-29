package com.ali.leb.bomd.designpattern.responseandstrategy.res.event;

import com.ali.leb.bomd.designpattern.responseandstrategy.event.BaseEvent;
import com.ali.leb.bomd.designpattern.responseandstrategy.res.err.ResErr;
import lombok.Data;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午5:36
 **/
@Data
public class ResEvent implements BaseEvent {

    private String eventCode;   // 10001
    private String eventDesc;   // 银行卡密码不正确

    private int status;

    public ResEvent(ResErr err) {
        this.eventCode = err.getCode();
        this.eventDesc = err.getDesc();
    }

    @Override
    public String getEventCode() {
        return eventCode;
    }
}
