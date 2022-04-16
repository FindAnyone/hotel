import request from '@/utils/request'

// 查询客房列表
// export const getroomList = params => { return request({ url: '/hotel/room/roomAllList', method: 'get', params: params }) }

// 组合查询客房列表
export const getroomAllList = data => { return request({ url: '/hotel/room/roomList', method: 'post', data: data }) }
// 新增客房列表
export const addroomx = data => { return request({ url: '/hotel/room/roomAdd', method: 'post', data: data }) }

// 根据ID查询客房列表
export const getroomById = roomId => { return request({ url: '/hotel/room/getRoomById?roomId=' + roomId, method: 'get', params: roomId }) }

// 修改客房列表
export const updateroom = data => { return request({ url: '/hotel/room/roomupdate', method: 'post', data: data }) }

// 删除客房列表
export const delroom = roomId => { return request({ url: '/hotel/room/deleteRoomById?roomId=' + roomId, method: 'get', params: roomId }) }

export const delImg = roomImg => { return request({ url: '/hotel/FileUpload/deleteFile?roomImg=' + roomImg, method: 'get', params: roomImg }) }