package com.msy.hotel.service.impl;

import com.msy.hotel.exception.CustomerException;
import com.msy.hotel.mapper.UserMapper;
import com.msy.hotel.pojo.Users;
import com.msy.hotel.service.ifc.IUserService;
import com.msy.hotel.vo.PageUtil;
import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseModel login(Map<String, Object> map) throws Exception {
        Users users=userMapper.login(map);
        if(users==null){
            throw new Exception("没有此用户，请注册!");
        }
        return ResponseModel.success(ResCode.SUCCESS,users);
    }

    @Override
    public ResponseModel queryMsgByPhone(String phone) throws Exception {
        Users users = userMapper.queryByPhone(phone);
        Random rad=new Random();
        if(users==null){
            Users user=new Users();
            user.setPhone(phone);
            String str="qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<=5;i++){
                sb.append(str.charAt(rad.nextInt(str.length())));
            }
            user.setUserName(sb.toString());
            user.setGrade(2);
            System.out.println(user);
            int row = userMapper.insertMsg(user);
            if(row==0){
                throw new CustomerException(777,"注册失败");
            }else{
                Users users1 = userMapper.queryByPhone(phone);
                return ResponseModel.success(ResCode.SUCCESS,users1);
            }
        }
        return ResponseModel.success(ResCode.SUCCESS,users);
    }

    @Override
    public ResponseModel addUser(Users user) throws Exception {

        Users users = userMapper.queryByPhone(user.getPhone());
        System.out.println(user);
        if(users!=null){
            return ResponseModel.fail(ResCode.FAIL);
        }else {
            int row = userMapper.insertMsg(user);
            if (row == 0) {
                throw new CustomerException(777, "注册失败");
            }
            return ResponseModel.success(ResCode.SUCCESS);
        }
    }


    /**
     * 查询所有用户信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public ResponseModel findUserList(Map<String, Object> map) throws Exception {
        List<Users> usersList = userMapper.queryMsgByMap(map);
        PageUtil<Users> pageUtil = new PageUtil<>();
        pageUtil.setPageNumber((Integer) map.get("pageNumber"));
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setList(usersList);

        //查询总记录数
        pageUtil.setTotal(userMapper.queryMsgByMapCount(map));
        pageUtil.setPageCount();
        return ResponseModel.success(ResCode.SUCCESS, pageUtil);
    }

    @Override
    public ResponseModel insertMsg(Users users) throws Exception {
        int row = userMapper.insertMsg(users);

        if (row==0){
            System.out.println("请求错误！"+row);
            return ResponseModel.fail(ResCode.FAIL);
        }
        System.out.println(row);
        return ResponseModel.success(ResCode.SUCCESS);
    }

    @Override
    public ResponseModel updateMsg(Users users) throws Exception {

        System.out.println(users);
        int row= userMapper.updateMsg(users);
        if (row==0){
            return ResponseModel.fail(ResCode.FAIL);
        }
        return ResponseModel.success(ResCode.SUCCESS);
    }

    @Override
    public ResponseModel deleteUser(Integer[] ids) throws Exception {
        int row=userMapper.deleteMsg(ids);

        System.out.println(ids);
        if(row==ids.length){
            return ResponseModel.success(ResCode.SUCCESS);
        }else{
            throw  new Exception("操作失败");
        }
    }


    @Override
    public ResponseModel queryMsgById(Integer id) throws Exception {

        return ResponseModel.success(ResCode.SUCCESS,userMapper.queryMsgById(id));
    }

}
