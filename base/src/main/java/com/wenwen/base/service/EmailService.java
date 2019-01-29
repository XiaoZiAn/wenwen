package com.wenwen.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenwen.base.dao.Email;
import com.wenwen.base.mapper.EmailMapper;

/**
 * @author xiaoxinga
 * @date 2019/01/18 15:41
 * @since
 */
@Service
public class EmailService {

    @Autowired
    private EmailMapper emailMapper;

    public void insertEmail(Email email) {
        emailMapper.insert(email);
    }

    public void updateStatus(String sendTo, String emailType, String newStatus, String oldSatus) {
        emailMapper.updateStatus(sendTo, emailType, newStatus, oldSatus);
    }
}
