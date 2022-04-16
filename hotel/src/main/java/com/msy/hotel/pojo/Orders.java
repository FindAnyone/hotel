package com.msy.hotel.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Orders {
    private Integer orderId;
    private Integer typeId;
    private Integer roomId;
    private Integer userId;
    private Double total;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date checkTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date outTime;
    private Double cashPledge;
    private Integer state;
    private Integer payState;
    private Integer quantity;

    private RoomType roomType;
    private RoomDetailet roomDetailet;
    private Users users;

    private List<CheckIn> checkInList;
}
