<template>
  <el-row class="hr-li">
    <el-col :span="5">
      <div class="avatar-content">
        <img :src="hr.avatar" alt="" class="avatar-img">
      </div>
    </el-col>
    <el-col :span="19">
      <div class="avatar-content">
        <el-tooltip class="item" effect="dark" :content="hr.place" placement="top">
          <span class="avatar-b">
            <span class="b-name">{{hr.name}}</span>
            <span class="b-job">{{hr.ehr_position}}</span>
          </span>
        </el-tooltip>
        <div class="b-state">
          正在招聘 {{hr.ehr_currentaccount}} 个职位
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "HrItem",
  props: {
    hr: {
      type: Object
    }
  }
}
</script>

<style scoped>
@import "../../../assets/css/apply-system/components/hritem.css";
</style>
