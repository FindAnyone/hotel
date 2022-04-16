package com.msy.hotel.service.ifc;

import com.msy.hotel.exception.CustomerException;
import com.msy.hotel.pojo.Users;
import com.msy.hotel.vo.ResponseModel;

import java.util.Map;

public interface IUserService {
    public ResponseModel login(Map<String,Object> map) throws Exception;

    //前台登录流程
    public ResponseModel queryMsgByPhone(String phone)throws Exception;

    public ResponseModel addUser(Users user)throws Exception;

    ResponseModel queryMsgById(Integer id) throws  Exception;

    ResponseModel findUserList(Map<String,Object> map) throws  Exception;

    ResponseModel insertMsg(Users users) throws  Exception;

    ResponseModel updateMsg(Users users) throws  Exception;

    ResponseModel deleteUser(Integer[] ids) throws Exception;



}
