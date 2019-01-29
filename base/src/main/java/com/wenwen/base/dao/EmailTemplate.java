package com.wenwen.base.dao;

import lombok.Data;

/**
 * @author xiaoxinga
 * @date 2019/01/18 10:49
 * @since
 */
@Data
public class EmailTemplate {
    private String emailTitle;//邮件主题

    private String emailType;//邮件类型

    private String emailContent;//邮件内容
}
