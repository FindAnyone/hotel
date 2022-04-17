import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import common from './common'
import store from 'store'

import 'font-awesome/css/font-awesome.min.css'
import qs from 'qs'
import axios from 'axios'

Vue.use(ElementUI)
Vue.config.productionTip = false
// 将模块挂载到Vue实例中
Vue.prototype.$axios = axios
Vue.prototype.$qs = qs;
Vue.prototype.$getCurDate = common.getCurDate;
Vue.prototype.$setSessionStorage = common.setSessionStorage;
Vue.prototype.$getSessionStorage = common.getSessionStorage;
Vue.prototype.$removeSessionStorage = common.removeSessionStorage;
Vue.prototype.$setLocalStorage = common.setLocalStorage;
Vue.prototype.$getLocalStorage = common.getLocalStorage;
Vue.prototype.$removeLocalStorage = common.removeLocalStorage;


new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
