package System.dao;

import System.code.ResultCode;

/**
 * @author xiaoxinga
 * @date 2018/9/10 15:58
 * @since
 */
public class Result {
    private String rsMsg;
    private String rsCode;
    public void setRsCode (String rsCode){
        this.rsCode = rsCode;
    }

    public String getRsCode (){
        return rsCode;
    }

    public void setRsMsg(){
        this.rsMsg = ResultMsg.getResultByCode(rsCode).rsMsg;
    }

    public String getRsMsg(){
        return rsMsg;
    }
    public enum ResultMsg {
        WL_ERROR("网络错误", "1000");
        public final String rsMsg;
        public final String rsCode;

        private ResultMsg(String rsMsg, String rsCode) {
            this.rsMsg = rsMsg;
            this.rsCode = rsCode;
        }
        public static ResultMsg getResultByCode(String rsCode) {
            for (ResultMsg resultCode : ResultMsg.values()) {
                if (resultCode.rsCode.equals(rsCode)) {
                    return resultCode;
                }
            }
            return null;
        }
    }
    }
