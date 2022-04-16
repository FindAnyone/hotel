package com.msy.hotel.vo;
import lombok.Data;

/**
 * @author: wudagai
 * @date: 2021/10/29 14:29
 * @description:视图对象
 */
@Data
public class ResponseModel {
    private Integer code;
    private String msg;
    private Object obj;
    private boolean flag;

    /**
     * 成功并返回jieguo
     * @return
     */
    public static ResponseModel success(ResCode resCode, Object obj){
        ResponseModel model =new ResponseModel();
        model.code=resCode.getCode();
        model.msg=resCode.getMsg();
        model.flag=resCode.getFlag();
        model.obj=obj;
        return  model;
    }

    /**
     * 成功不返回信息
     * @return
     */
    public static ResponseModel success(ResCode resCode){
        ResponseModel model =new ResponseModel();
        model.code=resCode.getCode();
        model.msg=resCode.getMsg();
        model.flag=resCode.getFlag();
        return  model;
    }

    /**
     * 失败返回信息
     * @return
     */
    public static ResponseModel fail(ResCode resCode){
        ResponseModel model =new ResponseModel();
        model.code=resCode.getCode();
        model.flag=resCode.getFlag();
        model.msg=resCode.getMsg();
        return  model;
    }

    /**
     * 出现异常返回异常信息
     * @return
     */
    public static ResponseModel error(ResCode resCode){
        ResponseModel model =new ResponseModel();
        model.code=resCode.getCode();
        model.msg=resCode.getMsg();
        model.flag=resCode.getFlag();
        return  model;
    }
}
