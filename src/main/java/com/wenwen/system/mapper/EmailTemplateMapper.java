package com.wenwen.system.mapper;

import com.wenwen.system.dao.EmailTemplate;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaoxinga
 * @date 2019/01/18 10:51
 * @since
 */
public interface EmailTemplateMapper {
    EmailTemplate getEmailTemplate(@Param("emailTheme") String emailTheme);

}
