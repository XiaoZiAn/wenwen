package com.wenwen.base.service;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.wenwen.system.dao.Lock;
import com.wenwen.system.model.LockSight;

/**
 * @author xiaoxinga
 * @date 2019/01/15 10:05
 * @since
 */
public class LockHelpService {

    private LockService lockService;

    public Object executeLock(ProceedingJoinPoint point) throws Throwable {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        LockSight annotation = method.getAnnotation(LockSight.class);
        if (annotation != null) {
            String value = (String) point.getArgs()[0];
            if (StringUtils.isBlank(value)) {
                throw new Exception("加锁标志不能为空！");
            }
            Lock lock = new Lock();
            lock.setValue(value);
            lock.setSight(annotation.sight());
            lockService.addLock(lock);
            boolean isSuccess = true;
            try {
                return point.proceed();
            } catch (Exception e) {
                isSuccess = false;
                throw e;
            } finally {
                if (isSuccess) {
                    lockService.deleteLoanLock(lock);
                }
            }
        } else {
            return point.proceed();
        }
    }

    public void setLockService(LockService lockService) {
        this.lockService = lockService;
    }

}
