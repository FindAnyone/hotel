package com.msy.hotel.controller;
import com.msy.hotel.pojo.CheckIn;
import com.msy.hotel.service.ifc.ICheckInService;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/checkin")
@CrossOrigin
public class CheckInController {

    @Autowired
    private ICheckInService checkInService;

    @RequestMapping("/addCheckInMsg")
    public ResponseModel addCheckInMsg(@RequestBody Map<String, Object[]> map) throws Exception {
        ResponseModel model = checkInService.addCheckInMsg(map);
        return model;
    }

}
