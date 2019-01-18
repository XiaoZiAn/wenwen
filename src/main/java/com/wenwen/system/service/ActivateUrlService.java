package com.wenwen.system.service;

import com.wenwen.persons.model.Person;
import org.springframework.stereotype.Service;

/**
 * @author xiaoxinga
 * @date 2019/01/18 11:33
 * @since
 */
@Service
public class ActivateUrlService {

    private final static String url = "http://localhost:8020/person/activate/";

    public String getActivateUrl(Person person) {
        return url + "personName=" + person.getPersonName() + "activateCode=" + person.getActivateCode();
    }
}
