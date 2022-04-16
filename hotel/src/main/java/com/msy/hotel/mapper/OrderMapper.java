package com.msy.hotel.mapper;

import com.msy.hotel.pojo.Orders;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Map;

public interface OrderMapper extends CommonMapper<Orders>{


    public List<Orders> queryByUserMap(Map<String,Object> map)throws Exception;

    //生成订单
    public int insertOrderMsg(Map<String,Object> map) throws Exception;

    //退房流程
    public int checkout(Map<String,Object> map) throws Exception;


    public Orders queryByOrderAndCheckIn(Integer id)throws Exception;

    public int deleteById(Integer id)throws  Exception;

}
