package com.wenwen.persons;

/**
 * @author xiaoxinga
 * @date 2019/01/16 9:46
 * @since
 */
public enum PersonStatus {
    ACTIVATED("已激活", "1"),
    WAIT_ACTIVATED("待激活", "0"),
    SEALED("账号被封", "2");
    public final String name;
    public final String code;

    PersonStatus(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static PersonStatus gePersonStatusByCode(String code) {
        for (PersonStatus personStatus : PersonStatus.values()) {
            if (personStatus.code.equals(code)) {
                return personStatus;
            }
        }
        return null;
    }
}
