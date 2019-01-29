package com.wenwen.system.service;

import com.wenwen.base.service.DateToolsService;
import com.wenwen.base.service.EmailService;
import com.wenwen.base.service.EmailTemplateService;
import com.wenwen.base.service.SendEmailBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenwen.persons.model.Person;
import com.wenwen.base.dao.Email;
import com.wenwen.base.dao.EmailTemplate;
import com.wenwen.base.enums.EmailStatus;
import com.wenwen.base.enums.EmailType;
import com.wenwen.base.model.EorrorException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoxinga
 * @date 2018/12/13 17:23
 * @since
 */
@Service
@Slf4j
public class SendEmailService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Autowired
    private CreateUrlService createUrlService;

    @Autowired
    private SendEmailBaseService sendEmailBaseService;
    
    public void sendEmailBefor(Email email, Person person) throws EorrorException {
        try {
            sendEmailBaseService.sendEmail(email);
        } catch (Exception e) {
            emailService.updateStatus(email.getSendTo(), email.getEmailType(), EmailStatus.send_faild.code, EmailStatus.wait_send.code);
            log.info(person.getEmail() + " " + email.getEmailTitle() + "发送失败");
            e.printStackTrace();
            throw new EorrorException(person.getEmail() + " " + email.getEmailTitle() + "发送失败");
        }
        emailService.updateStatus(email.getSendTo(), email.getEmailType(), EmailStatus.send_success.code, EmailStatus.wait_send.code);
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
        sendEmailBefor(email,person);
    }

    public void sendChangePasswordEmail(Person person) throws Exception {
        EmailTemplate emailTemplate = emailTemplateService.getEmailTemplate(EmailType.CHANGEPASSWORD_EMAIL.code);
        String content = emailTemplate.getEmailContent().replace("[&code&]", person.getPasswordCode());
        Email email = new Email();
        email.setSendDate(DateToolsService.getNowDate());
        email.setSendTo(person.getEmail());
        email.setEmailType(emailTemplate.getEmailType());
        email.setEmailTitle(emailTemplate.getEmailTitle());
        email.setEmailContent(content);
        email.setIsBatch("0");
        emailService.insertEmail(email);
        sendEmailBefor(email,person);
    }
}
