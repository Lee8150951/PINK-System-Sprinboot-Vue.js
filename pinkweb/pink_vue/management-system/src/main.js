import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import store from './store'
import echarts from 'echarts'
import Vuex from 'vuex' //引入状态管理
import axios from 'axios'
import moment from 'moment'
// 公共样式
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/base.css'
Vue.prototype.$moment = moment
Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(Vuex) ;
Vue.prototype.$echarts = echarts
Vue.prototype.$http=axios

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
/* eslint-disable no-new */
new Vue({
  el: '#app',
  store:store,
  router,
  render: h => h(App)
})
