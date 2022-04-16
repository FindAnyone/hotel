package com.msy.hotel.vo;

public enum ResCode {
    SUCCESS(2000,"成功",true),
    FAIL(600,"失败",false),
    ERROR(700,"未知错误",false),

    SELECT_DATABASE_SCUCCESS(2001,"查询成功",true),
    INSERT_DATABASE_SCUCCESS(2002,"插入成功",true),
    UPDATE_DATABASE_SCUCCESS(2003,"更新成功",true),
    DELETE_DATABASE_SCUCCESS(2004,"删除成功",true),
    ;

    private Integer code;
    private String msg;
    private boolean flag;


    private ResCode(Integer code,String msg,boolean flag){
        this.code=code;
        this.msg=msg;
        this.flag=flag;
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

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
