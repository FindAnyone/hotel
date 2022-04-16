import request from '@/utils/request'
// 客户查询
export const getempList = data => { return request({ url: '/hotel/user/queryUserList', method: 'post', data: data }) }

// 新增客户
export const addemp = data => { return request({ url: '/hotel/user/insertUser', method: 'post', data: data }) }

// 根据客户ID查询客户信息
export const getEmployeeById = params => { return request({ url: '/hotel/user/queryMsgById' , method: 'get', params: params }) }

// 修改客户信息
export const update = data => { return request({ url: '/hotel/user/updateMsg', method: 'post', data: data }) }

// 重置密码
export const resetPassword = employeeId => { return request({ url: '/HotelManagement/json/users/resetPassword?employeeId=' + employeeId, method: 'put', params: employeeId }) }

// 修改密码
export const updatePassword = params => { return request({ url: '/HotelManagement/json/users/updatePassword', method: 'put', params: params }) }

// 删除客户
export const delemp = data => { return request({ url: '/hotel/user/deleteUser', method: 'post', data: data }) }
