<template>
  <register-body>
    <img src="../../../assets/img/back/company_reg.jpg" alt="" slot="background" class="reg-back">
    <div class="form-body" slot="form-base">
      <el-form ref="form" label-width="80px">
        <div class="info">
          <h2>账号信息</h2>
          <el-input placeholder="账号" v-model="username" autocomplete="off" clearable></el-input>
          <el-input placeholder="密码" v-model="password" autocomplete="off" show-password></el-input>
          <el-input placeholder="确认密码" v-model="re_password" autocomplete="off" show-password></el-input>
        </div>
        <div style="margin-top: 35px">
          <el-button type="success" class="right-button">完成</el-button>
        </div>
      </el-form>
    </div>
  </register-body>
</template>

<script>
import RegisterBody from "../../../components/premise/RegisterBody";
export default {
  name: "company_register",
  components: {RegisterBody},
  data() {
    return {
      username: '',
      password: '',
      re_password: ''
    }
  }
}
</script>

<style scoped>
@import "../../../assets/css/premise/register/registerbody.css";
</style>
