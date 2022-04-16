package com.msy.hotel.vo;


import java.util.List;

public class PageUtil<T>{
//    页码
    private Integer pageNumber;
//    页面条数
    private Integer pageSize;
//    总计条数
    private Integer total;
//    总页数
    private Integer pageCount;
//    数据
    private List<T> list;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {

        this.total = total;

    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount() {
        this.pageCount = this.total%this.pageSize==0?this.total/this.pageSize:this.total/this.pageSize+1;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

