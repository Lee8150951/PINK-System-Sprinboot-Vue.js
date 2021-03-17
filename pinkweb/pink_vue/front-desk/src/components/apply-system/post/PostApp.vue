<template>
  <div class="post-item" @click="postClick">
    <el-row>
      <el-col :span="12">
        <div class="header-content">
          <li class="job-name" id="name">{{post.r_name}}</li>
          <li class="job-info">
            <span class="salary">{{post.r_salary}}</span>
            <span class="experience">{{post.r_experience}}</span>
            <span class="degree">{{post.r_education}}</span>
            <span class="address">{{post.r_address}}</span>
          </li>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="header-content">
          <li class="company-name">{{post.companyHr.company.e_name}}</li>
          <li class="company-info">
            <span class="type">{{post.companyHr.company.e_type}}</span>
            <span class="state">{{post.companyHr.company.e_operationstatus}}</span>
            <span class="employees">{{post.companyHr.company.e_size}}</span>
          </li>
        </div>
      </el-col>
      <el-col :span="4">
        <div class="header-content">
          <div class="company-logo">
            <!-- <img :src="post.logo" alt="logo" class="company-logo-img"> -->
            <img :src="'http://localhost:8085/companyAvatar/'+post.companyHr.company.e_logo" alt="" class="company-logo-img">
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "PostApp",
  props: {
    post: {
      type: Object
    }
  },
  methods: {
      postClick() {
        // 携带索引跳转
        this.$router.push({
          path: '/index/position_detail',
          query: {
            index: this.post.r_id,
            name: this.post.r_name,
            company_name: this.post.companyHr.company.e_name
          }
        })
        location.reload();
      }
  }
}
</script>

<style scoped>
@import "../../../assets/css/apply-system/components/postapp.css";
</style>
