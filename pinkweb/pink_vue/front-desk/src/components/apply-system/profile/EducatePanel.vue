<template>
  <el-card shadow="hover" class="message-card edu-card">
    <ul class="edu">
      <li>
        <span class="edu-name">{{edu.c_fschool}}</span>
        <span class="edu-time">{{$moment(edu.c_ftime).format('YYYY-MM-DD')}}</span>
      </li>
      <li>
        <span class="edu-depart">{{edu.c_fmajor}}</span>
        <span class="edu-work">{{edu.c_fdegree}}</span>
      </li>
    </ul>

    <ul class="edu">
      <li>
        <span class="edu-name">{{edu.c_sschool}}</span>
        <span class="edu-time">{{$moment(edu.c_stime).format('YYYY-MM-DD')}}</span>
      </li>
      <li>
        <span class="edu-depart">{{edu.c_smajor}}</span>
        <span class="edu-work">{{edu.c_sdegree}}</span>
      </li>
    </ul>
  </el-card>
</template>

<script>
export default {
  name: "EducatePanel",
  props: {
    edu: Object
  }
}
</script>

<style scoped>
@import "../../../assets/css/apply-system/components/educatepanel.css";
</style>
