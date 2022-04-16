package com.msy.hotel.service.ifc;

import com.msy.hotel.vo.ResponseModel;

import java.util.Map;

public interface IAvailableRoomService {
    //查询可预订房间
    ResponseModel findEngageRoom(Map<String, Object> map) throws  Exception;


    //查询可预订房间数量
    ResponseModel findavailableroom(Map<String, Object> map) throws  Exception;



    //根据预订信息查询楼层
    public ResponseModel fingdByTypeId(Integer id)throws Exception;


    //房间联动
    ResponseModel findByMap(Map<String, Object> map) throws  Exception;


}
