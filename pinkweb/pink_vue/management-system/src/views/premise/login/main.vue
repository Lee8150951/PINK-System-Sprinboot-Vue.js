<template>
  <div class="background">
    <el-container>
      <el-header height="200px" class="login_head">
        <img src="../../../assets/img/components/sign-in.png" alt="" class="sign_in">
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "main"
}
</script>

<style scoped>
@import "../../../assets/css/premise/login/login.css";
</style>
