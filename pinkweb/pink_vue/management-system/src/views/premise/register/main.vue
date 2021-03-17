<template>
  <div class="background">
    <el-container>
      <el-header height="150px" class="login_head">
        <img src="../../../assets/img/components/register.png" alt="" class="register">
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
@import "../../../assets/css/premise/register/register.css";
</style>
