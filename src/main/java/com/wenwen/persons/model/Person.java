package com.wenwen.persons.model;

import lombok.Data;

import java.util.Date;

/**
 * @author xiaoxinga
 * @date 2018/9/10 11:19
 * @since
 */
@Data
@SuppressWarnings("serial")
public class Person {
    private String personId;// 用户ID

    private String personName;// 用户昵称

    private String sex;// 用户性别

    private String birthday;// 用户生日

    private String email;// 用户邮箱

    private String password;// 用户密码

    private String createTime;// 注册时间

    private String status;// 账号状态（1：已激活 0：未激活 2：账号被封）

    private Date unbLockTime;// 解封时间

    private long sealedTime;// 需被封时间

    private String activateCode;// 激活标识

    private String passwordCode;// 更改密码标识

    private String passwordCodeLastTime;// 更改密码最后时间
}
