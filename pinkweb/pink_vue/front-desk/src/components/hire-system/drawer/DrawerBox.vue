<template>
  <div>
    <el-button @click="drawer = true" type="primary" icon="el-icon-s-operation" circle class="drawer-btn"></el-button>
    <el-drawer :visible.sync="drawer" :with-header="false">
      <span>未施工，敬请期待！</span>
    </el-drawer>
  </div>
</template>

<script>
export default {
  name: "DrawerBox",
  data() {
    return {
      drawer: false,
    };
  }
}
</script>

<style scoped>
@import "../../../assets/css/hire-system/components/drawerbox.css";
</style>
