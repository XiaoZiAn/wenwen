package com.wenwen.base.mapper;

import org.apache.ibatis.annotations.Param;

import com.wenwen.base.dao.Email;

/**
 * @author xiaoxinga
 * @date 2019/01/18 15:43
 * @since
 */
public interface EmailMapper {
    void insert(@Param("email") Email email);

    void updateStatus(@Param("sendTo") String sendTo, @Param("emailType") String emailType, @Param("newStatus") String newStatus, @Param("oldStatus") String oldStatus);
}
