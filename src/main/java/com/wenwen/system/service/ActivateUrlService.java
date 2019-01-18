package com.wenwen.system.service;

import com.wenwen.persons.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author xiaoxinga
 * @date 2019/01/18 11:33
 * @since
 */
@Service
public class ActivateUrlService {

    @Value("${wenwen.activate.http.url}")
    private String activateUrl;

    public String getActivateUrl(Person person) {
        return activateUrl + "?personName=" + person.getPersonName() + "&activateCode=" + person.getActivateCode();
    }
}
