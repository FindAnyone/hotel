package com.msy.hotel.exception;

/**
 * @author: wudagai
 * @date: 2021/10/29 15:34
 * @description:
 */
public class CustomerException extends  Exception{

    private Integer code;
    private String msg;

    public CustomerException(Integer code, String msg){
        super(msg);
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
