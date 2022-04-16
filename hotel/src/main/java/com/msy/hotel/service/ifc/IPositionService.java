package com.msy.hotel.service.ifc;

import com.msy.hotel.pojo.Position;
import com.msy.hotel.vo.ResponseModel;

import java.util.Map;

public interface IPositionService {
    public ResponseModel postitonList(Map<String,Object> map)throws Exception;


    public ResponseModel queryAllMsg()throws Exception;


    public ResponseModel updatePosition(Position position)throws Exception;

    public ResponseModel addPosition(Position position)throws Exception;


    public ResponseModel delPosition(Integer[] ids)throws Exception;
}
