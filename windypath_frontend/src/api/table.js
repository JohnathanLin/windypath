import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/vue-admin-template/table/list',
    method: 'get',
    params
  })
}
export function testConnnect() {
  return request({
    url: '/hello',
    method: 'get'
  })
}
