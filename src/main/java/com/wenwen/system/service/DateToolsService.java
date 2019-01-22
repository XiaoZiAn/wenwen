package com.wenwen.system.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author xiaoxinga
 * @date 2019/01/16 11:42
 * @since
 */
@Service
public class DateToolsService {

    final static DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final static DateTimeFormatter pattern1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String getNowDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
        return pattern.format(localDateTime);
    }

    public static String getNowTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        return pattern1.format(localDateTime);
    }

    public static String get10MinutsTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        return pattern1.format(localDateTime.plusMinutes(10));
    }

    public String nowToUnbLockTime(Date unbLockTime) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date startTime = Date.from(zdt.toInstant());
        if (unbLockTime == null || !startTime.before(unbLockTime)) {
            return null;
        }
        long start = startTime.getTime();
        long end = unbLockTime.getTime();
        long between = end - start;
        long day, hour, minute;
        // 相差天数 
        day = between / (24 * 60 * 60 * 1000);
        // 相差小时数 一共相差的毫秒数 - 天所占的毫秒数
        hour = (between - day * (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
        // 相差分钟数 一共相差的毫秒数 - 天所占的毫秒数-小时所占的毫秒数
        minute = (between - day * (24 * 60 * 60 * 1000) - hour * (60 * 60 * 1000)) / (60 * 1000);
        return "距离解封还有" + day + "天" + hour + "时" + minute + "分";
    }
}
