<template>
  <div class="post-item" @click="postClick">
    <el-row>
      <el-col :span="12">
        <div class="header-content">
          <li class="job-name" id="name">{{post.r_name}}</li>
          <li class="job-info">
            <span class="r_salary">{{post.r_salary}}</span>
            <span class="r_experience">{{post.r_experience}}</span>
            <span class="r_education">{{post.r_education}}</span>
            <span class="r_address">{{post.r_address}}</span>
          </li>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="header-content">
          <li class="company-name">{{showName}}</li>
          <li class="company-info">
            <span class="e_type">{{post.companyHr.company.e_type}}</span>
            <span class="e_operationstatus">{{post.companyHr.company.e_operationstatus}}</span>
            <span class="e_scale">{{post.companyHr.company.e_size}}人</span>
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
    <el-row class="bottom-content">
      <el-col :span="20">
        <div class="footer-content">
          <el-tag v-for="item in (post.r_tag).split(',')" :key="item" type="info" size="mini" style="margin-left: 12px">
            {{item}}
          </el-tag>
        </div>
      </el-col>
      <el-col :span="4">
        <div class="footer-content">
          <span class="welfare">
            {{post.welfare}}
          </span>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "PostItem",
    props: {
      post: {
        type: Object
      }
    },
    data() {
      return {
        showName: ''
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
      },
      showCompanyName() {
        let name = this.post.companyHr.company.e_name
        if (name.length > 12) {
          this.showName = name.slice(0, 12) + '...'
        } else {
          this.showName = name
        }
      }
    },
    mounted() {
      this.showCompanyName()
    }
  }
</script>

<style scoped>
@import "../../../assets/css/apply-system/components/postitem.css";
</style>
