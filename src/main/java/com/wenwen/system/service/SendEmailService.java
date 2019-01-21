package com.wenwen.system.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.wenwen.persons.model.Person;
import com.wenwen.system.dao.Email;
import com.wenwen.system.dao.EmailTemplate;
import com.wenwen.system.enums.EmailStatus;
import com.wenwen.system.enums.EmailType;
import com.wenwen.system.model.EorrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Properties;

/**
 * @author xiaoxinga
 * @date 2018/12/13 17:23
 * @since
 */
@Service
@Slf4j
public class SendEmailService {

    @Value("${wenwen.email.address}")
    private String emailAddress;

    @Value("${wenwen.email.password}")
    private String emailPassword;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Autowired
    private CreateUrlService createUrlService;

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
        message.setFrom(new InternetAddress(emailAddress));
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

    public void sendActivateEmail(Person person) throws Exception {
        EmailTemplate emailTemplate = emailTemplateService.getEmailTemplate(EmailType.ACTIVATE_EMAIL.code);
        String content = emailTemplate.getEmailContent().replace("[&url&]", createUrlService.getActivateUrl(person));
        Email email = new Email();
        email.setSendDate(DateToolsService.getNowDate());
        email.setSendTo(person.getEmail());
        email.setEmailType(emailTemplate.getEmailType());
        email.setEmailTitle(emailTemplate.getEmailTitle());
        email.setEmailContent(content);
        email.setIsBatch("0");
        emailService.insertEmail(email);
        try {
            sendEmail(email);
        } catch (Exception e) {
            emailService.updateStatus(email.getSendTo(), email.getEmailType(), EmailStatus.send_faild.code, EmailStatus.wait_send.code);
            log.info(person.getEmail() + "的账户激活邮件发送失败");
            e.printStackTrace();
            throw new EorrorException(person.getEmail() + "账户激活邮件发送失败");
        }
        emailService.updateStatus(email.getSendTo(), email.getEmailType(), EmailStatus.send_success.code, EmailStatus.wait_send.code);
    }

    public void sendChangePasswordEmail(Person person) throws Exception{
        EmailTemplate emailTemplate = emailTemplateService.getEmailTemplate(EmailType.CHANGEPASSWORD_EMAIL.code);
        String content = emailTemplate.getEmailContent().replace("[&url&]", createUrlService.getChangePasswordUrl(person));
        Email email = new Email();
        email.setSendDate(DateToolsService.getNowDate());
        email.setSendTo(person.getEmail());
        email.setEmailType(emailTemplate.getEmailType());
        email.setEmailTitle(emailTemplate.getEmailTitle());
        email.setEmailContent(content);
        email.setIsBatch("0");
        emailService.insertEmail(email);
        try {
            sendEmail(email);
        } catch (Exception e) {
            emailService.updateStatus(email.getSendTo(), email.getEmailType(), EmailStatus.send_faild.code, EmailStatus.wait_send.code);
            log.info(person.getEmail() + "的更改账号密码邮件发送失败");
            e.printStackTrace();
            throw new EorrorException(person.getEmail() + "更改账号密码邮件发送失败");
        }
        emailService.updateStatus(email.getSendTo(), email.getEmailType(), EmailStatus.send_success.code, EmailStatus.wait_send.code);
    }}
