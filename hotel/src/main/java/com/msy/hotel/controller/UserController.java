package com.msy.hotel.controller;

import com.msy.hotel.exception.CustomerException;
import com.msy.hotel.pojo.Users;
import com.msy.hotel.service.ifc.IUserService;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    //后台登录
    @RequestMapping("/login")
    public ResponseModel login(@RequestBody Map<String,Object> map) throws Exception {
        ResponseModel model = userService.login(map);
        return model;
    }


    //前台登录
    @RequestMapping("/FrontDesklogin")
    public ResponseModel FrontDesklogin(String phone) throws Exception {
//        System.out.println(phone);
        ResponseModel model = userService.queryMsgByPhone(phone);
        return model;
    }

    //用户注册
    @RequestMapping("/register")
    public ResponseModel addUser(Users user) throws Exception {
//        System.out.println(phone);
        ResponseModel model = userService.addUser(user);
        return model;
    }

    @RequestMapping("/queryUserList")
    public ResponseModel queryUserList(@RequestBody Map<String,Object> map) throws Exception {

        return  userService.findUserList(map);
    }

    @RequestMapping("/insertUser")
    public int insertMsg(@RequestBody Users users)throws Exception {

        return userService.insertMsg(users).getCode();
    }

    @RequestMapping("/updateMsg")
    public ResponseModel updateMsg(@RequestBody Users users)throws Exception  {

        return userService.updateMsg(users);
    }

    @RequestMapping("/queryMsgById")
    public ResponseModel queryMsgById(Integer userId)throws Exception  {

        return userService.queryMsgById(userId);
    }

    @RequestMapping("/deleteUser")
    public ResponseModel deleteUser(@RequestBody  Map<String,Integer[]> ids) throws Exception {
        return userService.deleteUser(ids.get("ids"));
    }
}
