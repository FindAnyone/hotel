package com.msy.hotel.pojo;

import lombok.Data;

@Data
public class Emp {

    private Integer empId;
    private String empName;
    private Integer gender;
    private String idCard;
    private String empPhone;
    private String empImg;
    private Double salary;
    private Integer grade;

    private Position position;
}
