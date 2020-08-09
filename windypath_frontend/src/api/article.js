import request from '@/utils/request'

const Qs = require('qs')

/**
 * 根据分页查询文章
 *
 * @param page 页数
 * @param size 每页行数
 * @param model 搜素条件
 */
export function findArticleByPage(page, size, model) {
  let categoryIdListStr = model.categoryIdList.join()
  let jsonData = {
    page: page,
    size: size,
    titleCn: model.titleCn,
    categoryIdList: categoryIdListStr,
    status: model.status
  }
  return request({
    url: '/windypath/admin/article/findByPage',
    method: 'post',
    data: Qs.stringify(jsonData)
  })
}

/**
 * 保存文章
 *
 * @param form
 */
export function saveArticle(form) {
  let jsonData = {
    titleCn: form.titleCn,
    titleEn: form.titleEn,
    status: form.status,
    // todo categoryId categoryId: form.categoryId,
    // todo categoryId tagList: tagListStr,
    allowComment: form.allowComment,
    contentCn: form.contentCn,
    contentEn: form.contentEn
  }
  return request({
    url: '/windypath/admin/article/save',
    method: 'post',
    data: Qs.stringify(jsonData)
  })
}

/**
 * 编辑文章
 *
 * @param form 文章信息
 */
export function editArticle(form) {
  let jsonData = {
    id: form.id,
    titleCn: form.titleCn,
    titleEn: form.titleEn,
    status: form.status,
    // todo categoryId categoryId: form.categoryId,
    // todo categoryId tagList: tagListStr,
    allowComment: form.allowComment,
    contentCn: form.contentCn,
    contentEn: form.contentEn
  }
  return request({
    url: '/windypath/admin/article/edit',
    method: 'post',
    data: Qs.stringify(jsonData)
  })
}

/**
 * 根据id获取文章
 *
 * @param id
 */
export function getArticleById(id) {
  let jsonData = {
    id: id
  }
  return request({
    url: '/windypath/admin/article/getById',
    method: 'post',
    data: Qs.stringify(jsonData)
  })
}

/**
 * 根据id删除文章
 *
 * @param id id
 */
export function deleteArticle(id) {
  let jsonData = {
    id: id
  }
  return request({
    url: '/windypath/admin/article/delete',
    method: 'post',
    data: Qs.stringify(jsonData)
  })

}
