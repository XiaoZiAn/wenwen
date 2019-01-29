package com.wenwen.base.enums;

/**
 * @author xiaoxinga
 * @date 2019/01/18 10:42
 * @since
 */
public enum EmailType {
    ACTIVATE_EMAIL("账号激活邮件", "ActivateEmail"),
    CHANGEPASSWORD_EMAIL("账号密码修改邮件","ChangePasswordEmail");
    public final String name;
    public final String code;

    EmailType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static EmailType getResultEnumsByCode(String code) {
        for (EmailType emailType : EmailType.values()) {
            if (emailType.code.equals(code)) {
                return emailType;
            }
        }
        return null;
    }
}
