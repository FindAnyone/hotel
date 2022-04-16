package com.msy.hotel.controller;

import com.msy.hotel.pojo.HistoryOrders;
import com.msy.hotel.service.ifc.IHistoryOrdersService;
import com.msy.hotel.service.ifc.IOrderService;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/historyOrder")
@CrossOrigin
public class HistoryOrderController {
    @Autowired
    private IHistoryOrdersService historyOrdersService;

    @RequestMapping("findHistoryOrderList")
    public ResponseModel findOrderList(@RequestBody Map<String,Object> map) throws Exception {
//        System.out.println(map);
        ResponseModel model = historyOrdersService.findMsgByMap(map);
        return model;
    }
//
    @RequestMapping("delHistoryOrderList")
    public ResponseModel delOrderList(@RequestBody Map<String,Integer[]> ids) throws Exception {
//        System.out.println(ids);
        ResponseModel model = historyOrdersService.delOrderList(ids.get("ids"));
        return model;
    }
//
    //查询用户的历史订单
    @RequestMapping("/queryHistoryByUser")
    public ResponseModel queryByUser(Integer id) throws Exception {
//        System.out.println(id);
        return  historyOrdersService.findMsgByUser(id);
    }
//
}
