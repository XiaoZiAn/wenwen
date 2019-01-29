package com.wenwen.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenwen.base.dao.EmailTemplate;
import com.wenwen.base.mapper.EmailTemplateMapper;

/**
 * @author xiaoxinga
 * @date 2019/01/18 10:46
 * @since
 */
@Service
public class EmailTemplateService {

    @Autowired
    private EmailTemplateMapper emailTemplateMapper;

    public EmailTemplate getEmailTemplate(String emailType){
        return emailTemplateMapper.getEmailTemplate(emailType);
    }
}
