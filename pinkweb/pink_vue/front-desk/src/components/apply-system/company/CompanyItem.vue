<template>
  <div @click="companyClick">
    <el-col :span="8">
      <div class="company-content">
        <li class="c-logo">
          <!-- <img :src="company.e_logo" alt="" class="c-logo-img"> -->
          <img :src="'http://localhost:8085/companyAvatar/'+company.e_logo" alt="" class="c-logo-img">
        </li>
        <li class="c-name">{{company.e_name}}</li>
        <li class="c-info">
          <span class="c-info-left">{{company.e_operationstatus}}</span>
          <span class="c-info-right">{{company.e_type}}</span>
        </li>
        <li class="c-post">
          <el-button plain class="c-post-btn">
            {{company.companyHrList[0].ehr_currentaccount}}个招聘岗位
          </el-button>
        </li>
      </div>
    </el-col>
  </div>
</template>

<script>
export default {
  name: "CompanyItem",
  props: {
    company: {
      type: Object
    }
  },
  methods: {
    companyClick() {
      // 携带索引跳转
      this.$router.push({
        path: "/index/company_detail",
        query: {
          e_id: this.company.e_id
        }
      })
    }
  }
}
</script>

<style scoped>
@import "../../../assets/css/apply-system/components/companyitem.css";
</style>
