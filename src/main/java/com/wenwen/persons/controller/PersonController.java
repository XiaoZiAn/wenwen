package com.wenwen.persons.controller;

import com.wenwen.system.dao.Result;
import com.wenwen.system.service.SendEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wenwen.persons.model.Person;
import com.wenwen.persons.service.PersonService;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiaoxinga
 * @date 2018/9/10 11:53
 * @since
 */
@Slf4j
@Controller
@RequestMapping(path = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private SendEmailService sendEmailService;

    @RequestMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @RequestMapping("/signin")
    public String signIn() {
        return "signin";
    }

    @ResponseBody
    @RequestMapping(path = "/addPerson", method = RequestMethod.POST)
    public Result addPerson(@RequestBody Person person) {
        Result result = personService.insert(person);
        if(!Result.ResultEnums.SIGIN_SUCCESS.rsCode.equals(result.getRsCode())){
            log.info("person name:{}注册失败", person.getPersonName());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(path = "/check", method = RequestMethod.POST)
    public Result<Person> check(@RequestBody Person param) {
        log.info(param.getPersonName());
        Result<Person> result = personService.check(param);
        return result;
    }

    @RequestMapping(path = "/activate", method = RequestMethod.GET)
    public void activate(String personName, String activateCode) {
        log.info(personName + "激活账号");
        personService.activate(personName,activateCode);
    }

    @ResponseBody
    @RequestMapping(path = "/sendActivateEmail", method = RequestMethod.POST)
    public Result sendActivateEmail(@RequestBody Person param) {
        Person person = personService.getByNameOrEmail(param.getPersonName(), param.getPersonName());
        Result result = new Result(Result.ResultEnums.SUCCESS);
        try {
            sendEmailService.sendActivateEmail(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setRsMsg("请查看邮箱激活账户！");
        return result;
    }

    @ResponseBody
    @RequestMapping(path = "/sendChangePassword", method = RequestMethod.POST)
    public Result sendChangePasswordEmail(@RequestBody String param) {
        Result result = new Result(Result.ResultEnums.SUCCESS);
        personService.sendChangePasswordEmail(param);
        result.setRsMsg("请查看邮箱更改账号密码！");
        return result;
    }

    @RequestMapping(path = "/changePassword", method = RequestMethod.GET)
    public void changePassword(String personName, String activateCode) {
        log.info(personName + "激活账号");
        personService.activate(personName,activateCode);
    }
}
