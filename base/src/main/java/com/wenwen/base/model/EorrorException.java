package com.wenwen.base.model;

/**
 * @author xiaoxinga
 * @date 2019/01/15 11:27
 * @since
 */
public class EorrorException extends Exception{
    private String code;
    private String message;

    public EorrorException(String message) {
        super(message);
    }
    public EorrorException(String code,String message){
        super(message);
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
