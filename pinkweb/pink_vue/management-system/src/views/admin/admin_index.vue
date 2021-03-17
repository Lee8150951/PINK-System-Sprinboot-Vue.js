<template>
  <div>
    <el-row class="welcome">
      <el-col :span="2">
        <img src="../../assets/img/components/welcome.svg" alt="welcome" class="wel_logo">
      </el-col>
      <el-col :span="15" class="greet-panel">
        <h2>{{greet}}, Admin！</h2>
        <p>Welcome to <strong>PINK</strong>-panel.</p>
      </el-col>
      <el-col :span="7">
        <div class="feature-btn">
          <el-tooltip class="item" effect="dark" content="Edit" placement="top">
            <el-button icon="el-icon-edit-outline" circle @click="turnProfile"></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="Logout" placement="top">
            <el-button type="success" icon="el-icon-turn-off" circle @click="turnLogout"></el-button>
          </el-tooltip>
        </div>
      </el-col>
    </el-row>
    <el-row class="main-body">
      <el-col :span="9" class="left-body">
        <div class="panel-title">公司列表</div>
        <ul>
          <company-li v-for="(company, index) in companies.slice(0,7)" :key="index" :index="index" :company="company"></company-li>
        </ul>
        <el-button type="text" class="more-btn_1" @click="turnCompany">
          Show more <i class="el-icon-caret-bottom"></i>
        </el-button>
      </el-col>
      <el-col :span="14" class="right-body">
        <div class="panel-title">
          总览图谱
        </div>
        <div class="num-change">
          <el-row class="progress-panel">
            <el-col :span="5">
              <div>
                <el-progress type="circle" :percentage="user_c_percent" :width="100"></el-progress>
              </div>
              <div class="tips-name">用户数量变化</div>
            </el-col>
            <el-col :span="5">
              <div>
                <el-progress type="circle" :percentage="company_c_percent" :width="100"></el-progress>
              </div>
              <div class="tips-name">公司数量变化</div>
            </el-col>
            <el-col :span="1"><div style="height: 1px"></div></el-col>
            <el-col :span="5">
              <div class="change-num">{{user_c_num}}</div>
              <div class="change-title">用户增量</div>
            </el-col>
            <el-col :span="5">
              <div class="change-num">{{company_c_num}}</div>
              <div class="change-title">公司增量</div>
            </el-col>
          </el-row>
        </div>
        <div class="diagram">
          <div id="myChart" :style="{width: '100%', height: '340px'}"></div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import CompanyLi from "../../components/admin/companylist/CompanyLi";
import UserLi from "../../components/admin/userlist/UserLi";
export default {
  name: "admin_index",
  components: {UserLi, CompanyLi},
  data() {
    return {
      // 近10天日期
      dates: [],
      // 近10天公司新增数据
      company_nums: [20, 12, 24, 50, 12, 3, 1, 10, 14, 18],
      // 近10天用户新增数据
      user_nums: [10, 23, 41, 24, 12, 6, 20, 34, 51, 60],
      // 较昨日公司变化数量
      company_c_num: '',
      // 较昨日用户变化数量
      user_c_num: '',
      // 较昨日公司变化比例
      company_c_percent: '',
      // 较昨日用户变化比例
      user_c_percent: '',
      greet: '',
      companies: [
        {
          e_name: '腾讯(深圳)科技有限公司',
          e_legalrepresentative: '马化腾',
          e_establishtime: '2020-09-10',
          e_businessscope: 'IT业',
          e_logo: require('../../assets/img/company/tencent.jpg')
        }
      ]
    }
  },
  created(){
    const v = this
    this.$http.post('http://localhost:8085/index/apply_company/query?key='+this.$route.query.key+"&flag="+this.$route.query.flag,{
      params:{
        pageNow: 1,
        pageSize: 6
      }
    })
    .then(function (response) {
          v.companies = []
          for(var len = 0 ;len < 15;len++){
            if(len>=response.data.list.length){
              break;
            }
              v.companies.push(response.data.list[len])
            }
    })
    .catch(function (error) {
      console.log(error);
    });

    this.$http.get('http://localhost:8085/admin/admin_home/userChange')
    .then(function (response) {
      const that = v
      v.user_nums = response.data
      console.log("user_nums = "+ v.user_nums)
      v.$http.get('http://localhost:8085/admin/admin_home/companyChange')
      .then(function (response) {
        that.company_nums = response.data
        console.log("company_nums = "+ that.company_nums)
        for (let i = -9; i <= 0; i ++) {
        that.dates.push(that.getDay(i))
        }
        that.companyChange();
        that.userChange();
        that.drawLine();
        that.greetJudge();
      }).catch(function (error) {
      console.log(error);
    })
    })
    .catch(function (error) {
      console.log(error);
    });
  },
  methods: {
    turnCompany() {
      this.$router.push({
        path: '/admin/manage_company'
      })
    },
    turnLogout() {
      this.$router.push({
        path: '/UserShunt'
      })
    },
    turnProfile() {
      this.$router.push({
        path: '/admin/admin_profile'
      })
    },
    // 判断时间
    greetJudge() {
      const now = new Date(),hour = now.getHours()
      if(hour < 6) {
        this.greet = '凌晨好'
      } else if (hour < 9) {
        this.greet = '早上好'
      } else if (hour < 12) {
        this.greet = '上午好'
      } else if (hour < 14) {
        this.greet = '中午好'
      } else if (hour < 17) {
        this.greet = '下午好'
      } else if (hour < 19) {
        this.greet = '早傍晚好'
      } else if (hour < 22) {
        this.greet = '晚上好'
      } else {
        this.greet = '深夜好'
      }
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
    // 计算公司数量变化
    companyChange() {
      let companyNumList = this.company_nums;
      let length = companyNumList.length;
      let num_1 = companyNumList[length - 1];
      let num_2 = companyNumList[length - 2];
      let change = num_1 - num_2;
      let percent = change / num_2 * 100;
      this.company_c_percent = percent.toFixed(2);
      if (change > 0) {
        this.company_c_num = "+" + change
      } else {
        this.company_c_num = change
      }
    },
    // 计算用户数量变化
    userChange() {
      let userNumList = this.user_nums;
      let length = userNumList.length;
      let num_1 = userNumList[length - 1];
      let num_2 = userNumList[length - 2];
      let change = num_1;
      let percent = change / num_2 * 100;
      this.user_c_percent = percent.toFixed(2);
      if (change > 0) {
        this.user_c_num = "+" + change
      } else {
        this.user_c_num = change
      }
    },
    // echarts
    drawLine(){
      let myChart = this.$echarts.init(document.getElementById('myChart'), "style")
      myChart.setOption({
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          name: '日期',
          type: 'category',
          axisLine: {
            lineStyle: {
              // 设置x轴颜色
              color: '#bababa'
            }
          },
          data: this.dates
        },
        yAxis: {
          name: '新增数',
          type: 'value',
          splitNumber:6,  // 设置y轴刻度间隔个数
          axisLine: {
            lineStyle: {
              // 设置y轴颜色
              color: '#bababa'
            }
          },
        },
        series: [
          {
            name: '用户增量',
            data: this.user_nums,
            type: 'line',
            symbol: 'none',
            smooth: 0.5,
          },
          {
            name: '公司增量',
            data: this.company_nums,
            type: 'line',
            symbolSize:8,
            itemStyle:{
              normal:{
                // 拐点上显示数值
                label : {
                  show: true
                },
                borderColor:'red',  // 拐点边框颜色
                lineStyle:{
                  width:5,  // 设置线宽
                  type:'dotted'  //'dotted'虚线 'solid'实线
                }
              }
            }
          }
        ],
        color: ['#3ccd86', '#FF9F7F']
      });
    }
  },
}
</script>

<style scoped>
@import "../../assets/css/admin/admin_index.css";
</style>
