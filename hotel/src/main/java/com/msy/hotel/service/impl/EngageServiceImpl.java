package com.msy.hotel.service.impl;

import com.msy.hotel.mapper.AvailableRoomMapper;
import com.msy.hotel.mapper.EngageMapper;
import com.msy.hotel.pojo.Engage;
import com.msy.hotel.service.ifc.IEngageService;
import com.msy.hotel.vo.PageUtil;
import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service("engageServiceImpl")
public class EngageServiceImpl implements IEngageService {
    @Autowired
    private  EngageMapper enagegMapper;

    @Autowired
    private AvailableRoomMapper availableRoomMapper;
    @Override
    public ResponseModel findMsgByMap(Map<String, Object> map) throws Exception {
        List<Engage> engageList= enagegMapper.queryMsgByMap(map);
        PageUtil<Engage> pageUtil=new PageUtil<>();

        pageUtil.setPageNumber((Integer) map.get("pageNumber"));
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setList(engageList);
        System.out.println(engageList);
        //查询总记录数
        pageUtil.setTotal(enagegMapper.queryMsgByMapCount(map));
        pageUtil.setPageCount();
        return ResponseModel.success(ResCode.SUCCESS,pageUtil);
    }

    public ResponseModel deleteMsgByIds(Integer[] ids) throws Exception{
       int row = enagegMapper.deleteMsg(ids);

        if(row==ids.length){
            return ResponseModel.success(ResCode.SUCCESS);
        }else{
            throw  new Exception("操作失败");
        }
    }

    @Override
    public ResponseModel findMsgById(Integer id) throws Exception {

        List<Engage> engages = enagegMapper.queryByUserId(id);
        return ResponseModel.success(ResCode.SUCCESS,engages);
    }

    @Override
    public ResponseModel addEngageMsg(Engage engage) throws Exception {

        int row = enagegMapper.insertMsg(engage);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        throw  new Exception("操作失败");
    }


    //支付后修改状态
    @Override
    public ResponseModel updateMsg(Engage engage) throws Exception {

        int row = enagegMapper.updateMsg(engage);

        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        throw  new Exception("操作失败");
    }

    @Override
    public ResponseModel findByDateCount(Map<String,Object> map) throws Exception {
        return ResponseModel.success(ResCode.SUCCESS,availableRoomMapper.queryMsgByMapCount(map)-enagegMapper.queryByDateCount(map));
    }

}
