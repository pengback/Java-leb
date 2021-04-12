package com.ali.leb.auth2.modules.user.bean;

import lombok.Data;

/**
 * @Author: aliber
 * @Date: 2021/3/27 下午1:25
 **/
@Data
public class User {

    private Long id;

    private String name;

    private String password;

    private String rols;

}
