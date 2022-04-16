import request from '@/utils/request'
// 订单查询
export const getorderList = data => { return request({ url: '/hotel/order/findOrderList', method: 'post', data: data }) }

// 删除订单
export const delorder = data => { return request({ url: '/hotel/order/delOrderList', method: 'post', data: data }) }

// 生成订单
export const addorder = data => { return request({ url: '/hotel/order/addOrder', method: 'post', data: data }) }

//退房流程
export const checkout = data => { return request({ url: '/hotel/order/checkout', method: 'post', data: data }) }

//取消订单客服确认
export const getcancelOrderConfirm = data => { return request({ url: '/hotel/order/cancelOrderConfirm', method: 'post', data: data }) }


export const getfindByOrderAndCheckIn = params => { return request({ url: '/hotel/order/findByOrderAndCheckIn', method: 'get', params: params }) }





