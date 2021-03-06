import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '数据概览', icon: 'dashboard' }
    }],

  },

  {
    path: '/dataControl',
    component: Layout,
    redirect: '/dataControl/article',
    name: 'dataControl',
    meta: { title: '数据管理', icon: 'example' },
    children: [
      {
        path: 'article',
        name: 'Article',
        component: () => import('@/views/dataControl/article/article'),
        meta: { title: '文章管理', icon: 'table' }
      },
      {
        path: 'comment',
        name: 'Comment',
        component: () => import('@/views/dataControl/comment/comment'),
        meta: { title: '评论管理', icon: 'tree' }
      },
      {
        path: 'category',
        name: 'Category',
        component: () => import('@/views/dataControl/category/category'),
        meta: { title: '分类管理', icon: 'tree' }
      },
      {
        path: 'tag',
        name: 'Tag',
        component: () => import('@/views/dataControl/tag/tag'),
        meta: { title: '标签管理', icon: 'tree' }
      }
    ]
  },
  {
    path: '/blogSetting',
    component: Layout,
    redirect: '/blogSetting/menu',
    name: 'dataControl',
    meta: { title: '博客管理', icon: 'example' },
    children: [
      {
        path: 'menu',
        name: 'Menu',
        component: () => import('@/views/blogSetting/menu/menu'),
        meta: { title: '菜单管理', icon: 'table' }
      },
      {
        path: 'setting',
        name: 'Setting',
        component: () => import('@/views/blogSetting/setting/setting'),
        meta: { title: '系统设置', icon: 'tree' }
      }
    ]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system/user',
    name: 'dataControl',
    meta: { title: '系统管理', icon: 'example' },
    children: [
      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/system/user/user'),
        meta: { title: '用户管理', icon: 'table' }
      },
      {
        path: 'role',
        name: 'Role',
        component: () => import('@/views/system/role/role'),
        meta: { title: '角色管理', icon: 'tree' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'hash', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
