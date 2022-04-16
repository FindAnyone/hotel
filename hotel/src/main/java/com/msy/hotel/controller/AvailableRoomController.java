package com.msy.hotel.controller;

import com.msy.hotel.service.ifc.IAvailableRoomService;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/availableRoom")
@CrossOrigin
public class AvailableRoomController {

    @Autowired
    private IAvailableRoomService availableRoomService;

    @RequestMapping("/findEngageRoom")
    private ResponseModel findEngageRoom(@RequestBody Map<String,Object> map) throws Exception {
//        System.out.println(map);
        return availableRoomService.findEngageRoom(map);
    }

    @RequestMapping("/findAvailableRoomCount")
    private ResponseModel findAvailableRoomCount(@RequestBody Map<String,Object> map) throws Exception {
        System.out.println(map);
        return availableRoomService.findavailableroom(map);
}


    @RequestMapping("/fingdByTypeId")
    private ResponseModel fingdByTypeId(Integer id) throws Exception {
//        System.out.println(id);
        return availableRoomService.fingdByTypeId(id);
    }


    @RequestMapping("/findByMap")
    private ResponseModel findByMap(@RequestBody Map<String,Object> map) throws Exception {
//        System.out.println(map);
        return availableRoomService.findByMap(map);
    }


}
