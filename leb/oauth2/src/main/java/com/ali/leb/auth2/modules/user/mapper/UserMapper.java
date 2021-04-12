package com.ali.leb.auth2.modules.user.mapper;

import com.ali.leb.auth2.modules.user.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDate;

/**
 * @Author: aliber
 * @Date: 2021/3/27 下午1:33
 **/
public interface UserMapper extends BaseMapper<User> {

    public static void main(String[] args) {
        LocalDate data = LocalDate.now();

    }

}
