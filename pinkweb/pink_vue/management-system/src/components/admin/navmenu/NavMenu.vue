<template>
  <div>
    <el-menu mode="horizontal">
      <el-menu-item>
        <img src="../../../assets/img/logo/logo_02.png" alt="LOGO" class="logo">
      </el-menu-item>
<!--      <el-menu-item>-->
<!--        <el-button type="text" @click="contraction">-->
<!--          <img src="../../assets/img/components/contraction.svg" alt="contraction" class="contraction">-->
<!--        </el-button>-->
<!--      </el-menu-item>-->
      <el-menu-item class="occupied"></el-menu-item>
      <el-menu-item index="1">
        <el-badge class="item">
          <i class="el-icon-setting"></i>
        </el-badge>
      </el-menu-item>
      <el-menu-item index="2">
        <el-badge is-dot class="item">
          <i class="el-icon-bell"></i>
        </el-badge>
      </el-menu-item>
      <el-menu-item index="3">
        <el-badge is-dot class="item">
          <i class="el-icon-message"></i>
        </el-badge>
      </el-menu-item>
      <el-menu-item class="user" @click="getUserJump('/admin/admin_profile')">
        <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "NavMenu",
  data() {
    return {
      isCollapse: true
    };
  },
  methods: {
    contraction() {
      this.$store.commit("contraction")
    },
    getUserJump(e) {
      this.$router.push(e)
    }
  }
}
</script>

<style scoped>
@import "../../../assets/css/admin/components/navmenu.css";
</style>
