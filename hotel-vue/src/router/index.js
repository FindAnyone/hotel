import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}


export default new Router({
    routes: [
        {
            path: "/",
            redirect: "/roomlist",

        },
        {
            path: "/login",
            component: () => import('@/views/Login'),
            name: "",

        },
        {
            path: "/register",
            component: () => import('@/views/Register'),
            name: "",

        },
        {
            path: "/roomlist",
            component: () => import('@/views/room/roomList'),
            name: "",
        },
        {
            path: "/roomdetails",
            component: () => import('@/views/room/Details'),
            name: "",
        },
        {
            path: "/userinfo",
            component: () => import('@/views/userinfo/userinfo'),
            name: "",
        },
        {
            path: "/historyorder",
            component: () => import('@/views/order/historyorder'),
            name: "",
        },
        {
            path: "/order",
            component: () => import('@/views/order/order'),
            name: "",
        },
        {
            path: "/engage",
            component: () => import('@/views/engage/engage'),
            name: "",
        },
        {
            path: "/userinfo",
            component: () => import('@/views/userinfo/userinfo'),
            name: "",
        }

        
    ]
})

