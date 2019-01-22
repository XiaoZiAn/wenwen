package com.wenwen.system.service;

import java.util.Random;

/**
 * @author xiaoxinga
 * @date 2019/01/22 9:31
 * @since
 */
public class CodeTools {

    public static String newCode() {
        String s = "abcdefghijklmnopqrstuvwxyz";
        s += s + s.toUpperCase();
        String n = "0123456789";
        String m = s + n;
        StringBuilder code = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            code.append(m.charAt(new Random().nextInt(m.length())));
        }
        return code.toString();
    }
}
