package com.wenwen.persons.controller;

import com.wenwen.system.dao.Result;
import com.wenwen.system.service.NewTableIdService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @ResponseBody
    @RequestMapping(path = "/addPerson", method = RequestMethod.POST)
    public Result addPerson(@RequestBody Person person) {
        Result result = personService.insert(person);
        if ("0001".equals(result.getRsCode())) {
            result.setRsMsg("用户名或邮箱已注册");
        }
        log.info("person name:{}注册失败", person.getName());
        return result;
    }

    @ResponseBody
    @RequestMapping(path = "/check", method = RequestMethod.POST)
    public Result<Person> check(@RequestBody Person param) {
        log.info(param.getName());
        Result<Person> result = new Result<Person>(Result.ResultEnums.LOGON_ERROR);
        Person person = personService.check(param);
        if (person != null) {
            result.setResultEnums(Result.ResultEnums.LOGON_SUCCESS);
        }
        return result;
    }

    @RequestMapping(path = "/selectPersons", method = RequestMethod.POST)
    public List<Person> selectPersons(String json) throws Exception {
        List<Person> persons = personService.selectAll();
        System.out.println("success");
        log.info("com.wenwen.persons:{}", persons);
        return persons;
    }
}
