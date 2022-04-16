package com.msy.hotel.service.impl;

import com.msy.hotel.exception.CustomerException;
import com.msy.hotel.mapper.EngageMapper;
import com.msy.hotel.mapper.HistoryOrderMapper;
import com.msy.hotel.mapper.OrderMapper;
import com.msy.hotel.mapper.RoomDetailetMapper;
import com.msy.hotel.pojo.Engage;
import com.msy.hotel.pojo.Orders;
import com.msy.hotel.pojo.RoomDetailet;
import com.msy.hotel.service.ifc.IOrderService;
import com.msy.hotel.vo.PageUtil;
import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderviceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private EngageMapper engageMapper;
    @Autowired
    private HistoryOrderMapper historyOrderMapper;

    @Override
    public ResponseModel findOrderPage(Map<String, Object> map) throws Exception {
        List<Orders> orders = orderMapper.queryMsgByMap(map);
        PageUtil<Orders> pageUtil = new PageUtil<>();
        pageUtil.setPageNumber((Integer) map.get("pageNumber"));
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setList(orders);

        pageUtil.setTotal(orderMapper.queryMsgByMapCount(map));
        pageUtil.setPageCount();

        return ResponseModel.success(ResCode.SUCCESS, pageUtil);
    }


    @Override
    public ResponseModel delOrderList(Integer[] ids) throws Exception {
        int row = orderMapper.deleteMsg(ids);
        if (row == 0) {
            throw new CustomerException(777, "删除失败");
        }
        return ResponseModel.success(ResCode.SUCCESS);
    }

    @Override
    public ResponseModel findMsgByUser(Map<String, Object> map) throws Exception {
        List<Orders> orders = orderMapper.queryByUserMap(map);
        return ResponseModel.success(ResCode.SUCCESS, orders);
    }

    //生成订单业务逻辑
    @Override
    public ResponseModel addOrderMsg(Map<String, Object> map) throws Exception {
        map.put("state", 1);

        System.out.println("addorder="+map);
        int row = orderMapper.insertOrderMsg(map);
        if (row > 0) {
                int row2 = engageMapper.deleteById((Integer) map.get("engageId"));
                if (row2 > 0) {
                    return ResponseModel.success(ResCode.SUCCESS);
            }
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    //退房流程
    @Override
    public ResponseModel checkout(Map<String, Object> map) throws Exception {

        System.out.println(map.get("orderId"));
        Integer id=(Integer) map.get("orderId");
        int row = historyOrderMapper.insertMsgMap(map);
        if(row>0){
            int row1 = orderMapper.deleteById(id);
            if(row1>0){
                return ResponseModel.success(ResCode.SUCCESS);
            }
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    //    取消订单
    @Override
    public ResponseModel cancelorder(Integer[] ids) throws Exception {
        for (int i = 0; i < ids.length; i++) {
            Orders orders = new Orders();
            orders.setOrderId(ids[i]);
            orders.setState(3);
            int row = orderMapper.updateMsg(orders);
            if (row > 0) {
                return ResponseModel.success(ResCode.SUCCESS);
            }
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    //取消订单客服确认流程
    @Override
    public ResponseModel cancelorderconfirm(Integer[] ids) throws Exception {
        for (int i = 0; i < ids.length; i++) {
            Orders orders = orderMapper.queryMsgById(ids[i]);
                int row1 = orderMapper.deleteMsg(ids);
                if(row1>0) {
                    return ResponseModel.success(ResCode.SUCCESS);
            }
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel findByOrderAndCheckIn(Integer id) throws Exception {
        Orders orders = orderMapper.queryByOrderAndCheckIn(id);
        if(orders!=null){
            return ResponseModel.success(ResCode.SUCCESS,orders);
        }
        throw new CustomerException(777,"没有入住人信息");
    }


}
