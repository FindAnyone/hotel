import request from '@/utils/request'

//查询历史订单

export const getengageorder = params => { return request({ url: '/hotel/engage/queryById',  method: 'get', params: params }) }


export const getaddengage = data => { return request({ url: '/hotel/engage/addEngageMsg',  method: 'post', data: data }) }

//
export const getupdateengage = data => { return request({ url: '/hotel/engage/updateEngageMsg',  method: 'post', data: data }) }

export const deleteengage = data => { return request({ url: '/hotel/engage/delete', method: 'post', data: data }) }

//查询剩余房间数
export const getfindavailableroom = data => { return request({ url: '/hotel/engage/findByDateCount', method: 'post', data: data }) }

export const getUserMsgById = params => { return request({ url: '/hotel/user/queryMsgById',  method: 'get', params: params }) }







