package com.msy.hotel.service.impl;

import com.msy.hotel.mapper.AvailableRoomMapper;
import com.msy.hotel.pojo.RoomDetailet;
import com.msy.hotel.service.ifc.IAvailableRoomService;
import com.msy.hotel.vo.PageUtil;
import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AvailableRoomServiceImpl implements IAvailableRoomService {
    @Autowired
    private AvailableRoomMapper availableRoomMapper;
    @Override
    public ResponseModel findEngageRoom(Map<String, Object> map) throws Exception {

        List<RoomDetailet> roomDetailets = availableRoomMapper.queryMsgByMap(map);

        PageUtil<RoomDetailet> pageUtil = new PageUtil<>();
        pageUtil.setPageNumber((Integer) map.get("pageNumber"));
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setList(roomDetailets);

        pageUtil.setTotal(availableRoomMapper.queryMsgByMapCount(map));
        pageUtil.setPageCount();

        return ResponseModel.success(ResCode.SUCCESS,pageUtil);
    }

    @Override
    public ResponseModel findavailableroom(Map<String, Object> map) throws Exception {
        PageUtil<RoomDetailet> pageUtil = new PageUtil<>();
        pageUtil.setTotal(availableRoomMapper.queryMsgByMapCount(map));
        return  ResponseModel.success(ResCode.SUCCESS,pageUtil);
    }

    @Override
    public ResponseModel fingdByTypeId(Integer id) throws Exception {

        List<RoomDetailet> roomDetailets = availableRoomMapper.queryByTypeId(id);
        return ResponseModel.success(ResCode.SUCCESS,roomDetailets);
    }

    @Override
    public ResponseModel findByMap(Map<String, Object> map) throws Exception {

        List<RoomDetailet> roomDetailets = availableRoomMapper.queryByMap(map);
        return ResponseModel.success(ResCode.SUCCESS,roomDetailets);
    }
}
