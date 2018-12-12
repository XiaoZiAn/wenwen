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
    private String id;//用户ID

    private String name;//用户昵称

    private String sex;//用户性别

    private String birthday;//用户生日

    private String email;//用户邮箱

    private String password;//用户密码

    private String createTime;//注册时间
}
