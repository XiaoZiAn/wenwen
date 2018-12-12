package com.wenwen.persons.controller;

import com.wenwen.System.dao.Result;
import com.wenwen.System.service.NewBillNoService;
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
    NewBillNoService newBillNoService;

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
        if (personService.insert(person) > 0) {
            result.setResultEnums(Result.ResultEnums.SIGIN_SUCCESS);
            model.addAttribute("msg", "注册成功");
            return "SignIn";
        }
        log.info("personId:{}注册失败", person.getName());
        return "SignUp";
    }

    @RequestMapping(path = "/check", method = RequestMethod.POST)
    public String check(Person person, Model model) throws Exception {
        log.info(person.getName());
        Result<Person> result = new Result<Person>(Result.ResultEnums.LOGON_ERROR);
        String hashed = personService.getPasswordByName(person.getName());
        if (BCrypt.checkpw(person.getPassword(), hashed)) {
            Person person1 = personService.selectByPersonName(person.getName());
            result.setResultEnums(Result.ResultEnums.LOGON_SUCCESS, person);
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
