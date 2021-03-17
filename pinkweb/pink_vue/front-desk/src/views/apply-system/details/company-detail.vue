<template>
  <div class="company-detail-body">
    <el-row></el-row>
    <el-row class="detail-header">
      <el-col :span="15">
        <div class="header-content">
          <!-- <img :src="company.logo" alt="logo" class="company-logo"> -->
          <img :src="'http://localhost:8085/companyAvatar/'+company[0].e_logo" alt="" class="company-logo">
          <div class="c-info">
            <h2>{{company[0].e_name}}</h2>
            <p>{{company[0].e_operationstatus}} · {{company[0].e_size}}人以上</p>
          </div>
        </div>
      </el-col>
      <el-col :span="9">
        <div class="header-content">
          <div class="p-info">
            <el-row class="p-info-top">
              <el-col :span="18">
                <div class="grid-content">
                  <h2>{{company[0].companyHrList[0].ehr_currentaccount}}</h2>
                  <p>在招岗位</p>
                </div>
              </el-col>
              <el-col :span="6">
                <div class="grid-content">
                  <h2>{{company[0].companyHrList[0].hr_account}}</h2>
                  <p>位HR</p>
                </div>
              </el-col>
            </el-row>
            <el-row class="p-resume">
              <el-col :span="24">
                <div class="grid-content">
                  <el-button type="warning" class="resume-btn">上传简历</el-button>
                </div>
              </el-col>
            </el-row>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row class="c-main">
      <el-col :span="17">
        <div class="left-content">
          <div class="c-intro">
            <h3>{{company[0].e_name}}简介</h3>
            <p class="c-intro-main">{{company[0].e_introduction}}</p>
          </div>
          <div class="c-business">
            <h3>工商信息</h3>
            <el-collapse accordion class="collapse">
              <el-collapse-item title="法人 Corporate" name="1">
                <div>{{company[0].e_legalrepresentative}}</div>
              </el-collapse-item>
              <el-collapse-item title="企业类型 Type" name="2">
                <div>{{company[0].e_type}}</div>
              </el-collapse-item>
              <el-collapse-item title="经营状态 State" name="3">
                <div>{{company[0].e_operationstatus}}</div>
              </el-collapse-item>
              <el-collapse-item title="注册地址 Address" name="4">
                <div>{{company[0].e_registeredaddress}}</div>
              </el-collapse-item>
              <el-collapse-item title="经营范围 Range" name="5">
                <div>{{company[0].e_businessscope}}</div>
              </el-collapse-item>
            </el-collapse>
          </div>
          <div class="c-position">
            <h3>在招岗位</h3>
            <el-row class="p-items">
              <post-item-simple v-for="(post, index) in posts" :key="post.index" :post="post" :index="index"></post-item-simple>
            </el-row>
          </div>
        </div>
      </el-col>
      <el-col :span="7">
        <div class="right-content">
          <div class="c-intro">
            <h3>公司高管</h3>
            <el-row>
              <el-col :span="6">
                <!-- <div class="exe-content">
                  <img :src="executive.logo" alt="" class="executive">
                </div> -->
              </el-col>
              <el-col :span="18">
                <div class="boss-info-content">
                  <div class="boss-name">{{company[0].e_executive}}</div>
                  <div class="boss-job">{{company[0].e_position}}</div>
                </div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24" class="boss-info">
                {{company[0].e_executive_intro}}
              </el-col>
            </el-row>
          </div>
        </div>
        <div class="right-content">
          <div class="c-intro">
            <h3>公司环境</h3>
            <div class="environ-block">
              <el-carousel height="180px" style="z-index: 0">
                <el-carousel-item style="z-index: 0">
                  <img src="../../../assets/img/environment/ali01.jpg" alt="" class="environ-img">
                </el-carousel-item>
                <el-carousel-item>
                  <img src="../../../assets/img/environment/ali02.jpg" alt="" class="environ-img">
                </el-carousel-item>
                <el-carousel-item>
                  <img src="../../../assets/img/environment/ali03.jpg" alt="" class="environ-img">
                </el-carousel-item>
              </el-carousel>
            </div>
          </div>
        </div>
        <div class="right-content">
          <div class="c-intro">
            <h3>{{company[0].e_name}}招聘HR</h3>
          </div>
          <hr-item v-for="hr in hrs" :key="hr.index" :hr="hr"></hr-item>
        </div>
        <el-row>
          <el-col :span="24">
            <div class="avatar-btn-content">
              <el-button type="warning" plain class="avatar-btn">查看所有在招岗位</el-button>
            </div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// 导入PostItemSimple组件
import PostItemSimple from "../../../components/apply-system/post/PostItemSimple";
// 导入HrItem组件
import HrItem from "../../../components/apply-system/hr/HrItem";
export default {
  name: "company-detail",
  components: {HrItem, PostItemSimple},
  data() {
    return {
      company: [],
      hrs: [],
      hrs_tmp: [],
      posts : [],
      company_detail_tmp: []
    }
  },
    created() {
    this.e_id = this.$route.query.e_id;
    const _this = this
    this.$http.get('http://localhost:8085/index/company_detail/'+this.e_id)
      .then(function (response) {
        _this.company.push(response.data)
        // console.log(response)
      })
      .catch(function (error) {
        console.log(error);
      });

    this.e_id = this.$route.query.e_id;
    const __this = _this
    this.$http.get('http://localhost:8085/index/company_detail/joblist/'+this.e_id)
      .then(function (response) {
        console.log(response)
       __this.company_detail_tmp.push(response.data)
        var count = 0
       for(let i = 0;i<__this.company_detail_tmp.length;i++)
       {

        for(let j = 0;j<__this.company_detail_tmp[i].companyHrList.length;j++)
        {

          for(let k = 0;k<__this.company_detail_tmp[i].companyHrList[j].recruitmentList.length;k++)
          {
          __this.company_detail_tmp[i].companyHrList[j].recruitmentList[k].name = __this.company_detail_tmp[i].companyHrList[j].user.name
          __this.company_detail_tmp[i].companyHrList[j].recruitmentList[k].ehr_position = __this.company_detail_tmp[i].companyHrList[j].ehr_position
          __this.posts.push(__this.company_detail_tmp[i].companyHrList[j].recruitmentList[k])
          }
        }
       }
      })
      .catch(function (error) {
        console.log(error);
      });


    this.e_id = this.$route.query.e_id;
    this.$http.get('http://localhost:8085/index/company_detail/hrlist/'+this.e_id)
      .then(function (response) {


        __this.hrs_tmp.push(response.data)

       for(let i = 0;i<__this.hrs_tmp[0].length;i++)
       {
          __this.hrs_tmp[0][i].e_name = __this.hrs_tmp[0][i].company.e_name
          __this.hrs_tmp[0][i].name = __this.hrs_tmp[0][i].user.name
          __this.hrs.push(__this.hrs_tmp[0][i])
       }
      })
      .catch(function (error) {
        console.log(error);
      });
  },
}
</script>

<style scoped>
@import "../../../assets/css/apply-system/company-detail.css";
</style>
