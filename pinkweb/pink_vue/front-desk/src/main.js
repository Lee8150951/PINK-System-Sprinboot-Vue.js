import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import echarts from 'echarts'
import Vuex from 'vuex' //引入状态管理
import axios from 'axios'

// 公共样式
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/base.css'
import moment from 'moment'
import store from './store'
Vue.prototype.$moment = moment
Vue.prototype.$http=axios
Vue.prototype.$echarts = echarts
Vue.use(ElementUI)
Vue.use(Vuex) ;
/* eslint-disable no-new */

Vue.config.productionTip = false


axios.interceptors.request.use(
  config => {
    if (localStorage.getItem('token')) {
      config.headers.token = localStorage.getItem('token');
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  });
new Vue({
  el: '#app',
  store:store,
  router,
  render: h => h(App)
})
