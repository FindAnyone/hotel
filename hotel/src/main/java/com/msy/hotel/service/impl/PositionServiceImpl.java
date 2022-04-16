package com.msy.hotel.service.impl;

import com.msy.hotel.exception.CustomerException;
import com.msy.hotel.mapper.PositionMapper;
import com.msy.hotel.pojo.Position;
import com.msy.hotel.service.ifc.IPositionService;
import com.msy.hotel.vo.PageUtil;
import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PositionServiceImpl implements IPositionService {
    @Autowired
    private PositionMapper positionMapper;
    @Override
    public ResponseModel postitonList(Map<String, Object> map) throws Exception {
        List<Position> positionList = positionMapper.queryMsgByMap(map);
        PageUtil<Position> pageUtil=new PageUtil<>();
        pageUtil.setPageNumber((Integer) map.get("pageNumber"));
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setList(positionList);

        pageUtil.setTotal(positionMapper.queryMsgByMapCount(map));
        pageUtil.setPageCount();
        return ResponseModel.success(ResCode.SUCCESS, pageUtil);
    }

    @Override
    public ResponseModel queryAllMsg() throws Exception {
        List<Position> positions = positionMapper.queryAllMsg();
        return ResponseModel.success(ResCode.SUCCESS, positions);
    }

    @Override
    public ResponseModel updatePosition(Position position) throws Exception {

        int row = positionMapper.updateMsg(position);
        System.out.println(row);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel addPosition(Position position) throws Exception {

        int row = positionMapper.insertMsg(position);
        System.out.println(row);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel delPosition(Integer[] ids) throws Exception {
        int row = positionMapper.deleteMsg(ids);

        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }
}
