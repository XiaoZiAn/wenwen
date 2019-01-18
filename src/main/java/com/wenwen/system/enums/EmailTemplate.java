package com.wenwen.system.enums;

/**
 * @author xiaoxinga
 * @date 2019/01/18 10:42
 * @since
 */
public enum  EmailTemplate {
    ACTIVATE_EMAIL("账号激活邮件","ActivateEmail");
    public final String name;
    public final String code;

    EmailTemplate(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static EmailTemplate getResultEnumsByCode(String code) {
        for (EmailTemplate emailTemplate : EmailTemplate.values()) {
            if (emailTemplate.code.equals(code)) {
                return emailTemplate;
            }
        }
        return null;
    }
}
