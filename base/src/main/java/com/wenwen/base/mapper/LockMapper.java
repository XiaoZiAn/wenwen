package com.wenwen.base.mapper;

import org.apache.ibatis.annotations.Param;

import com.wenwen.base.dao.Lock;

/**
 * @author xiaoxinga
 * @date 2019/01/15 10:07
 * @since
 */
public interface LockMapper {

    Lock getLock(@Param("name") String name, @Param("sight") String sight);

    int addLock(@Param("name") String name, @Param("sight") String sight);

    void deleteLoanLock(@Param("name") String name, @Param("sight") String sight);
}
