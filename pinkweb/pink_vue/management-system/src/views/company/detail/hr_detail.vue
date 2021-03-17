<template>
  <div class="home-main">
    <main-title>
      <i class="el-icon-coordinate"></i> HR详情 HireDetail
    </main-title>
    <el-row class="main-row">
      <el-col :span="8">
        <div class="left-content">
          <div class="avatar-col">
            <ul class="profile-bg">
              <li class="relative">
                <img src="../../../assets/img/avatar/avatar02.png" alt="" class="avatar">
              </li>
              <li class="user-name">{{user.name}}</li>
              <li class="company-name">{{user.phone}}</li>
            </ul>
          </div>
          <div class="base-info">
            <div class="base-info-box">
              <el-row>
                <el-col :span="8">
                  <div class="box-content">
                    <ul>
                      <li class="box-num">{{user.release_num}}</li>
                      <li class="box-title">已发布</li>
                    </ul>
                  </div>
                </el-col>
                <el-col :span="8">
                  <div class="box-content">
                    <ul>
                      <li class="box-num">{{user.attract_num}}</li>
                      <li class="box-title">已招募</li>
                    </ul>
                  </div>
                </el-col>
                <el-col :span="8">
                  <div class="box-content" style="border-right: 0">
                    <ul>
                      <li class="box-num">{{user.collection_num}}</li>
                      <li class="box-title">已收藏</li>
                    </ul>
                  </div>
                </el-col>
              </el-row>
            </div>
            <div class="base-info-li">
              <ul>
                <li class="info-li">
                  <span class="info-title">工号：</span>
                  <span class="info-body">{{user.id}}</span>
                </li>
                <li class="info-li">
                  <span class="info-title">职位：</span>
                  <span class="info-body">{{user.position}}</span>
                </li>
                <li class="info-li">
                  <span class="info-title">微信：</span>
                  <span class="info-body">{{user.wechat}}</span>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span="16">
        <div class="right-content">
          <div class="right-top">
            <div class="rt-title">详细信息 Detail</div>
            <el-row>
              <el-col :span="12" class="left-detail">
                <div class="detail-col">
                  <el-row>
                    <el-col :span="4" class="detail-title">姓名：</el-col>
                    <el-col :span="20" class="detail-content">{{user.name}}</el-col>
                  </el-row>
                </div>
                <div class="detail-col">
                  <el-row>
                    <el-col :span="4" class="detail-title">工号：</el-col>
                    <el-col :span="20" class="detail-content">{{user.id}}</el-col>
                  </el-row>
                </div>
                <div class="detail-col">
                  <el-row>
                    <el-col :span="4" class="detail-title">职位：</el-col>
                    <el-col :span="20" class="detail-content">{{user.position}}</el-col>
                  </el-row>
                </div>
                <div class="detail-col">
                  <el-row>
                    <el-col :span="4" class="detail-title">微信：</el-col>
                    <el-col :span="20" class="detail-content">{{user.wechat}}</el-col>
                  </el-row>
                </div>
              </el-col>
              <el-col :span="12" class="right-detail">
                <div class="detail-col">
                  <el-row>
                    <el-col :span="4" class="detail-title">性别：</el-col>
                    <el-col :span="20" class="detail-content">{{user.sex}}</el-col>
                  </el-row>
                </div>
                <div class="detail-col">
                  <el-row>
                    <el-col :span="4" class="detail-title">生日：</el-col>
                    <el-col :span="20" class="detail-content">{{user.birthday}}</el-col>
                  </el-row>
                </div>
                <div class="detail-col">
                  <el-row>
                    <el-col :span="4" class="detail-title">邮箱：</el-col>
                    <el-col :span="20" class="detail-content">{{user.email}}</el-col>
                  </el-row>
                </div>
              </el-col>
            </el-row>
          </div>
          <div class="right-bottom">
            <div class="rt-title rb-special">招募折线 Recruitment</div>
            <div id="myChart" :style="{width: '800px', height: '300px'}"></div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import qs from 'qs'; 
import MainTitle from "../../../components/company/body/MainTitle";
export default {
  name: "hr-detail",
  components: {MainTitle},
  data() {
    return {
      // 近10天日期
      dates: [],
      // 近10天招聘人数
      nums: [20, 12, 24, 50, 12, 3, 1, 10, 14, 30],
      editAvatarDialog: false,
      editVisible: false,
      imageUrl: '',
      user: {
        id: 'M202076625',
        phone: '15176685934',
        name: '马小超',
        sex: '男',
        birthday: '1998-07-05',
        email: '619580605@qq.com',
        wechat: 'Mxc15176685934',
        position: '高级Java技术总监',
        attract_num: '23',
        collection_num: '46',
        release_num: '51'
      }
    }
  },
  created(){
      const _this=this
      this.$http.get('http://localhost:8085/hire/hire_profile/'+this.$route.query.id)
      .then(function (response) {
            _this.user.id=response.data.user.id
            _this.user.phone=response.data.user.phone
            _this.user.name=response.data.user.name
            _this.user.sex=response.data.user.sex
            _this.user.birthday=response.data.user.birthday
            _this.user.email=response.data.user.email
            _this.user.wechat=response.data.ehr_vx
            _this.user.position=response.data.ehr_position
            _this.user.release_num=response.data.ehr_currentaccount         
      })
      .catch(function (error) {
        console.log(error)
      });
  },
  methods: {
    drawLine(){
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('myChart'))
      // 绘制图表
      myChart.setOption({
        tooltip: {},
        xAxis: {
          type: 'category',
          data: this.dates
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: this.nums,
          type: 'line',
          smooth: true
        }]
      });
    },
    mouseOver() {
      const dom = document.getElementsByClassName("occupy")
      dom[0].style.opacity = 1
    },
    mouseOut() {
      const dom = document.getElementsByClassName("occupy")
      dom[0].style.opacity = 0
    },
    // 获取近15日时间
    doHandleMonth(month){
      let m = month;
      if(month.toString().length === 1){
        m = "0" + month;
      }
      return m;
    },
    getDay(day){
      const today = new Date();
      const target_milliseconds=today.getTime() + 1000*60*60*24*day;
      today.setTime(target_milliseconds);
      let tMonth = today.getMonth();
      let tDate = today.getDate();
      tMonth = this.doHandleMonth(tMonth + 1);
      tDate = this.doHandleMonth(tDate);
      return tMonth + "-" + tDate;
    },
    submitEdit() {
      this.editVisible = false
      console.log(this.user)
    }
  },
  mounted() {
    // for (let i = -9; i <= 0; i ++) {
    //   this.dates.push(this.getDay(i))
    // }
    // this.drawLine();
        // this.drawLine();
        console.log(this.user)
    const _this = this
      this.nums = []
      for (let i = -9; i <= 0; i ++) {
        this.dates.push(this.getDay(i))
      }
      this.newdates=qs.stringify(this.dates, { arrayFormat: 'repeat' })
      this.$http.get('http://localhost:8085/hire/attract_detail/applyCompanyChange/'+this.$route.query.id)
      .then(function (response) {

        for(var len = 0;len<response.data.length;len++){
          _this.nums.push(response.data[len])
        }
        _this.drawLine();
      })
      .catch(function (error) {
        console.log(error);
      });
  }
}
</script>

<style scoped>
@import "../../../assets/css/company/hr-detail.css";
</style>
