package com.msy.hotel.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Engage {

    private Integer engageId;
    private Integer typeId;
    private Integer userId;
    private Integer quantity;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date checkTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date outTime;
    private Double total;
    private Integer state;

    private RoomType roomType;
    private RoomDetailet roomDetailet;
    private Users users;

}
