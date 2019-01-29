package com.wenwen.base.dao;

import lombok.Data;

/**
 * @author xiaoxinga
 * @date 2019/01/15 10:22
 * @since
 */
@Data
public class Lock {
    String value;//加锁标识
    String sight;//加锁场景
}
