package com.wenwen.persons.service;

import com.wenwen.persons.PersonStatus;
import com.wenwen.system.dao.Result;
import com.wenwen.system.service.EncryptService;
import com.wenwen.system.service.NewTableIdService;
import com.wenwen.system.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenwen.persons.mapper.PersonMapper;
import com.wenwen.persons.model.Person;
import java.util.List;

/**
 * @author xiaoxinga
 * @date 2018/9/19 10:23
 * @since
 */
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

    public Result insert(Person person) {
        Person val = getByNameOrEmail(person.getPersonName(), person.getEmail());
        Result<Person> result = new Result<Person>(Result.ResultEnums.SIGIN_ERROR);
        if (val == null) {
            String personId = newTableIdService.getTableId("person", "personId", "pe");
            person.setPersonId(personId);
            String passworded = encryptService.encryptString(person.getPassword());
            person.setPassword(passworded);
            if (personMapper.insert(person) > 0) {
                result.setResultEnums(Result.ResultEnums.SIGIN_SUCCESS);
            }
        } else {
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
        } else if (PersonStatus.SEALED.code.equals(person.getStatus())) {
            result.setRsMsg("您的账号已被封！");
        } else if (encryptService.checkString(val.getPassword(), person.getPassword())) {
            result.setResultEnums(Result.ResultEnums.LOGON_SUCCESS, person);
        } else {
            result.setRsMsg("密码错误！");
        }
        return result;
    }
}
