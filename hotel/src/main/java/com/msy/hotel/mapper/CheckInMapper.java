package com.msy.hotel.mapper;

import com.msy.hotel.pojo.CheckIn;

import java.util.Map;

public interface CheckInMapper extends CommonMapper<CheckIn>{

    public int insertMsgByMap(Map<String,Object> map)throws Exception;
}
