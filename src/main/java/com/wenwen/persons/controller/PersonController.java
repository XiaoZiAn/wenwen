package com.wenwen.persons.controller;

import com.wenwen.system.dao.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wenwen.persons.model.Person;
import com.wenwen.persons.service.PersonService;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

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

    @RequestMapping("/signUp")
    public String signUp() {
        return "SignUp";
    }

    @RequestMapping("/signIn")
    public String signIn() {
        return "SignIn";
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

    @RequestMapping(path = "/selectPersons", method = RequestMethod.POST)
    public List<Person> selectPersons(String json) throws Exception {
        List<Person> persons = personService.selectAll();
        System.out.println("success");
        log.info("com.wenwen.persons:{}", persons);
        return persons;
    }

    @RequestMapping(path = "/activate", method = RequestMethod.GET)
    public void activate(String personName, String activateCode) {
        log.info(personName + "激活账号");
        personService.activate(personName,activateCode);
    }
}
