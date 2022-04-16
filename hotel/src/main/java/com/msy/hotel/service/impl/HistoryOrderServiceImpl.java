package com.msy.hotel.service.impl;

import com.msy.hotel.exception.CustomerException;
import com.msy.hotel.mapper.HistoryOrderMapper;
import com.msy.hotel.pojo.HistoryOrders;
import com.msy.hotel.service.ifc.IHistoryOrdersService;
import com.msy.hotel.vo.PageUtil;
import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HistoryOrderServiceImpl implements IHistoryOrdersService {

    @Autowired
    private HistoryOrderMapper historyOrderMapper;
    @Override
    public ResponseModel findMsgByMap(Map<String, Object> map) throws Exception {

        List<HistoryOrders> historyOrders = historyOrderMapper.queryMsgByMap(map);
        PageUtil<HistoryOrders> pageUtil = new PageUtil<>();
        pageUtil.setPageNumber((Integer) map.get("pageNumber"));
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setList(historyOrders);

        pageUtil.setTotal(historyOrderMapper.queryMsgByMapCount(map));
        pageUtil.setPageCount();

        return ResponseModel.success(ResCode.SUCCESS, pageUtil);
    }


    @Override
    public ResponseModel delOrderList(Integer[] ids) throws Exception {
        int row = historyOrderMapper.deleteMsg(ids);
        if (row == 0) {
            throw new CustomerException(777, "删除失败");
        }
        return ResponseModel.success(ResCode.SUCCESS);
    }

    @Override
    public ResponseModel findMsgByUser(Integer id) throws Exception {
        List<HistoryOrders> historyOrders = historyOrderMapper.queryByUserMap(id);
        return ResponseModel.success(ResCode.SUCCESS, historyOrders);
    }

//    //生成订单业务逻辑
//    @Override
//    public ResponseModel addOrderMsg(Map<String,Object> map) throws Exception {
//        int row = historyOrderMapper.insertMsgMap(map);
//        if (row > 0) {
//                return ResponseModel.success(ResCode.SUCCESS);
//        }
//        return ResponseModel.fail(ResCode.FAIL);
//    }

}
