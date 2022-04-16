import request from '@/utils/request'
// 订单查询
export const getorderList = data => { return request({ url: '/hotel/historyOrder/findHistoryOrderList', method: 'post', data: data }) }


export const delorder = data => { return request({ url: '/hotel/historyOrder/delHistoryOrderList', method: 'post', data: data }) }


