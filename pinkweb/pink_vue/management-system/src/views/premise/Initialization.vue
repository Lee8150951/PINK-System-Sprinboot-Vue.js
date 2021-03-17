<template>
  <div class="background">
    <el-container>
      <el-header height="200px" class="login_head">
        <img src="../../assets/img/logo/logo_05.png" alt="" class="login_logo">
      </el-header>
      <el-main>
        <el-row>
          <el-col :span="12">
            <div class="left_content content">
              <div class="decorate">
                <i class="el-icon-suitcase"></i>
              </div>
              <div class="content_title">
                <p>Company</p>
                <h3>公司管理</h3>
                <h4>
                  &nbsp;&nbsp;&nbsp;
                  Use the <strong>PINKPanel</strong> to manage the members and related info.
                </h4>
              </div>
              <el-row class="content_button">
                <el-col :span="21">
                  <button class="el-button left_button el-button--success"
                          path="/login/company_login"
                          style="border-radius: 0; padding: 10px 30%"
                          @click="buttonClick($event)">登录</button>
                </el-col>
              </el-row>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="right_content content">
              <div class="decorate">
                <i class="el-icon-key"></i>
              </div>
              <div class="content_title">
                <p>Admin</p>
                <h3>后台管理</h3>
                <h4>
                  Use the <strong>PINKPanel</strong> to manage the companies, users and related info.
                </h4>
              </div>
              <el-row class="content_button">
                <el-col :span="21">
                  <button class="el-button left_button el-button--warning"
                          style="border-radius: 0; padding: 10px 30%"
                          path="/login/admin_login"
                          @click="buttonClick($event)">登录</button>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>
      </el-main>
      <el-footer></el-footer>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: "login",
    methods: {
      buttonClick(e) {
        let path = e.target.getAttribute("path")
        this.$router.push(path)
      }
    }
  }
</script>

<style scoped>
@import "../../assets/css/premise/Initialization.css";
</style>
