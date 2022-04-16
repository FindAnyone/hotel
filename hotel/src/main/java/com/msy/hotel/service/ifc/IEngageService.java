package com.msy.hotel.service.ifc;

import com.msy.hotel.pojo.Engage;
import com.msy.hotel.vo.ResponseModel;

import java.util.Map;

public interface IEngageService {

    //查询所有订单
    ResponseModel findMsgByMap(Map<String, Object> map) throws  Exception;

    //删除预订订单
    ResponseModel deleteMsgByIds(Integer[] ids) throws  Exception;

    //根据用户id查询订单
    public ResponseModel findMsgById(Integer id) throws  Exception;

    //生成预订订单
    public ResponseModel addEngageMsg(Engage engage)throws Exception;

    //支付后修改状态
    public ResponseModel updateMsg(Engage engage)throws Exception;


    //查询预定表中按房间类型查询的时间冲突的房间数
    public ResponseModel findByDateCount(Map<String,Object> map)throws Exception;


}
