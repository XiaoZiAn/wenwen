package com.wenwen.system.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wenwen.persons.model.Person;

/**
 * @author xiaoxinga
 * @date 2019/01/18 11:33
 * @since
 */
@Service
public class CreateUrlService {

    @Value("${wenwen.activate.http.url}")
    private String url;

    public String getActivateUrl(Person person) {
        return url + "/person/activate?personName=" + person.getPersonName() + "&activateCode=" + person.getActivateCode();
    }
}
