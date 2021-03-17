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
            <span class="right-title orange">'PINK'</span>
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
                <el-button type="warning" @click="onSubmit" class="sub-btn">登录</el-button>
              </div>
            </el-form>
          </div>
          <div class="another-log">
            <span class="left-span">
              <el-button type="text" class="another-btn" @click="turnRegister">点击注册</el-button>
            </span>
            <span class="right-span">
              <el-button type="text" class="another-btn" @click="turnAnother">切换身份登录...</el-button>
            </span>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  name: "hire_login",
  data() {
    return {
      form: {
        phone: '',
        password: '',
        userToken: "",
      }
    }
  },
  methods: {
     ...mapMutations(["changeLogin"]),
    onSubmit() {
      let v = this;
      if (this.phone === "" || this.password === "") {
        alert("账号或密码不能为空");
      } else {
        const _this = this;
        this.$http
          .post("http://localhost:8085/premise/hr_login", {
            data: {
              phone: this.form.phone,
              password: this.form.password,
            },
          })
          .then(function (res) {
            console.log(res.data);
            if (res.data == "账号或密码错误") {
              alert("账号或密码错误");
            } else {
              v.userToken = res.data.token;
              console.log(res.data.token);
              // 将用户token保存到vuex中
              v.changeLogin({ token: v.userToken });
              localStorage.setItem("phone", v.form.phone);
              v.$router.push({ path: "/hire/hire_home" });
              v.$message("登录成功");
            }
          })
          .catch(function (err) {
            console.log("err", err);
            v.$message("密码或用户名错误");
          });
      }
    },
    // onSubmit() {
    //   this.$router.push({
    //     path: '/index/hire_home',
    //     query: {
    //       phone: this.form.phone,
    //       password: this.form.password
    //     }
    //   })
    // },
    turnAnother() {
      this.$router.push({
        path: '/premise/apply_login'
      })
    },
    turnRegister() {
      this.$router.push({
        path: '/premise/hire_register'
      })
    }
  }
}
</script>

<style scoped>
@import "../../../assets/css/premise/premise-main.css";
</style>
