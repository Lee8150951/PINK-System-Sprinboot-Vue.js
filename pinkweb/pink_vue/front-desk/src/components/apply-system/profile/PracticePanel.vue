<template>
  <el-card shadow="hover" class="message-card practice-card">
    <ul class="practice">
      <li>
        <span class="practice-name">{{practice.c_finternship_enterprise}}</span>
        <span class="practice-time">{{$moment(practice.c_finternship_stime).format('YYYY-MM-DD')}} 至 {{$moment(practice.c_finternship_etime).format('YYYY-MM-DD')}}</span>
      </li>
      <li>
        <span class="practice-work">{{practice.c_finternship_job}}</span>
      </li>
    </ul>
    <ul class="practice">
      <li>
        <span class="practice-name">{{practice.c_sinternship_enterprise}}</span>
        <span class="practice-time">{{$moment(practice.c_sinternship_stime).format('YYYY-MM-DD')}} 至 {{$moment(practice.c_sinternship_etime).format('YYYY-MM-DD')}}</span>
      </li>
      <li>
        <span class="practice-work">{{practice.c_sinternship_job}}</span>
      </li>
    </ul>
  </el-card>
</template>

<script>
  export default {
    name: "PracticePanel",
    props: {
      practice: Object
    }
  }
</script>

<style scoped>
@import "../../../assets/css/apply-system/components/practicepanel.css";
</style>
