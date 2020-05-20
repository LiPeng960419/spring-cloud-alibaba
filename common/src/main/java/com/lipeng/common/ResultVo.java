package com.lipeng.common;


/**
 * @author 910138
 */
public class ResultVo<T> {

    private static final String SUCCESS = "200";
    private static final String SUCCESS_MSG = "success";
    private static final String FAIL = "500";
    private String returnCode;
    private String returnMsg;
    private T data;

    public ResultVo() {
        this.returnCode = SUCCESS;
        this.returnMsg = SUCCESS_MSG;
    }

    public ResultVo(String returnCode, String returnMsg, Object data) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.data = (T) data;
    }

    public static ResultVo success(Object data, String returnMsg) {
        ResultVo resultVo = new ResultVo(SUCCESS, returnMsg, data);
        return resultVo;
    }

    public static ResultVo success(Object data) {
        ResultVo resultVo = new ResultVo(SUCCESS, SUCCESS_MSG, data);
        return resultVo;
    }

    public static ResultVo success() {
        ResultVo resultVo = new ResultVo(SUCCESS, SUCCESS_MSG, null);
        return resultVo;
    }

    public static ResultVo fail(String returnMsg) {
        ResultVo resultVo = new ResultVo(FAIL, returnMsg, null);
        return resultVo;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}