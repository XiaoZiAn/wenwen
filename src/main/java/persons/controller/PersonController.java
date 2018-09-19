package persons.controller;

import System.dao.Result;
import System.service.NewBillNoService;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import persons.model.Person;
import com.alibaba.fastjson.JSONObject;
import persons.service.PersonService;

import java.util.List;
import java.util.Map;

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
    public Result addPerson(@RequestBody Map<String, String> param) throws Exception {
        Result result = new Result(Result.ResultEnums.SIGIN_ERROR);
        Person person = new Person();
        String personId = String.valueOf(newBillNoService.createNewBillNo());
        person.setPersonId(personId);
        person.setPersonName(param.get("name"));
        person.setPersonBirthday(param.get("birthday"));
        String passworded = BCrypt.hashpw(param.get("password"), BCrypt.gensalt());
        person.setPersonPassword(passworded);
        int cnt = personService.insert(person);
        if(cnt == 0){
            logger.info("personId:{}注册失败",personId);
            return result;
        }
        result.setResultEnums(Result.ResultEnums.SIGIN_SUCCESS);
        return result;
    }

    @RequestMapping(path = "/logon",method = RequestMethod.POST)
    public Result<Person> logon(@RequestBody JSONObject paramJson) throws  Exception {
        Result<Person> result = new Result<Person>(Result.ResultEnums.LOGON_ERROR);
        String personId = paramJson.getString("personId");
        String hashed = personService.getPasswordById(personId);
        if (BCrypt.checkpw(paramJson.getString("password"), hashed)){
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
