package com.msy.hotel.controller;
import com.msy.hotel.pojo.Engage;
import com.msy.hotel.service.ifc.IEngageService;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.geom.AreaOp;

import java.util.Map;

@RestController
@RequestMapping("/engage")
@CrossOrigin
public class EngageController {
    @Autowired
    private IEngageService engageService;

    @RequestMapping("/queryMsgByMap")
    public ResponseModel queryMsgByMap(@RequestBody Map<String,Object> map) throws Exception {
//        System.out.println(11111);
//        System.out.println(map);
        return  engageService.findMsgByMap(map);
    }

    @RequestMapping("/delete")
    public ResponseModel delete(@RequestBody Integer[] ids) throws Exception {
//        System.out.println(11111);
        System.out.println(ids);
        return  engageService.deleteMsgByIds(ids);
    }

    //查询用户的预订订单
    @RequestMapping("/queryById")
    public ResponseModel findById(Integer id) throws Exception {
//        System.out.println(id);
        return  engageService.findMsgById(id);
    }

    @RequestMapping("/addEngageMsg")
    public ResponseModel addEngageMsg(@RequestBody Engage engage) throws Exception {
//        System.out.println(engage);
        return  engageService.addEngageMsg(engage);
    }

    @RequestMapping("/updateEngageMsg")
    public ResponseModel updateEngageMsg(@RequestBody Engage engage) throws Exception {
//        System.out.println(engage);
        return  engageService.updateMsg(engage);
    }


    //查询预定表中按房间类型查询的时间冲突的房间数
    @RequestMapping("/findByDateCount")
    public ResponseModel findByDateCount(@RequestBody Map<String,Object> map) throws Exception {
        System.out.println(map);
        return  engageService.findByDateCount(map);
    }
}
