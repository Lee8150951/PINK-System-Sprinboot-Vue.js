<template>
  <div>
    <el-menu default-active="1" class="el-menu-vertical-demo" :collapse="$store.state.isCollapse">
      <el-row index="0" disabled class="main_menu">
        <el-col :span="18" class="main_menu_left">
          Main Menu
        </el-col>
        <el-col :span="6" class="main_menu_right">
          <img src="../../../assets/img/components/contraction.svg" alt="contraction" class="contraction">
        </el-col>
      </el-row>
      <slot></slot>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "SideBar"
}
</script>

<style scoped>
@import "../../../assets/css/admin/components/sidebar.css";
</style>
