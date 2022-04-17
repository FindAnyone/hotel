import request from '@/utils/request'

//查询历史订单

export const gethistoryorder = data => { return request({ url: '/hotel/order/queryByUser',  method: 'post', data: data }) }


//删除历史订单
export const delorder = data => { return request({ url: '/hotel/order/cancelOrder', method: 'post', data: data }) }



