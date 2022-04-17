
import request from '@/utils/request'

export const getuserbyid = params => { return request({ url: '/hotel/user/queryMsgById', method: 'get', params: params }) }


export const updateuser = data => { return request({ url: '/hotel/user/updateMsg', method: 'post', data: data }) }

export const delImg = roomImg => { return request({ url: '/hotel/FileUpload/deleteFile?roomImg=' + roomImg, method: 'get', params: roomImg }) }
