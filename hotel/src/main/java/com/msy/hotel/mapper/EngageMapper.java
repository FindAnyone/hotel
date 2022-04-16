package com.msy.hotel.mapper;

import com.msy.hotel.pojo.Engage;

import java.util.List;
import java.util.Map;

public interface EngageMapper extends CommonMapper<Engage>{
    public List<Engage> queryByUserId(Integer id)throws Exception;

    public int deleteById(Integer id)throws Exception;

    //查询预定表中按房间类型查询的时间冲突的房间数
    public int queryByDateCount(Map<String,Object> map) throws Exception;

}
