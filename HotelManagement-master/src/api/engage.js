import request from '@/utils/request'

// 查询预订列表
export const getengageList = data => { return request({ url: '/hotel/engage/queryMsgByMap', method: 'post', data: data }) }


export const deleteengage = data => { return request({ url: '/hotel/engage/delete', method: 'post', data: data }) }



export const fingdByTypeId = params => { return request({ url: '/hotel/availableRoom/fingdByTypeId', method: 'get', params: params }) }


export const findByMap = data => { return request({ url: '/hotel/availableRoom/findByMap', method: 'post', data: data }) }

