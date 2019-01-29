package com.wenwen.base.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * @author xiaoxinga
 * @date 2019/01/11 10:38
 * @since
 */
@Service
public class EncryptService {

    /**
     * 数据加密
     * @param val 加密字段
     * @return
     */
    public String encryptString(String val){
        return BCrypt.hashpw(val, BCrypt.gensalt());
    }

    /**
     * 校验加密字段
     * @param val 未加密字段
     * @param vel 加密后字段
     * @return
     */
    public Boolean checkString(String val, String vel){
        return BCrypt.checkpw(val, vel);
    }
}
