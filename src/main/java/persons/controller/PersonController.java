package persons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import persons.dao.PersonDao;
import persons.mapper.PersonMapper;

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

    @RequestMapping(path = "/addPerson", method = RequestMethod.POST)
    public void addPerson(@RequestBody Map<String, String> param) throws Exception {

    }
}
