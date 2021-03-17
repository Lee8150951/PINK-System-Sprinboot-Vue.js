<template>
  <div class="back-body">
    <el-row class="main">
      <el-col :span="9">
        <div class="left-content">
          <img src="../../../assets/img/background/premise/hire/hire_login.png" alt="bg" class="left-bg">
        </div>
      </el-col>
      <el-col :span="15">
        <div class="right-content">
          <div class="title">
            <span class="left-title">Log in</span>
            <span class="right-title blue">'PINK'</span>
          </div>
          <div class="form">
            <el-form ref="form" :model="form" label-width="80px">
              <div class="form-item">
                <el-input v-model="form.phone" placeholder="请输入账号"></el-input>
              </div>
              <div class="form-item">
                <el-input v-model="form.password" placeholder="请输入密码" show-password></el-input>
              </div>
              <div class="form-item">
                <el-button type="primary" @click="onSubmit" class="sub-btn">登录</el-button>
              </div>
            </el-form>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "hire_register",
  data() {
    return {
      form: {
        phone: '',
        password: ''
      }
    }
  },
  methods: {
    onSubmit() {
      this.$router.push({
        path: '/index/hire_home',
        query: {
          phone: this.form.phone,
          password: this.form.password
        }
      })
    },
    turnAnother() {
      this.$router.push({
        path: '/premise/apply_login'
      })
    }
  }
}
</script>

<style scoped>
@import "../../../assets/css/premise/premise-main.css";
</style>
