package com.wenwen.base.mapper;

import org.apache.ibatis.annotations.Param;

import com.wenwen.base.dao.EmailTemplate;

/**
 * @author xiaoxinga
 * @date 2019/01/18 10:51
 * @since
 */
public interface EmailTemplateMapper {
    EmailTemplate getEmailTemplate(@Param("emailType") String emailType);

}
