package com.wenwen.persons.mapper;

import com.wenwen.persons.model.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoxinga
 * @date 2018/9/10 11:14
 * @since
 */
public interface PersonMapper {

    int insert(Person person);

    Person selectByPersonName(@Param("personName")String personName);

    List<Person> selectAll();

    Person getByNameOrEmail(@Param("personName") String personName,@Param("email")String email);
}
