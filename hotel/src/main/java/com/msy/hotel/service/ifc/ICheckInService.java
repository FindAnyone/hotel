package com.msy.hotel.service.ifc;

import com.msy.hotel.pojo.CheckIn;
import com.msy.hotel.vo.ResponseModel;

import java.util.Map;

public interface ICheckInService {

    public ResponseModel addCheckInMsg(Map<String, Object[]> map)throws Exception;
}
