package com.wenwen.base.service;

import com.wenwen.base.dao.Email;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author xiaoxinga
 * @date 2019/01/29 15:27
 * @since
 */
public class SendEmailBaseService {

    @Value("${wenwen.email.address}")
    private String emailAddress;

    @Value("${wenwen.email.password}")
    private String emailPassword;
    /**
     ** 邮件单发（自由编辑短信，并发送，适用于私信）
     **
     ** @param email 邮件实体类
     ** @throws Exception
     *            
     */
    public void sendEmail(Email email) throws Exception {
        // 设置邮件服务器
        Properties properties = new Properties();
        // 可以设置邮件服务器
        properties.setProperty("mail.host", "smtp.163.com");
        properties.setProperty("mail.smtp.auth", "true");
        // 与邮件服务器的连接
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAddress, emailPassword);
            }
        });
        // 创建邮件
        Message message = new MimeMessage(session);
        // 设置收件人地址
        message.setFrom(new InternetAddress(emailAddress,"文文网"));
        // 抄送
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getSendTo()));
        // 设置邮件的主体
        message.setSubject(email.getEmailTitle());
        //披上马甲不被当成垃圾邮件
        message.addHeader("X-MimeOLE", "Produced By Microsoft MimeOLE V6.00.2900.2869");
        message.addHeader("ReturnReceipt", "1");
        // 设置内容
        message.setContent(email.getEmailContent(), "text/html;charset=utf-8");
        // 发送邮件
        Transport.send(message);
    }
}
