<template>
  <div>
    <el-row>
      <el-col :span="6"><div class="grid-content"></div></el-col>
      <el-col :span="4">
        <div class="content">
          <slot name="background"></slot>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="content">
          <slot name="form-base"></slot>
          <slot name="tips"></slot>
        </div>
      </el-col>
      <el-col :span="6"><div class="grid-content"></div></el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "RegisterBody"
}
</script>

<style scoped>
@import "../../assets/css/admin/components/registerbody.css";
</style>
