package com.msy.hotel.controller;

import com.msy.hotel.pojo.Position;
import com.msy.hotel.service.ifc.IPositionService;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/position")
@CrossOrigin
public class PositionController {

    @Autowired
    private IPositionService postitonList;

    @RequestMapping("/findAllPosition")
    public ResponseModel findAllPosition() throws Exception {
        ResponseModel model = postitonList.queryAllMsg();
        return model;
    }

    @RequestMapping("/positionList")
    public ResponseModel positonList(@RequestBody Map<String,Object> map) throws Exception {
        System.out.println(map);
        ResponseModel model = postitonList.postitonList(map);
        return model;
    }

    @RequestMapping("/updatePosition")
    public ResponseModel updatePosition(@RequestBody Position position) throws Exception {
        ResponseModel model = postitonList.updatePosition(position);
        return model;
    }

    @RequestMapping("/addPosition")
    public ResponseModel addPosition(@RequestBody Position position) throws Exception {
        ResponseModel model = postitonList.addPosition(position);
        return model;
    }

    @RequestMapping("/delPosition")
    public ResponseModel delEmp(@RequestBody Map<String,Integer[]> ids) throws Exception {
        ResponseModel model = postitonList.delPosition(ids.get("ids"));
        return model;
    }



}
