package com.wenwen.persons.service;

import com.wenwen.system.dao.Result;
import com.wenwen.system.service.EncryptService;
import com.wenwen.system.service.NewTableIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenwen.persons.mapper.PersonMapper;
import com.wenwen.persons.model.Person;
import java.util.List;

/**
 * @author xiaoxinga
 * @date 2018/9/19 10:23
 * @since
 */
@Service
public class PersonService {
    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private NewTableIdService newTableIdService;

    @Autowired
    private EncryptService encryptService;

    public Result insert(Person person) {
        Person val = getByNameOrEmail(person.getPersonName(), person.getEmail());
        Result<Person> result = new Result<Person>(Result.ResultEnums.SIGIN_ERROR);
        if (val == null) {
            String personId = newTableIdService.getTableId("person", "id", "pe");
            person.setPersonId(personId);
            String passworded = encryptService.encryptString(person.getPassword());
            person.setPassword(passworded);
            if (personMapper.insert(person) > 0) {
                result.setResultEnums(Result.ResultEnums.SIGIN_SUCCESS);
            }
        }
        return result;
    }

    public Person getByNameOrEmail(String name, String email) {
        return personMapper.getByNameOrEmail(name, email);
    }

    public Person selectByPersonName(String name) {
        return personMapper.selectByPersonName(name);
    }

    public List<Person> selectAll() {
        return personMapper.selectAll();
    }

    public Person check(Person val) {
        Person person = getByNameOrEmail(val.getPersonName(), val.getEmail());
        if (encryptService.checkString(val.getPassword(), person.getPassword())) {
            return person;
        }
        return null;
    }
}
