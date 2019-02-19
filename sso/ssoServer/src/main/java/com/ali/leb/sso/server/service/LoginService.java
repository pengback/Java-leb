package com.ali.leb.sso.server.service;

import com.ali.leb.base.cache.RedisCacheHandle;
import com.ali.leb.base.utils.PDUtils;
import com.ali.leb.base.utils.ResultUtil;
import com.ali.leb.base.utils.StringUtil;
import com.ali.leb.base.utils.dto.ResultDto;
import com.ali.leb.sso.core.entity.User;
import com.ali.leb.sso.core.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

@Service
public class LoginService {

    public static final String LOGIN_ERROR_COUNT = "login_error_count_";

    public static final Integer LOGIN_ERROR_COUNT_NUM = 5;

    public static final String LOGIN_ERROR_COUNT_MESSAGE = "您登录次数过多请稍后再试!";

    public static final long LOGIN_ERROR_COUNT_TIME = 30 * 60;

    public static final TimeUnit LOGIN_ERROR_COUNT_TIME_UNIT = TimeUnit.SECONDS;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisCacheHandle redisCacheHandle;

    public ResultDto doLogin(String loginName, String passwd) {
        ResultDto resultDto = new ResultDto();

        User user = userMapper.getUserInfoByLoginName(loginName);

        // 登录次数校验
        String countRedis = LOGIN_ERROR_COUNT + loginName;
        String countStr = StringUtil.toString(redisCacheHandle.getString(countRedis));
        Integer count = countStr == null ? 0 : Integer.valueOf(countStr);
        if (count > LOGIN_ERROR_COUNT_NUM) {
            resultDto.setFlag(ResultUtil.FALSE);
            resultDto.setResult(LOGIN_ERROR_COUNT_MESSAGE);
            return resultDto;
        }
        // 将用户登录的次数记录早cache中,并重置计时器时间
        redisCacheHandle.putString(countRedis, StringUtil.toString(count + 1), LOGIN_ERROR_COUNT_TIME, LOGIN_ERROR_COUNT_TIME_UNIT);


        // 用户密码校验
        if (PDUtils.comparePasswd(user.getUserPasswd(), passwd, user.getSalt(), "")) {
            // 清楚登录次数记录
            redisCacheHandle.delete(countRedis);
            resultDto.setFlag(ResultUtil.TRUE);
            resultDto.setResult("登录成功");
        } else {
            resultDto.setFlag(ResultUtil.FALSE);
            resultDto.setResult("登录失败,用户名或密码失败");
        }

        return resultDto;
    }

}
