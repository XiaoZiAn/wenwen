package com.wenwen.persons.service;

import com.wenwen.persons.PersonStatus;
import com.wenwen.system.dao.Email;
import com.wenwen.system.dao.EmailTemplate;
import com.wenwen.system.dao.Result;
import com.wenwen.system.enums.EmailStatus;
import com.wenwen.system.enums.EmailType;
import com.wenwen.system.model.EorrorException;
import com.wenwen.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenwen.persons.mapper.PersonMapper;
import com.wenwen.persons.model.Person;
import java.util.List;
import java.util.UUID;

/**
 * @author xiaoxinga
 * @date 2018/9/19 10:23
 * @since
 */
@Slf4j
@Service
public class PersonService {
    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private NewTableIdService newTableIdService;

    @Autowired
    private EncryptService encryptService;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Autowired
    private ActivateUrlService activateUrlService;

    @Autowired
    private DateToolsService dateToolsService;

    @Autowired
    private EmailService emailService;

    public Result insert(Person person) {
        Person val = getByNameOrEmail(person.getPersonName(), person.getEmail());
        Result<Person> result = new Result<Person>(Result.ResultEnums.SIGIN_ERROR);
        if (val == null) {
            String personId = newTableIdService.getTableId("person", "personId", "pe");
            person.setPersonId(personId);
            String passworded = encryptService.encryptString(person.getPassword());
            person.setPassword(passworded);
            String activateCode = UUID.randomUUID().toString();
            person.setActivateCode(activateCode);
            if (personMapper.insert(person) > 0) {
                result.setResultEnums(Result.ResultEnums.SIGIN_SUCCESS);
                result.setRsMsg("注册成功，请查看邮箱激活账号！");
                try {
                    sendActivateEmail(person);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            result.setResultEnums(Result.ResultEnums.SIGINED);
            result.setRsMsg("用户名或邮箱已注册");
        }
        return result;
    }

    public void sendActivateEmail(Person person) throws Exception {
        EmailTemplate emailTemplate = emailTemplateService.getEmailTemplate(EmailType.ACTIVATE_EMAIL.code);
        String content = emailTemplate.getEmailContent().replace("[&url&]", activateUrlService.getActivateUrl(person));
        Email email = new Email();
        email.setSendTo(person.getEmail());
        email.setEmailType(emailTemplate.getEmailType());
        email.setEmailTitle(emailTemplate.getEmailTitle());
        email.setEmailContent(content);
        emailService.insertEmail(email);
        try {
            sendEmailService.sendEmail(email);
        } catch (Exception e) {
            emailService.updateStatus(email.getSendTo(),email.getEmailType(),EmailStatus.send_faild.code,EmailStatus.wait_send.code);
            log.info(person.getEmail() + "的账户激活邮件发送失败");
            e.printStackTrace();
            throw new EorrorException(person.getEmail() + "账户激活邮件发送失败");
        }
        emailService.updateStatus(email.getSendTo(), email.getEmailType(), EmailStatus.send_success.code, EmailStatus.wait_send.code);
    }

    public Person getByNameOrEmail(String name, String email) {
        return personMapper.getByNameOrEmail(name, email);
    }

    public Person selectByPersonName(String name) {
        return personMapper.selectByPersonName(name);
    }

    public List<Person> selectAll() {
        return personMapper.selectAll();
    }

    public Result<Person> check(Person val) {
        val.setEmail(val.getPersonName());// 用户可能用邮箱登录
        Result<Person> result = new Result<Person>(Result.ResultEnums.LOGON_ERROR);
        Person person = getByNameOrEmail(val.getPersonName(), val.getEmail());
        if (person == null) {
            result.setRsMsg("用户不存在！");
        } else if (PersonStatus.WAIT_ACTIVATED.code.equals(person.getStatus())) {
            result.setRsMsg("您的账号未激活！");
        } else if (StringUtils.isNotBlank(dateToolsService.nowToUnbLockTime(person.getUnbLockTime()))) {
            result.setResultEnums(Result.ResultEnums.SEALED);
            result.setRsMsg("您的账号已被封！");
        } else {
            if (PersonStatus.SEALED.code.equals(person.getStatus())) {
                personMapper.updateStatus(PersonStatus.ACTIVATED.code, PersonStatus.SEALED.code);
            }
            if (encryptService.checkString(val.getPassword(), person.getPassword())) {
                result.setResultEnums(Result.ResultEnums.LOGON_SUCCESS, person);
            } else {
                result.setRsMsg("密码错误！");
            }
        }
        return result;
    }

    public void activate(String val1, String val2) {
        String activateCode = personMapper.selectActivateCodeByPersonName(val1);
        if (activateCode.equals(val2)) {
            personMapper.updateStatus(PersonStatus.ACTIVATED.code, PersonStatus.WAIT_ACTIVATED.code);
        } else {
            log.info(val1 + "账号激活失败！");
        }
    }
}
