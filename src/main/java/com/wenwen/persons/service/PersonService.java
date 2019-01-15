package com.wenwen.persons.service;

import com.wenwen.system.dao.Result;
import com.wenwen.system.service.EncryptService;
import com.wenwen.system.service.NewTableIdService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenwen.persons.mapper.PersonMapper;
import com.wenwen.persons.model.Person;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    public Result insert(Person val) {
        Person person = getByNameOrEmail(val.getName(), val.getEmail());
        Result<Person> result = new Result<Person>(Result.ResultEnums.SIGIN_ERROR);
        if (person == null) {
            String personId = newTableIdService.getTableId("person", "id", "pe");
            val.setId(personId);
            String passworded = encryptService.encryptString(val.getPassword());
            val.setPassword(passworded);
            if (personMapper.insert(val) > 0) {
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
        Person person = getByNameOrEmail(val.getName(), val.getEmail());
        if (encryptService.checkString(val.getPassword(), person.getPassword())) {
            return person;
        }
        return null;
    }
}
