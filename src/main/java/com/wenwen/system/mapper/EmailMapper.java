package com.wenwen.system.mapper;

import com.wenwen.system.dao.Email;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaoxinga
 * @date 2019/01/18 15:43
 * @since
 */
public interface EmailMapper {
    int insert(@Param("email") Email email);

    void updateStatus(@Param("sendTo") String sendTo, @Param("emailType") String emailType, @Param("newStatus") String newStatus, @Param("oldStatus") String oldStatus);
}
