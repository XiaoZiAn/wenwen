package persons.controller;

import System.dao.Result;
import System.service.NewBillNoService;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import persons.model.Person;
import persons.service.PersonService;
import java.util.List;

/**
 * @author xiaoxinga
 * @date 2018/9/10 11:53
 * @since
 */
@RestController
@RequestMapping(path = "/person")
public class PersonController {
    @Autowired
    PersonService personService;
    @Autowired
    NewBillNoService newBillNoService;
    @Autowired
    BCrypt BCrypt;

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);
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

    @RequestMapping(path = "/logon",method = RequestMethod.POST)
    public Result<Person> logon(String personId,String password) throws  Exception {
        Result<Person> result = new Result<Person>(Result.ResultEnums.LOGON_ERROR);
        String hashed = personService.getPasswordById(personId);
        if (BCrypt.checkpw(password, hashed)){
            Person person = personService.selectByPersonId(personId);
            result.setResultEnums(Result.ResultEnums.LOGON_SUCCESS, person);
            return result;
        }
        else
            return result;
    }

    @RequestMapping(path = "/selectPersons", method = RequestMethod.POST)
    public List<Person> selectPersons(@RequestBody String json) throws Exception {
        List<Person> persons = personService.selectAll();
        System.out.println("success");
        logger.info("persons:{}",persons);
        return persons;
    }
}
