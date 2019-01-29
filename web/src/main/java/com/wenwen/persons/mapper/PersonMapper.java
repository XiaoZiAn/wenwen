package com.wenwen.persons.mapper;

import org.apache.ibatis.annotations.Param;

import com.wenwen.persons.model.Person;

/**
 * @author xiaoxinga
 * @date 2018/9/10 11:14
 * @since
 */
public interface PersonMapper {

    int insert(Person person);

    String selectActivateCodeByPersonName(@Param("personName") String personName);

    Person selectByPersonName(@Param("personName") String personName);

    Person getByNameOrEmail(@Param("personName") String personName, @Param("email") String email);

    void updateStatus(@Param("newStatus") String newStatus, @Param("oldStatus") String oldStatus);

    void updatePassword(@Param("personName") String personName, @Param("newPassword") String newPassword);

    void updatePasswordCodeAndLastTime(@Param("person") Person person);
}
