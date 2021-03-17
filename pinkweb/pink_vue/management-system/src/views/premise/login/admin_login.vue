<template>
  <login-body>
    <img src="../../../assets/img/back/admin_back.jpg" alt="" class="login-back" slot="background">
    <div class="form-base" slot="form-base">
      <form action="">
        <el-input placeholder="用户名" v-model="admin_account" clearable></el-input>
        <el-input placeholder="请输入密码" v-model="admin_pwd" show-password></el-input>
        <el-button type="success" class="form-button" @click="onSubmit()">登录</el-button>
      </form>
    </div>
    <!-- <el-button type="text" class="tips" slot="tips">还没注册？请点击</el-button> -->
  </login-body>
</template>

<script>
import { mapMutations } from "vuex";
import LoginBody from "../../../components/premise/LoginBody";
export default {
  name: "admin_login",
  components: {LoginBody},
  data() {
    return {
      admin_account: '',
      admin_pwd: ''
    }
  },
  methods:{
    ...mapMutations(["changeLogin"]),
    onSubmit() {
      let v = this;
      if (this.admin_account === "" || this.admin_pwd === "") {
        alert("账号或密码不能为空");
      } else {
        const _this = this;
        this.$http
          .post("http://localhost:8085/premise/admin_login", {
            data: {
              admin_account: this.admin_account,
              admin_pwd: this.admin_pwd,
            },
          })
          .then(function (res) {
            console.log(res.data);
            if (res.data === "账号或密码错误" || res.data === "用户不存在") {
              alert("账号或密码错误");
            } else {
              v.userToken = res.data.token;
              console.log(res.data.token);
              // 将用户token保存到vuex中
              v.changeLogin({ token: v.userToken });
              localStorage.setItem("admin_account", v.admin_account);
              v.$router.push({ path: "/admin/admin_home" });
              v.$message("登录成功");
            }
          })
          .catch(function (err) {
            console.log("err", err);
            v.$message("密码或用户名错误");
          });
      }
    },
  }
}
</script>

<style scoped>
@import "../../../assets/css/premise/login/loginbody.css";
</style>
