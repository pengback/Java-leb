package com.ali.leb.sso.server.service;

import com.ali.leb.sso.core.entity.User;
import com.ali.leb.sso.core.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserService  {

    private static Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;


    public User getUserInfoByName(String username) {

        User user = userMapper.getUserInfoByName(username);
        return user;
    }

    public User getUserById(String id){
        return userMapper.selectById(id);
    }

    public User getUserInfoByLoginName(String abc) {
        return userMapper.getUserInfoByLoginName(abc);
    }
}
