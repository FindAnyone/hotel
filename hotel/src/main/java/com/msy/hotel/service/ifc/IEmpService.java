package com.msy.hotel.service.ifc;

import com.msy.hotel.pojo.Emp;
import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

public interface IEmpService {

    public ResponseModel findEmpPage(Map<String,Object> map)throws Exception;

    public ResponseModel delEmp(Integer[] ids)throws Exception;

    public ResponseModel addEmp(Emp emp)throws Exception;

    public ResponseModel findById(Integer empId) throws Exception;

    public ResponseModel updateEmp(Emp emp)throws Exception;

}
