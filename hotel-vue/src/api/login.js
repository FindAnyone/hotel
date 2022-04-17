import request from '@/utils/request'

//登录
export const getLogin = params => { return request({ url: '/hotel/user/FrontDesklogin', method: 'get', params: params }) }

//密码登录
export const getPassLogin = data => { return request({ url: '/hotel/user/login', method: 'post', data: data }) }
//验证码
export const getCode = params => { return request({ url: '/hotel/getCode',  method: 'get', params: params }) }