package com.msy.hotel.service.impl;

import com.msy.hotel.mapper.RoomDetailetMapper;
import com.msy.hotel.pojo.RoomDetailet;
import com.msy.hotel.service.ifc.IRoomDetailetService;
import com.msy.hotel.vo.PageUtil;
import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author whf
 */
@Service("roomDetailetService")
public class RoomDetailetServiceImpl implements IRoomDetailetService {

    @Autowired
    private RoomDetailetMapper roomDetailetMapper;

    @Override
    public ResponseModel findRoomDetailetAllList() throws Exception {
        return ResponseModel.success(ResCode.SUCCESS,roomDetailetMapper.queryAllMsg());
    }

    @Override
    public ResponseModel findRoomDetailetList(Map<String,Object> map) throws Exception {
        return ResponseModel.success(ResCode.SUCCESS,roomDetailetMapper.queryMsgByMap(map));
    }

    @Override
    public ResponseModel insertRoomDetailet(RoomDetailet roomDetailet) throws Exception {
        int rows = roomDetailetMapper.insertMsg(roomDetailet);
        if (rows != 0) {
            return ResponseModel.success(ResCode.SUCCESS);
        } else {
            throw new Exception("添加失败！");
        }
    }

    @Override
    public ResponseModel updateRoomDetailet(RoomDetailet roomDetailet) throws Exception {
        int rows=roomDetailetMapper.updateMsg(roomDetailet);
        if(rows != 0){
            System.out.println(rows);
            return ResponseModel.success(ResCode.SUCCESS);
        }
        else{
            throw new Exception("修改失败！");
        }
    }

    @Override
    public ResponseModel findRoomDetailetById(Integer id) throws Exception {

        return ResponseModel.success(ResCode.SUCCESS,roomDetailetMapper.queryMsgById(id));
    }

    @Override
    public ResponseModel deleteRoomDetailetById(Integer[] id) throws Exception {
        int rows=roomDetailetMapper.deleteMsg(id);
        if(rows != 0){
            System.out.println(rows);
            return ResponseModel.success(ResCode.SUCCESS);
        }
            else{
                throw new Exception("删除失败！！");
        }
    }


}
