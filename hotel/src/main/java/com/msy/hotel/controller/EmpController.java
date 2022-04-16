package com.msy.hotel.controller;

import com.msy.hotel.pojo.Emp;
import com.msy.hotel.service.ifc.IEmpService;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/emp")
@CrossOrigin
public class EmpController{

    @Autowired
    private IEmpService empService;

    @RequestMapping("/findEmpList")
    public ResponseModel login(@RequestBody Map<String,Object> map) throws Exception {
        System.out.println(map);
        ResponseModel model = empService.findEmpPage(map);
        return model;
    }

    @RequestMapping("/delEmp")
    public ResponseModel delEmp(@RequestBody Map<String,Integer[]> ids) throws Exception {
        ResponseModel model = empService.delEmp(ids.get("ids"));
        return model;
    }



    @RequestMapping("/addEmp")
    public ResponseModel addEmp(@RequestBody Emp emp) throws Exception {
        ResponseModel model = empService.addEmp(emp);
        System.out.println(emp);
        return model;
    }

    @RequestMapping("/findById")
    public ResponseModel findById(Integer empId) throws Exception {
        ResponseModel model = empService.findById(empId);
        return model;
    }

    @RequestMapping("/updateEmp")
    public ResponseModel updateEmp(@RequestBody Emp emp) throws Exception {
        ResponseModel model = empService.updateEmp(emp);
        return model;
    }
}
