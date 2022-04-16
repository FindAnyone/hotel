package com.msy.hotel.controller;
import com.msy.hotel.pojo.RoomDetailet;
import com.msy.hotel.service.ifc.IRoomDetailetService;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;
import java.util.UUID;

/**
 * @author whf
 */
@RestController
@RequestMapping("/room")
@CrossOrigin
public class RoomDetailetController {

    @Autowired
    private IRoomDetailetService roomDetailetService;

    @RequestMapping("/roomAllList")
    private ResponseModel queryRoomDetailetAllList() throws Exception {
//        System.out.println("请求了queryRoomDetailetAllList");
        return roomDetailetService.findRoomDetailetAllList();
    }
    @RequestMapping("/roomList")
    private ResponseModel queryRoomDetailetList(@RequestBody Map<String,Object> map) throws Exception {
//        System.out.println("请求了1111==========="+map);
        System.out.println(map);
        return roomDetailetService.findRoomDetailetList(map);
    }
    @RequestMapping("/getRoomById")
    private ResponseModel queryRoomDetailetById(Integer roomId) throws Exception {
//        System.out.println("请求了queryRoomDetailetById");
//        System.out.println(roomId);
        return roomDetailetService.findRoomDetailetById(roomId);
    }
    @RequestMapping("/roomAdd")
    private ResponseModel RoomDetailetAdd(@RequestBody RoomDetailet roomDetailet) throws Exception {

        System.out.println(roomDetailet);
        return roomDetailetService.insertRoomDetailet(roomDetailet);
    }
    @RequestMapping("/roomupdate")
    private ResponseModel RoomDetailetUpdata(@RequestBody RoomDetailet roomDetailet) throws Exception {
//        System.out.println("请求了3333");
        System.out.println(roomDetailet);
        return roomDetailetService.updateRoomDetailet(roomDetailet);
    }
    @RequestMapping("/deleteRoomById")
    private ResponseModel deleteRoomDetailetById(Integer[] roomId) throws Exception {
//        System.out.println("请求了deleteRoomDetailetById");
        return roomDetailetService.deleteRoomDetailetById(roomId);
    }

}
