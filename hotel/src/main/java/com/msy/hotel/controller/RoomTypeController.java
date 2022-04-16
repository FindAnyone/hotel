package com.msy.hotel.controller;

import com.msy.hotel.pojo.RoomDetailet;
import com.msy.hotel.pojo.RoomType;
import com.msy.hotel.service.ifc.IRoomTypeService;
import com.msy.hotel.vo.ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whf
 */
@RestController
@RequestMapping("/roomType")
@CrossOrigin
public class RoomTypeController {
    @Autowired
    private IRoomTypeService roomTypeService;
    @RequestMapping("/roomTypeList")
    public ResponseModel findAllRoomType() throws Exception {
        System.out.println("请求了roomTypeList");
        return roomTypeService.findRoomTypeList();
    }
    @RequestMapping("/getRoomTypeById")
    public ResponseModel findAllRoomTypeById(Integer typeId) throws Exception {
        System.out.println("请求了roomLIst"+"===="+typeId);
        return roomTypeService.findRoomTypeById(typeId);
    }
    @RequestMapping("/roomTypeAdd")
    private ResponseModel insertRoomTypeAdd(@RequestBody RoomType roomType) throws Exception {
        System.out.println("请求了insertRoomTypeAdd");
        System.out.println(roomType);
        return roomTypeService.insertRoomType(roomType);
    }
    @RequestMapping("/roomTypeupdate")
    private ResponseModel RoomTypeUpdata(@RequestBody RoomType roomType) throws Exception {
        System.out.println("请求了RoomTypeUpdata");
        System.out.println(roomType);
        return roomTypeService.updateRoomType(roomType);
    }
    @RequestMapping("/deleteRoomTypeById")
    private ResponseModel deleteRoomTypeById(Integer[] typeId) throws Exception {
        System.out.println("请求了deleteRoomTypeById");
        return roomTypeService.deleteRoomTypeById(typeId);
    }
}
