package com.wenwen.persons.model;

import lombok.Data;

/**
 * @author xiaoxinga
 * @date 2018/9/10 11:19
 * @since
 */
@Data
@SuppressWarnings("serial")
public class Person {
    private String personId;//用户ID

    private String personName;//用户昵称

    private String personSex;//用户性别

    private String personBirthday;//用户生日

    private String personEmail;//用户邮箱

    private String personPassword;//用户密码

    private String createTime;//注册时间
}