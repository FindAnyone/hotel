import request from '@/utils/request'

//房间类型
export const roomTypeList = data => { return request({ url: '/hotel/roomType/roomTypeList', method: 'post', data: data }) }

//可预订房间查询
export const findEngageRoom = data => { return request({ url: '/hotel/availableRoom/findEngageRoom', method: 'post', data: data }) }

//查询房间详细信息
export const getRoomById = params => { return request({ url: '/hotel/room/getRoomById', method: 'get', params: params }) }

