package com.wenwen.base.enums;

/**
 * @author xiaoxinga
 * @date 2019/01/18 16:24
 * @since
 */
public enum EmailStatus {
    wait_send("未发送", "0"),
    sended("已发送", "1"),
    send_faild("发送失败", "2"),
    send_success("发送成功", "3");
    public final String name;
    public final String code;

    EmailStatus(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static EmailStatus getResultEnumsByCode(String code) {
        for (EmailStatus emailStatus : EmailStatus.values()) {
            if (emailStatus.code.equals(code)) {
                return emailStatus;
            }
        }
        return null;
    }
}
