<template>
  <div class="home-body">
    <div class="index-background">
      <span class="mask"></span>
      <div class="main-title_1">Hi, {{name}}</div>
      <div class="main-title_2">欢迎使用「PINK」</div>
    </div>
    <el-row class="title">
      <el-col :span="8"><div class="grid-content"></div></el-col>
      <el-col :span="8">
        <div class="title-content">
          - 热门岗位 -
        </div>
      </el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
    </el-row>
    <el-row class="post">
      <el-col :span="3"><div class="grid-content"></div></el-col>
      <el-col :span="17">
        <post-item v-for="post in posts" :key="post.index" :post="post"></post-item>
      </el-col>
    </el-row>
    <el-row class="more-btn">
      <el-col :span="8"><div class="grid-content"></div></el-col>
      <el-col :span="8">
          <el-button type="warning" class="wide-button" @click="morePosition()">查看更多</el-button>
      </el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
    </el-row>
    <el-row class="title">
      <el-col :span="8"><div class="grid-content"></div></el-col>
      <el-col :span="8">
        <div class="title-content">
          - 热门企业 -
        </div>
      </el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
    </el-row>
    <el-row class="post">
      <el-col :span="3"><div class="grid-content"></div></el-col>
      <el-col :span="17">
        <el-row class="companies">
          <company-item v-for="company in companies" :key="company.index" :company="company">
            <!-- <img :src="avatarUrl + company.e_logo"> -->
           <div slot="c-name">{{company.e_name}}</div>
            <span slot="c-state">{{company.e_operationstatus}}</span>
            <span slot="c-type">{{company.e_type}}</span>
            <span slot="c-num">{{company.companyHrList[0].ehr_currentaccount}}</span>
          </company-item>
        </el-row>
      </el-col>
    </el-row>
    <el-row class="more-btn">
      <el-col :span="8"><div class="grid-content"></div></el-col>
      <el-col :span="8">
        <el-button type="warning" class="wide-button" @click="moreCompany()">查看更多</el-button>
      </el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="24"><div class="occupied-content"></div></el-col>
    </el-row>
  </div>
</template>

<script>
// 导入PostItem组件
import PostItem from "../../components/apply-system/post/PostItem";
// 导入CompanyItem组件
import CompanyItem from "../../components/apply-system/company/CompanyItem";
export default {
  name: "home",
  components: {CompanyItem, PostItem},
  data() {
    return {
      name:"",
      search: '',
      posts: [],
      companies: [],
      avatarUrl: 'http://localhost:8085/companyAvatar/'
    }
  },
  created() {

    const v = this
    this.$http.get('http://localhost:8085/premise/candidate_login/'+localStorage.getItem('phone'))
      .then(function (response) {
      v.name = response.data.name;
      localStorage.setItem("c_id",response.data.id)
      })
      .catch(function (error) {
        console.log(error);
      });


    const _this = this
    this.$http.get('http://localhost:8085/index/apply_home/hotRecruitment')
      .then(function (response) {
      for(var len = 0;len<response.data.length;len++){
        _this.posts.push(response.data[len])
        }
      })
      .catch(function (error) {
        console.log(error);
      });
      const __this = _this
      this.$http.get('http://localhost:8085/index/apply_home/hotCompany')
      .then(function (response) {
        console.log(response)
        for(var len = 0;len<response.data.length;len++){
        __this.companies.push(response.data[len])

        }
      })
      .catch(function (error) {
        console.log(error);
      });
  },
  methods: {
    morePosition() {
      this.$router.push({
        path: '/index/apply_position'
      })
      location.reload();
    },
    moreCompany() {
      this.$router.push({
        path: '/index/apply_company'
      })
      location.reload();
    }
  }

}
</script>

<style scoped>
@import "../../assets/css/apply-system/home.css";
</style>
