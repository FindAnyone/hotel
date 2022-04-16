package com.msy.hotel.service.impl;

import com.msy.hotel.exception.CustomerException;
import com.msy.hotel.mapper.EmpMapper;
import com.msy.hotel.pojo.Emp;
import com.msy.hotel.service.ifc.IEmpService;
import com.msy.hotel.vo.PageUtil;
import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements IEmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public ResponseModel findEmpPage(Map<String, Object> map) throws Exception {

        List<Emp> empList = empMapper.queryMsgByMap(map);
        PageUtil<Emp> pageUtil = new PageUtil<>();
        pageUtil.setPageNumber((Integer) map.get("pageNumber"));
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setList(empList);

        pageUtil.setTotal(empMapper.queryMsgByMapCount(map));
        pageUtil.setPageCount();
        return ResponseModel.success(ResCode.SUCCESS, pageUtil);
    }

    @Override
    public ResponseModel delEmp(Integer[] ids) throws Exception {
        int row = empMapper.deleteMsg(ids);

        if(row==0){
            throw new CustomerException(777,"失败");
        }
        return ResponseModel.success(ResCode.SUCCESS);
    }



    @Override
    public ResponseModel addEmp(Emp emp) throws Exception {
        int row = empMapper.insertMsg(emp);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel findById(Integer empId) throws Exception {

        Emp emp = empMapper.queryMsgById(empId);
        if(emp!=null){
            return ResponseModel.success(ResCode.SUCCESS,emp);
        }
        return ResponseModel.error(ResCode.ERROR);
    }

    @Override
    public ResponseModel updateEmp(Emp emp) throws Exception {

        int row = empMapper.updateMsg(emp);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }
}