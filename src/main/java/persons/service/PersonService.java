package persons.service;

import org.springframework.beans.factory.annotation.Autowired;
import persons.mapper.PersonMapper;
import persons.model.Person;

import java.util.List;

/**
 * @author xiaoxinga
 * @date 2018/9/19 10:23
 * @since
 */
public class PersonService {
    @Autowired
    PersonMapper personMapper;

    public int insert(Person person){
        int cnt = personMapper.insert(person);
        return cnt;
    }

    public String getPasswordById (String personId){
        String password = personMapper.getPasswordById(personId);
        return password;
    }

    public Person selectByPersonId (String personId){
        Person person = personMapper.selectByPersonId(personId);
        return person;
    }

    public List<Person> selectAll(){
        List<Person> persons = personMapper.selectAll();
        return persons;
    }
}
