import request from '@/utils/request'
// 员工查询
export const getempList = data => { return request({ url: '/hotel/emp/findEmpList', method: 'post', data: data }) }

// 新增员工
export const addemp = data => { return request({ url: '/hotel/emp/addEmp', method: 'post', data: data }) }

// 根据员工ID查询员工信息
export const getEmployeeById = employeeId => { return request({ url: '/hotel/emp/findById', method: 'get', params: employeeId }) }

// 修改员工信息
export const update = data => { return request({ url: '/hotel/emp/updateEmp', method: 'post', data: data }) }

// 修改密码
export const updatePassword = params => { return request({ url: '/HotelManagement/json/employee/updatePassword', method: 'put', params: params }) }

// 删除员工
export const delemp = data => { return request({ url: '/hotel/emp/delEmp', method: 'post', data: data }) }

