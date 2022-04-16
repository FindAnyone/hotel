import request from '@/utils/request'
// 职位查询
export const getPositionList = data => { return request({ url: '/hotel/position/findAllPosition', method: 'post', data: data }) }

// 职位查询分页
export const getPositionPageList = data => { return request({ url: '/hotel/position/positionList', method: 'post', data: data }) }

// 职位新增
export const addPosition = data => { return request({ url: '/hotel/position/addPosition', method: 'post', data: data }) }

// 职位修改
export const updatePosition = data => { return request({ url: '/hotel/position/updatePosition', method: 'post', data: data }) }

// 职位删除
export const deletePosition = data => { return request({ url: '/hotel/position/delPosition', method: 'post', data: data }) }