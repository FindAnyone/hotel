package com.msy.hotel.service.ifc;

import com.msy.hotel.vo.ResponseModel;

import java.util.Map;

public interface IHistoryOrdersService {

    public ResponseModel findMsgByMap(Map<String,Object> map)throws Exception;

    public ResponseModel delOrderList(Integer[] ids)throws Exception;

    public ResponseModel findMsgByUser(Integer id) throws  Exception;


}
