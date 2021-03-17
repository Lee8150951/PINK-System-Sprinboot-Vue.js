import Vue from 'vue'
import Vuex from 'vuex'
import actions from "./actions"
import getters from "./getters"
import mutations from "./mutations"

Vue.use(Vuex)
const store = new Vuex.Store({
  // 保存状态
  state: {
    token: localStorage.getItem('token') ? localStorage.getItem('token') : ''
  },
  // 定义方法
  mutations: {
    // 修改token，并将token存入localStorage
    changeLogin (state, user) {
        state.token = user.token;
        localStorage.setItem('token', user.token);
    }
},
  // 异步操作
  actions,
  // 数据处理
  getters
})

export default store
