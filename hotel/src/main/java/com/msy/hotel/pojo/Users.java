package com.msy.hotel.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Users {
    private Integer userId;
    private String userName;
    private String password;
    private Integer grade;
    private String name;
    private Integer gender;
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;
    private String idCard;
    private String userImg;
    private String address;

}
