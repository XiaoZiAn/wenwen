package com.wenwen.system.dao;

import lombok.Data;

/**
 * @author xiaoxinga
 * @date 2019/01/18 15:57
 * @since
 */
@Data
public class Email {
    private String sendTo;// 邮件收件人地址

    private String sendDate;//发送时间

    private String emailTitle;// 邮件主题

    private String emailContent;// 邮件内容

    private String emailType;// 邮件类型

    private String status;// 发送状态(1:已发送 0：未发送 2：发送失败 3：发送成功）

    private String isBatch;//是否参与批量（1：是 0：否）
}
