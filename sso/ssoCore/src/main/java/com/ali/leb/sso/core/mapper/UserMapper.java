package com.ali.leb.sso.core.mapper;


import com.ali.leb.base.mapper.SuperMapper;
import com.ali.leb.sso.core.entity.User;

public interface UserMapper extends SuperMapper<User> {

    User doLogin(String userName, String userPasswd);

    User getUserInfoByName(String userName);

    User getUserInfoByLoginName(String loginName);
}
