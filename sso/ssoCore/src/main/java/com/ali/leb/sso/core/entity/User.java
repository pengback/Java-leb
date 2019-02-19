package com.ali.leb.sso.core.entity;

import com.ali.leb.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.Alias;


@TableName(value="t_user")
public class User extends BaseEntity {

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "user_passwd")
    private String userPasswd;

    @TableField(value = "age")
    private Integer age;

    @TableField(value = "salt")
    private String salt;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
