package com.msy.hotel.mapper;

import com.msy.hotel.exception.CustomerException;
import com.msy.hotel.pojo.Users;
import com.tencentcloudapi.cms.v20190321.models.User;

import java.util.Map;

public interface UserMapper extends CommonMapper<Users>{

    //登录功能
    public Users login(Map<String,Object> map) throws CustomerException;

    public Users queryByPhone(String phone)throws Exception;
}
