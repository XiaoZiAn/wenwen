package com.wenwen.base.model;

import java.lang.annotation.*;

/**
 * @author xiaoxinga
 * @date 2019/01/15 10:47
 * @since
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockSight {
    String sight();
}
