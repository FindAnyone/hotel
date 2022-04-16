package com.msy.hotel.service.ifc;

import com.msy.hotel.pojo.RoomDetailet;
import com.msy.hotel.vo.ResponseModel;

import java.util.Map;

/**
 * @author whf
 */
public interface IRoomDetailetService {
    //查询所有列表
    ResponseModel findRoomDetailetAllList() throws Exception;
    //组合查询查询所有
    ResponseModel findRoomDetailetList(Map<String, Object> map) throws  Exception;
    //添加房间
    ResponseModel insertRoomDetailet(RoomDetailet roomDetailet) throws Exception;
    //修改房间
    ResponseModel updateRoomDetailet(RoomDetailet roomDetailet) throws Exception;

    ResponseModel findRoomDetailetById(Integer id) throws Exception;

    ResponseModel deleteRoomDetailetById(Integer[] id) throws Exception;

}
