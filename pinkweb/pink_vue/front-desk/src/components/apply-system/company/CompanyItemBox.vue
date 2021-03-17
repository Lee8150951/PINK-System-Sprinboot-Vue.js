<template>
  <div @click="companyClick">
    <el-col :span="6">
      <div class="box-content">
        <el-row class="box-head">
          <el-col :span="8">
            <div class="company-box-content">
              <!-- <img :src="company.logo" alt="logo" class="c-logo"> -->
              <img :src="'http://localhost:8085/companyAvatar/'+company.e_logo" alt="" class="c-logo">
            </div>
          </el-col>
          <el-col :span="16">
            <div class="grid-content">
              <div class="company-title">{{showName}}</div>
              <div class="company-info">
                {{company.e_operationstatus}} | {{company.e_type}}</div>
            </div>
          </el-col>
        </el-row>
        <el-row class="box-footer">
          <el-col :span="24">
            <div class="position-content">
              热招岗位<span class="position-info">{{company.companyHrList[0].ehr_currentaccount}}</span>个 |   企业规模 {{company.e_size}}
            </div>
          </el-col>
        </el-row>
      </div>
    </el-col>
  </div>
</template>

<script>
export default {
  name: "CompanyItemBox",
  data() {
    return {
      showName: ''
    }
  },
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
    },
    showNameFun() {
      // 控制公司名字长度
      const c_name = this.company.e_name
      if (c_name.length > 7) {
        this.showName = c_name.slice(0, 8) + "..."
      } else {
        this.showName = c_name
      }
    }
  },
  mounted() {
    this.showNameFun()
  }
}
</script>

<style scoped>
@import "../../../assets/css/apply-system/components/companyitembox.css";
</style>
