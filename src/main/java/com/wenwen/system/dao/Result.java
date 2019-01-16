package com.wenwen.system.dao;

import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author xiaoxinga
 * @date 2018/9/10 15:58
 * @since
 */
@SuppressWarnings("serial")
@NoArgsConstructor
public class Result<T> implements Serializable {
    private String rsMsg;
    private String rsCode;
    private T data;

    public Result(String rsCode, String rsMsg) {
        this(rsCode, rsMsg, null);
    }

    public Result(String rsCode, String rsMsg, T data) {
        this.data = data;
        this.rsCode = rsCode;
        this.rsMsg = rsMsg;
    }

    public Result(ResultEnums resultEnums) {
        this(resultEnums.rsCode, resultEnums.rsMsg, null);
    }

    public void setRsMsg(String rsMsg) {
        this.rsMsg = rsMsg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setResultEnums(ResultEnums resultEnums) {
        this.rsCode = resultEnums.rsCode;
        this.rsMsg = resultEnums.rsMsg;
    }

    public void setResultEnums(ResultEnums resultEnums, T data) {
        this.rsCode = resultEnums.rsCode;
        this.rsMsg = resultEnums.rsMsg;
        this.data = data;
    }

    public String getRsMsg() {
        return rsMsg;
    }

    public String getRsCode() {
        return rsCode;
    }

    public enum ResultEnums {
        SUCCESS("操作成功", "00000"),
        SIGIN_SUCCESS("注册成功", "0000"),
        SIGIN_ERROR("注册失败", "0001"),
        LOGON_SUCCESS("登录成功", "1000"),
        LOGON_ERROR("登录失败", "1001"),
        SYSTEM_ERROR("系统异常", "00001");
        public final String rsMsg;
        public final String rsCode;

        ResultEnums(String rsMsg, String rsCode) {
            this.rsMsg = rsMsg;
            this.rsCode = rsCode;
        }

        public static ResultEnums getResultEnumsByCode(String rsCode) {
            for (ResultEnums resultCode : ResultEnums.values()) {
                if (resultCode.rsCode.equals(rsCode)) {
                    return resultCode;
                }
            }
            return null;
        }
    }
}
