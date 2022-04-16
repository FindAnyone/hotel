package com.msy.hotel.mapper;

import java.util.List;
import java.util.Map;

public interface CommonMapper<T> {

    public List<T> queryAllMsg()throws Exception;

    public List<T> queryMsg(T t) throws Exception;

    public List<T> queryMsgByMap(Map<String,Object> map) throws Exception;

    public int queryMsgByMapCount(Map<String,Object> map) throws Exception;

    public T queryMsgById(Integer id) throws Exception;

    public int updateMsg(T t) throws Exception;

    public int insertMsg(T t) throws Exception;

    public int deleteMsg(Integer[] ids) throws Exception;



}

