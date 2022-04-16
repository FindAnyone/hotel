package com.msy.hotel.service.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msy.hotel.mapper.CheckInMapper;
import com.msy.hotel.mapper.OrderMapper;
import com.msy.hotel.mapper.RoomDetailetMapper;
import com.msy.hotel.pojo.CheckIn;
import com.msy.hotel.pojo.Orders;
import com.msy.hotel.pojo.RoomDetailet;
import com.msy.hotel.service.ifc.ICheckInService;
import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CheckInServiceImpl implements ICheckInService {

    @Autowired
    private CheckInMapper checkInMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RoomDetailetMapper roomDetailetMapper;

    //    入住流程
    @Override
    public ResponseModel addCheckInMsg(Map<String, Object[]> map) throws Exception {
        int row = 0;
        Orders order = new Orders();
        for (int i = 0; i < map.get("param").length; i++) {
            Map<String, Object> map1 = (Map<String, Object>) map.get("param")[i];

            System.out.println(map1);
            int row1 = checkInMapper.insertMsgByMap(map1);

            order.setOrderId((Integer) map1.get("orderId"));
            order.setCashPledge(Double.parseDouble((String) map1.get("cashPledge")));
            order.setState(2);
            if (row1 > 0) {
                row++;
            }
        }
        if (row > 0) {
            int row2 = orderMapper.updateMsg(order);
            if (row2 > 0) {
                    return ResponseModel.success(ResCode.SUCCESS);
            }
        }
        return ResponseModel.fail(ResCode.FAIL);
    }
}
