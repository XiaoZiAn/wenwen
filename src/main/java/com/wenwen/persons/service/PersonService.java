package com.wenwen.persons.service;

import com.wenwen.system.service.NewTableIdService;
import org.mindrot.jbcrypt.BCrypt;
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
public class PersonService implements PersonMapper {
    @Autowired
    private PersonMapper personMapper;

    @Autowired
    NewTableIdService newTableIdService;

    public int insert(Person person) throws Exception {
        String personId = newTableIdService.getTableId("person","person_id","pe");
        person.setId(personId);
        String passworded = BCrypt.hashpw(person.getPassword(), BCrypt.gensalt());
        person.setPassword(passworded);
        return personMapper.insert(person);
    }

    public String getPasswordByName(String name) {
        return personMapper.getPasswordByName(name);
    }

    public Person selectByPersonName(String name) {
        return personMapper.selectByPersonName(name);
    }

    public List<Person> selectAll() {
        return personMapper.selectAll();
    }
}
