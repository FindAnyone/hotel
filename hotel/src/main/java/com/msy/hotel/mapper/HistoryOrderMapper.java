package com.msy.hotel.mapper;

import com.msy.hotel.pojo.HistoryOrders;

import java.util.List;
import java.util.Map;

public interface HistoryOrderMapper extends CommonMapper<HistoryOrders>{

    public List<HistoryOrders> queryByUserMap(Integer id)throws Exception;


    public int insertMsgMap(Map<String,Object> map)throws Exception;

}
