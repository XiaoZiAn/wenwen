package com.wenwen.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wenwen.base.dao.Lock;
import com.wenwen.base.mapper.LockMapper;
import com.wenwen.base.model.EorrorException;

/**
 * @author xiaoxinga
 * @date 2019/01/15 10:07
 * @since
 */
@Component
public class LockService {
    
    @Autowired
    LockMapper lockMapper;
    
    public boolean addLock(Lock lock) throws EorrorException {
        Lock lock1 = lockMapper.getLock(lock.getValue(), lock.getSight());
        if (lock1 != null) {
            return lockMapper.addLock(lock.getValue(), lock.getSight()) > 0;
        } else {
            throw new EorrorException("00001","加锁失败，锁已经存在");
        }
    }

    public void deleteLoanLock(Lock lock) {
        lockMapper.deleteLoanLock(lock.getValue(),lock.getSight());
    }
}
