package com.msy.hotel.pojo;

import lombok.Data;

@Data
public class RoomDetailet {

    private Integer roomId;//主键
    private Integer rTypeId;//房间类型
    private Double roomPrice;//房间价格
    private String roomImg;//房间图片
    private Integer state;//房间状态
    private Integer roomNumber;//房间号
    private Integer floor;//房间楼层
    private Integer area;//房间面积
    private Integer window;//是否有窗
    private Double peopleNum;//可住人数
    private String facilities;//房间设施
    private String remark;//备注

    private RoomType roomType;

}
