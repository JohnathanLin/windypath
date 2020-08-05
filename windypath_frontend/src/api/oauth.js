import request from '@/utils/request'

const Qs = require('qs')

export function login(username, password) {
  let jsonData = {
    'username': username,
    'password': password
  }
  return request({
    url: '/windypath/oauth/login',
    method: 'post',
    data: Qs.stringify(jsonData)
  })
}

export function getInfo(username) {
  let jsonData = {
    'username': username
  }
  return request({
    url: '/windypath/oauth/info',
    method: 'post',
    data: Qs.stringify(jsonData)
  })
}

export function logout(username) {
  let jsonData = {
    'username': username
  }
  return request({
    url: '/windypath/oauth/logout',
    method: 'post',
    data: Qs.stringify(jsonData)
  })
}
