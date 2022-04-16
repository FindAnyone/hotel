package com.msy.hotel.controller;

import com.msy.hotel.pojo.Orders;
import com.msy.hotel.service.ifc.IOrderService;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("findOrderList")
    public ResponseModel findOrderList(@RequestBody Map<String,Object> map) throws Exception {
//        System.out.println(map);
        ResponseModel model = orderService.findOrderPage(map);
        return model;
    }

    @RequestMapping("delOrderList")
    public ResponseModel delOrderList(@RequestBody Map<String,Integer[]> ids) throws Exception {
//        System.out.println(ids);
        ResponseModel model = orderService.delOrderList(ids.get("ids"));
        return model;
    }

    //查询用户的历史订单
    @RequestMapping("/queryByUser")
    public ResponseModel queryByUser(@RequestBody Map<String,Object> map) throws Exception {
//        System.out.println(id);
        return  orderService.findMsgByUser(map);
    }

    //生成订单
    @RequestMapping("/addOrder")
    public ResponseModel addOrder(@RequestBody Map<String,Object> map) throws Exception {

        return  orderService.addOrderMsg(map);
    }

    //退房流程
    @RequestMapping("/checkout")
    public ResponseModel checkout(@RequestBody Map<String,Object> map) throws Exception {
//        System.out.println(map);
        return  orderService.checkout(map);
    }

    //取消订单
    @RequestMapping("/cancelOrder")
    public ResponseModel cancelorder(@RequestBody Map<String,Integer[]> ids) throws Exception {
//        System.out.println(map);
        return  orderService.cancelorder(ids.get("ids"));
    }

//客服确认
    @RequestMapping("/cancelOrderConfirm")
    public ResponseModel cancelorderconfirm(@RequestBody Map<String,Integer[]> ids) throws Exception {
    //        System.out.println(map);
        return  orderService.cancelorderconfirm(ids.get("ids"));
    }

    //查询入住人信息

    @RequestMapping("/findByOrderAndCheckIn")
    public ResponseModel findByOrderAndCheckIn(Integer id) throws Exception {
        //        System.out.println(map);
        return  orderService.findByOrderAndCheckIn(id);
    }

}
