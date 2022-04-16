package com.msy.hotel.service.impl;

import com.msy.hotel.mapper.RoomTypeMapper;
import com.msy.hotel.pojo.RoomType;
import com.msy.hotel.service.ifc.IRoomTypeService;
import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author whf
 */
@Service("roomTypeService")
public class RoomTypeServiceImpl implements IRoomTypeService {
    @Autowired
    private RoomTypeMapper roomTypeMapper;
    @Override
    public ResponseModel findRoomTypeList() throws Exception {

        return ResponseModel.success(ResCode.SUCCESS,roomTypeMapper.queryAllMsg());
    }

    @Override
    public ResponseModel findRoomTypeById(Integer id) throws Exception {

        return ResponseModel.success(ResCode.SUCCESS,roomTypeMapper.queryMsgById(id));
    }

    @Override
    public ResponseModel insertRoomType(RoomType roomType) throws Exception {
        int rows=roomTypeMapper.insertMsg(roomType);
        if(rows!=0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        else{
            throw new Exception("添加失败！！");
        }
    }

    @Override
    public ResponseModel updateRoomType(RoomType roomType) throws Exception {
        int rows=roomTypeMapper.updateMsg(roomType);
        if(rows != 0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        else{
            throw new Exception("修改失败！");
        }
    }

    @Override
    public ResponseModel deleteRoomTypeById(Integer[] typeId) throws Exception {
        int rows=roomTypeMapper.deleteMsg(typeId);
        if(rows != 0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        else{
            throw new Exception("删除失败！！");
        }
    }
}
