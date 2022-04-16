package com.msy.hotel.mapper;

import com.msy.hotel.pojo.RoomDetailet;

import java.util.List;
import java.util.Map;

public interface AvailableRoomMapper extends CommonMapper<RoomDetailet>{


    //根据预订信息查询楼层
    public List<RoomDetailet> queryByTypeId(Integer id)throws Exception;


    //房间联动
    public List<RoomDetailet> queryByMap(Map<String,Object> map)throws Exception;

}
