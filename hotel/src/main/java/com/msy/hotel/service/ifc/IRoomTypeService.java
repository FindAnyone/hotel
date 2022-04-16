package com.msy.hotel.service.ifc;

import com.msy.hotel.pojo.RoomDetailet;
import com.msy.hotel.pojo.RoomType;
import com.msy.hotel.vo.ResponseModel;

/**
 * @author whf
 */
public interface IRoomTypeService {
//    房间类型列表
    ResponseModel findRoomTypeList() throws  Exception;
//通过ID查询房间类型
    ResponseModel findRoomTypeById(Integer id) throws  Exception;
//添加房间类型
    ResponseModel insertRoomType(RoomType roomType) throws Exception;
//修改房间类型
    ResponseModel updateRoomType(RoomType roomType) throws Exception;

    ResponseModel deleteRoomTypeById(Integer[] typeId) throws Exception;
}
