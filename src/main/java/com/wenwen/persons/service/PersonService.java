package com.wenwen.persons.service;

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
public class PersonService implements PersonMapper{
    @Autowired
    private PersonMapper personMapper;

    public int insert(Person person){
        int cnt = personMapper.insert(person);
        return cnt;
    }

    public String getPasswordByName (String personName){
        String password = personMapper.getPasswordByName(personName);
        return password;
    }

    public Person selectByPersonName (String personName){
        Person person = personMapper.selectByPersonName(personName);
        return person;
    }

    public List<Person> selectAll(){
        List<Person> persons = personMapper.selectAll();
        return persons;
    }
}
