package persons.controller;

import System.service.NewBillNoService;
import net.sf.json.JSON;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import persons.dao.PersonDao;
import persons.mapper.PersonMapper;
import com.alibaba.fastjson.JSONObject;
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
    PersonMapper personMapper;
    @Autowired
    NewBillNoService newBillNoService;
    @Autowired
    BCrypt BCrypt;

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);
    @RequestMapping(path = "/addPerson", method = RequestMethod.POST)
    public void addPerson(@RequestBody Map<String, String> param) throws Exception {
        PersonDao person = new PersonDao();
        String personId = String.valueOf(newBillNoService.createNewBillNo());
        person.setPersonId(personId);
        person.setPersonName(param.get("name"));
        person.setPersonAge(param.get("age"));
        person.setPersonBirthday(param.get("birthday"));
        String passworded = BCrypt.hashpw(param.get("password"), BCrypt.gensalt());
        person.setPersonPassword(passworded);
        int cnt = personMapper.insert(person);
        if(cnt == 0){
            logger.info("personId:{}注册失败",personId);
        }
    }

    @RequestMapping(path = "/logon",method = RequestMethod.POST)
    public void logon(@RequestBody JSONObject paramJson) throws  Exception {
        String hashed = null;
        if (BCrypt.checkpw(paramJson.getString("password"), hashed))
            System.out.println("It matches");
        else
            System.out.println("It does not match");
    }

    @RequestMapping(path = "/selectPersons", method = RequestMethod.POST)
    public List<PersonDao> selectPersons(@RequestBody String json) throws Exception {
        List<PersonDao> persons = personMapper.selectAll();
        logger.info("persons:{}",persons);
        return persons;
    }
}
