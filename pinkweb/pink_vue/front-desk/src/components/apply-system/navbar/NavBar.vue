<template>
  <nav class="navbar">
    <div class="item-top">
      <div class="bar-item">
        <el-tooltip class="item" effect="dark" content="提示文字" placement="left">
          <el-button type="text"><i class="el-icon-setting"></i></el-button>
        </el-tooltip>
      </div>
      <div class="bar-item">
        <el-tooltip class="item" effect="dark" content="提示文字" placement="left">
          <el-button type="text"><i class="el-icon-star-off"></i></el-button>
        </el-tooltip>
      </div>
      <div class="bar-item">
        <el-tooltip class="item" effect="dark" content="提示文字" placement="left">
          <el-button type="text"><i class="el-icon-folder"></i></el-button>
        </el-tooltip>
      </div>
      <div class="bar-item">
        <el-tooltip class="item" effect="dark" content="提示文字" placement="left">
          <el-button type="text"><i class="el-icon-pie-chart"></i></el-button>
        </el-tooltip>
      </div>
    </div>
    <div class="item-bottom">
      <div class="bar-item-text">
        <el-button type="text" class="bar-btn"><i>客服</i></el-button>
      </div>
      <div class="bar-item-text">
        <el-button type="text" class="bar-btn"><i>反馈</i></el-button>
      </div>
      <div class="bar-item-text">
        <el-button type="text" class="bar-btn"><i>微信</i></el-button>
      </div>
      <div class="bar-item-text">
        <el-button type="text" class="bar-btn"><i>APP</i></el-button>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "NavBar"
}
</script>

<style scoped>
@import "../../../assets/css/apply-system/components/navbar.css";
</style>
