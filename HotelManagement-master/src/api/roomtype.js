import request from '@/utils/request'
// 查询客房类型列表
export const getRoomTypeList = params => { return request({ url: '/hotel/roomType/roomTypeList', method: 'get', params: params }) }

// 新增客房类型列表
export const addRoomType = data => { return request({ url: '/hotel/roomType/roomTypeAdd', method: 'post', data: data }) }

// 根据ID查询客房类型列表
export const getRoomTypeById = typeId => { return request({ url: '/hotel/roomType/getRoomTypeById?typeId=' + typeId, method: 'get', params: typeId }) }

// 查询所有客房类型简要信息列表
export const getAllRoomTypeList = params => { return request({ url: '/hotel/roomType/roomTypeList', method: 'get', params: params }) }

// 修改客房类型列表
export const updateRoomType = data => { return request({ url: '/hotel/roomType/roomTypeupdate', method: 'post', data: data }) }

// 删除客房类型列表
export const delRoomType = typeId => { return request({ url: '/hotel/roomType/deleteRoomTypeById?typeId=' + typeId, method: 'get', params: typeId }) }