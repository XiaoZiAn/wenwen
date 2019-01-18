package com.wenwen.system.service;

import com.wenwen.system.dao.EmailTemplate;
import com.wenwen.system.mapper.EmailTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaoxinga
 * @date 2019/01/18 10:46
 * @since
 */
@Service
public class EmailTemplateService {

    @Autowired
    private EmailTemplateMapper emailTemplateMapper;

    public EmailTemplate getEmailTemplate(String emailTheme){
        return emailTemplateMapper.getEmailTemplate(emailTheme);
    }
}
