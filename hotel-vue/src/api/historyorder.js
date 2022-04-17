
import request from '@/utils/request'
//查询历史订单

export const gethistoryorder = params => { return request({ url: '/hotel/historyOrder/queryHistoryByUser',  method: 'get', params: params }) }


//删除历史订单
export const delorder = data => { return request({ url: '/hotel/historyOrder/delHistoryOrderList', method: 'post', data: data }) }
