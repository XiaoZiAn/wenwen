package com.wenwen.System.code;

/**
 * @author xiaoxinga
 * @date 2018/9/10 16:04
 * @since
 */
public enum ResultCode {
    WL_ERROR("网络错误", "1000");
    public final String rsMsg;
    public final String rsCode;
    private ResultCode(String rsMsg, String rsCode) {
        this.rsMsg = rsMsg;
        this.rsCode = rsCode;
    }

    public static ResultCode getByValue(String rsCode) {
        ResultCode bt = null;
        for (ResultCode resultCode : ResultCode.values()) {
            if (resultCode.rsCode.equals(rsCode)) {
                bt = resultCode;
                break;
            }
        }
        if (null == bt) {
            throw new IllegalArgumentException("param value is not right,please check it.");
        }
        return bt;
    }
}
