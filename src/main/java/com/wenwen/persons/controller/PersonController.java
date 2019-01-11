package com.wenwen.persons.controller;

import com.wenwen.system.dao.Result;
import com.wenwen.system.service.NewTableIdService;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wenwen.persons.model.Person;
import com.wenwen.persons.service.PersonService;

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
    PersonService personService;
    @Autowired
    NewTableIdService newTableIdService;

    @RequestMapping("/signUp")
    public String signUp() {
        return "SignUp";
    }

    @RequestMapping("/signIn")
    public String signIn() {
        return "SignIn";
    }

    @RequestMapping(path = "/addPerson", method = RequestMethod.POST)
    public String addPerson(Person person, Model model) throws Exception {
        Result result = new Result(Result.ResultEnums.SIGIN_ERROR);
        if (personService.insert(person)) {
            result.setResultEnums(Result.ResultEnums.SIGIN_SUCCESS);
            model.addAttribute("msg", "注册成功");
            return "SignIn";
        }
        log.info("personId:{}注册失败", person.getName());
        return "SignUp";
    }

    @RequestMapping(path = "/check", method = RequestMethod.POST)
    public String check(Person param, Model model) throws Exception {
        log.info(param.getName());
        Result<Person> result = new Result<Person>(Result.ResultEnums.LOGON_ERROR);
        Person person = personService.check(param);
        if (person != null) {
            return "home";
        } else
            return "index";
    }

    @RequestMapping(path = "/selectPersons", method = RequestMethod.POST)
    public List<Person> selectPersons(String json) throws Exception {
        List<Person> persons = personService.selectAll();
        System.out.println("success");
        log.info("com.wenwen.persons:{}", persons);
        return persons;
    }
}
