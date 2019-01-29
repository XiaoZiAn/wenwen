package com.wenwen.persons.service;

import java.util.UUID;

import com.wenwen.base.service.*;
import com.wenwen.system.service.SendEmailService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenwen.persons.enums.PersonStatus;
import com.wenwen.persons.mapper.PersonMapper;
import com.wenwen.persons.model.Person;
import com.wenwen.base.dao.Result;

import lombok.extern.slf4j.Slf4j;

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
    private TableIdService tableIdService;

    @Autowired
    private EncryptService encryptService;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private DateToolsService dateToolsService;

    public Result insert(Person person) {
        Person val = getByNameOrEmail(person.getPersonName(), person.getEmail());
        Result<Person> result = new Result<Person>(Result.ResultEnums.SIGIN_ERROR);
        if (val == null) {
            String personId = tableIdService.getTableId("person", "personId", "pe");
            person.setPersonId(personId);
            String passworded = encryptService.encryptString(person.getPassword());
            person.setPassword(passworded);
            String activateCode = UUID.randomUUID().toString();
            person.setActivateCode(activateCode);
            if (personMapper.insert(person) > 0) {
                result.setResultEnums(Result.ResultEnums.SIGIN_SUCCESS);
                result.setRsMsg("注册成功，请查看邮箱激活账号！(若十天内未激活账户，需重新注册！)");
                try {
                    sendEmailService.sendActivateEmail(person);
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

    public Person getByNameOrEmail(String name, String email) {
        return personMapper.getByNameOrEmail(name, email);
    }

    public Person selectByPersonName(String name) {
        return personMapper.selectByPersonName(name);
    }

    public Result<Person> check(Person val) {
        val.setEmail(val.getPersonName());// 用户可能用邮箱登录
        Result<Person> result = new Result<Person>(Result.ResultEnums.LOGON_ERROR);
        Person person = getByNameOrEmail(val.getPersonName(), val.getEmail());
        if (person == null) {
            result.setRsMsg("用户不存在！");
        } else if (PersonStatus.WAIT_ACTIVATED.code.equals(person.getStatus())) {
            result.setResultEnums(Result.ResultEnums.WAIT_ACTIVATED);
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

    public void sendChangePasswordEmail(String val) {
        Person person = getByNameOrEmail(val, val);
        person.setPasswordCode(CodeTools.newCode());
        person.setPasswordCodeLastTime(DateToolsService.get10MinutsTime());
        updatePasswordCodeAndLastTime(person);
        try {
            sendEmailService.sendChangePasswordEmail(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Result<Person> checkPasswordCode(String name, String passwordCode) {
        Result result = new Result();
        Person person = getByNameOrEmail(name, name);
        String nowTime = DateToolsService.getNowTime();
        if (StringUtils.isNotBlank(passwordCode) && person.getPasswordCode().equals(passwordCode)) {
            if (person.getPasswordCodeLastTime().compareTo(nowTime) > 0) {
                result.setResultEnums(Result.ResultEnums.SUCCESS);
                result.setData(person);
            } else {
                result.setResultEnums(Result.ResultEnums.CODE_LOSE);
            }
        } else {
            result.setResultEnums(Result.ResultEnums.CODE_ERROR);
        }
        return result;
    }

    public Result changePassword(Person val) {
        Result result = new Result();
        Person person = getByNameOrEmail(val.getPersonName(), val.getPersonName());
        String nowTime = DateToolsService.getNowTime();
        if (StringUtils.isNotBlank(person.getPasswordCodeLastTime()) && StringUtils.isNotBlank(val.getPasswordCode()) && val.getPasswordCode().equals(person.getPasswordCode())
                && (person.getPasswordCodeLastTime().compareTo(nowTime) > 0)) {
            updatePassword(person.getPersonName(), encryptService.encryptString(val.getPassword()));
            result.setResultEnums(Result.ResultEnums.SUCCESS);
        } else {
            result.setRsMsg("系统错误");
            result.setResultEnums(Result.ResultEnums.Erroe);
        }
        return result;
    }

    private void updatePassword(String name, String newpassword) {
        personMapper.updatePassword(name, newpassword);
    }

    private void updatePasswordCodeAndLastTime(Person person) {
        personMapper.updatePasswordCodeAndLastTime(person);
    }
}
