<template>
  <login-body>
    <img src="../../../assets/img/back/company_back.jpg" alt="" class="login-back" slot="background">
    <div class="form-base" slot="form-base">
      <form action="">
        <el-input placeholder="工号" v-model="number" clearable></el-input>
        <el-input placeholder="请输入密码" v-model="password" show-password></el-input>
        <el-button type="success" class="form-button" @click="onSubmit()">登录</el-button>
      </form>
    </div>
    <!-- <el-button type="text" class="tips" slot="tips">没有账号？请联系我们</el-button> -->
  </login-body>
</template>

<script>
import { mapMutations } from "vuex";
import LoginBody from "../../../components/premise/LoginBody";
export default {
  name: "company_login",
  components: {LoginBody},
  data() {
    return {
      number: '',
      password: '',
      userToken: "",
    }
  },
  methods:{
    ...mapMutations(["changeLogin"]),
    onSubmit() {
      let v = this;
      if (this.number === "" || this.password === "") {
        alert("账号或密码不能为空");
      } else {
        const _this = this;
        this.$http
          .post("http://localhost:8085/premise/candidate_login", {
            data: {
              phone: this.number,
              password: this.password,
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

              localStorage.setItem("phone", v.number);
              console.log(localStorage.getItem('phone'))
              v.$router.push({ path: "/company/index" });
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
