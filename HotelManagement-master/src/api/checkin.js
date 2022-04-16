import request from '@/utils/request'

// 录入住宿人信息
export const addcheckin = data => { return request({ url: '/hotel/checkin/addCheckInMsg', method: 'post', data: data }) }

