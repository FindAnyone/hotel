package com.msy.hotel.service.ifc;

import com.msy.hotel.pojo.Orders;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

public interface IOrderService {
    public ResponseModel findOrderPage(Map<String,Object> map)throws Exception;

    public ResponseModel delOrderList(Integer[] ids)throws Exception;

    public ResponseModel findMsgByUser(Map<String,Object> map) throws  Exception;


    public ResponseModel addOrderMsg(Map<String,Object> map) throws  Exception;

    //退房流程
    public ResponseModel checkout(Map<String,Object> map) throws  Exception;

//    取消订单
    public ResponseModel cancelorder(Integer[] ids) throws  Exception;

//取消订单客服确认流程
    public ResponseModel cancelorderconfirm(Integer[] ids) throws  Exception;


    public ResponseModel findByOrderAndCheckIn(Integer id) throws  Exception;








}
