import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
          path: '/',
          redirect: '/login'
        },
        {
          path: '/',
          component: () => import(/* webpackChunkName: "home" */ '../components/common/Home.vue'),
          meta: { title: 'Home' },
          children: [
              {
                path: '/dashboard',
                component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Dashboard.vue'),
                meta: { title: '系统首页' }
              },
              {
                path: '/employee',
                component: () => import(/* webpackChunkName: "table" */ '../pub/employee/index'),
                meta: { title: '员工管理' }
              }, 
              {
                path: '/users',
                component: () => import(/* webpackChunkName: "table" */ '../pub/users/users'),
                meta: { title: '用户管理' }
              }, 
              {
                path: '/room',
                component: () => import(/* webpackChunkName: "form" */ '../pub/room/index'),
                meta: { title: '客房管理' }
              },
              {
                path: '/engage',
                component: () => import(/* webpackChunkName: "table" */ '../pub/engage/index'),
                meta: { title: '预订订单管理' }
              }, 
              {
                path: '/order',
                component: () => import(/* webpackChunkName: "table" */ '../pub/order/order'),
                meta: { title: '订单管理' }
              }, 
              {
                path: '/historyorder',
                component: () => import(/* webpackChunkName: "table" */ '../pub/order/history'),
                meta: { title: '历史订单管理' }
              }, 
              {
                path: '/cropper',
                component: () => import(/* webpackChunkName: "upload" */ '../components/page/Cropper.vue'),
                meta: { title: '文件上传(可裁剪)' }
              },
              {
                path: '/updatePwd',
                component: () => import(/* webpackChunkName: "donate" */ '../components/page/updatePwd.vue'),
                meta: { title: '修改密码' }
              }
          ]
        },
        {
          path: '/login',
          component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
          meta: { title: '登录' }
        },
        {
          path: '*',
          redirect: '/404'
        }
    ]
});
