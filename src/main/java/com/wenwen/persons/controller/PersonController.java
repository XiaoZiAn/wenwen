package com.wenwen.persons.controller;

import com.wenwen.System.dao.Result;
import com.wenwen.System.service.NewBillNoService;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wenwen.persons.model.Person;
import com.wenwen.persons.service.PersonService;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * @author xiaoxinga
 * @date 2018/9/10 11:53
 * @since
 */
@Controller
@SessionAttributes("person")
@RequestMapping(path = "/person")
public class PersonController {
    @Autowired
    PersonService personService;
    @Autowired
    NewBillNoService newBillNoService;

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/logon")
    public String logon(){
        return "logon";
    }

    @RequestMapping(path = "/addPerson", method = RequestMethod.POST)
    public String addPerson(Person person, Model model) throws Exception {
        Result result = new Result(Result.ResultEnums.SIGIN_ERROR);
        String personId = String.valueOf(newBillNoService.createNewBillNo());
        person.setPersonId(personId);
        String passworded = BCrypt.hashpw(person.getPersonPassword(), BCrypt.gensalt());
        person.setPersonPassword(passworded);
        int cnt = personService.insert(person);
        if(cnt == 0){
            logger.info("personId:{}注册失败",personId);
            return "index";
        }
        result.setResultEnums(Result.ResultEnums.SIGIN_SUCCESS);
        return "error";
    }

    @RequestMapping(path = "/check",method = RequestMethod.POST)
    public String check(Person person, Model model) throws  Exception {
        logger.info(person.getPersonName());
        Result<Person> result = new Result<Person>(Result.ResultEnums.LOGON_ERROR);
        String hashed = personService.getPasswordByName(person.getPersonName());
        if (BCrypt.checkpw(person.getPersonPassword(), hashed)){
            Person person1 = personService.selectByPersonName(person.getPersonName());
            result.setResultEnums(Result.ResultEnums.LOGON_SUCCESS, person);
            return "index";
        }
        else
            return "error";
    }

    @RequestMapping(path = "/selectPersons", method = RequestMethod.POST)
    public List<Person> selectPersons(String json) throws Exception {
        List<Person> persons = personService.selectAll();
        System.out.println("success");
        logger.info("com.wenwen.persons:{}",persons);
        return persons;
    }
}
