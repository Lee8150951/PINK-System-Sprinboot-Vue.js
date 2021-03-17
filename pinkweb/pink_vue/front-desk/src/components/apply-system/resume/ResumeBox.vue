<template>
  <el-col :span="8" class="resume-body">
    <div class="resume-content">
      <div class="img-col">
        <slot name="resume-img"></slot>
      </div>
      <div class="resume-name">
        <slot name="resume-name"></slot>
      </div>
    </div>
  </el-col>
</template>

<script>
export default {
  name: "ResumeBox",
}
</script>

<style scoped>
@import "../../../assets/css/apply-system/components/resumebox.css";
</style>
