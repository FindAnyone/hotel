package com.msy.hotel.pojo;

import lombok.Data;

@Data
public class CheckIn {
    private Integer checkInId;
    private Integer orderId;
    private Integer roomId;
    private String checkName;
    private String checkIdCard;

    private Orders orders;
}
