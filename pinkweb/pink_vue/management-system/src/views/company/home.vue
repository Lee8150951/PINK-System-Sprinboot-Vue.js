<template>
  <div class="home-main">
    <main-title>
      <i class="el-icon-odometer"></i> 总览 Dashboard
    </main-title>
    <div class="main-body">
      <el-row class="top-info">
        <el-col :span="12">
          <div class="tl-panel">
            <div class="greet">
              <span class="greet-time">Hello，</span>
              <span class="greet-target">{{company.user.name}}!</span>
            </div>
            <div class="diagram">
              <div id="myChart" :style="{width: '100%', height: '380px'}"></div>
            </div>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="tr-panel">
            <el-row>
              <el-col :span="12" class="hr-num">
                <div>
                  <div class="tips-title">旗下HR</div>
                  <div class="tips-num" id="hrNum"></div>
                  <div class="tips-other">
                    <span>Total number of hr</span>
                    <div class="more-btn_1">
                      <el-button type="text" @click="turnToList">more...</el-button>
                    </div>
                  </div>
                </div>
              </el-col>
              <el-col :span="11" class="post-num">
                <div>
                  <div class="tips-title">岗位总数</div>
                  <div class="tips-num" id="positionNum"></div>
                  <div class="tips-other">
                    <span>Total number of hr</span>
                    <div class="more-btn_2">
                      <el-button type="text" @click="turnToList">more...</el-button>
                    </div>
                  </div>
                </div>
              </el-col>
            </el-row>
            <div class="company-info">
              <div class="tips-title">公司信息</div>
              <div class="info-tips">
                <el-row>
                  <el-col :span="12">
                    <div class="left-tips">
                      <ul>
                        <li class="tip-li">
                          <i class="el-icon-info"></i>
                          {{company.company.e_id}}
                        </li>
                        <li class="tip-li">
                          <i class="el-icon-s-goods"></i>
                          {{company.company.e_name}}
                        </li>
                        <li class="tip-li">
                          <i class="el-icon-user-solid"></i>
                          {{company.company.e_legalrepresentative}}
                        </li>
                      </ul>
                    </div>
                  </el-col>
                  <el-col :span="12">
                    <div class="right-tips">
                      <ul>
                        <li class="tip-li">
                          <i class="el-icon-s-cooperation"></i>
                          {{company.company.e_businessscope}}
                        </li>
                        <li class="tip-li">
                          <i class="el-icon-s-shop"></i>
                          {{company.company.e_type}}
                        </li>
                        <li class="tip-li">
                          <i class="el-icon-s-opportunity"></i>
                          {{company.company.e_address}}
                        </li>
                      </ul>
                    </div>
                  </el-col>
                </el-row>
              </div>
              <div class="more-col">
                <el-button type="primary" class="more-btn" @click="turnToInfo">查看详情</el-button>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import MainTitle from "../../components/company/body/MainTitle";
export default {
  name: "home",
  components: {MainTitle},
  data() {
    return {
      // 近10天日期
      dates: [],
      // 近10天招聘人数
      nums: [20, 12, 24, 50, 12, 3, 1, 10, 14, 30],
      greet: '',
      company: {
        hrNum:'',
        positionNum:''
      },
      e_id:''
    }
  },
  created(){
      const v = this
    this.$http.get('http://localhost:8085/premise/candidate_login/'+localStorage.getItem('phone'))
      .then(function (response) {
        console.log(response)
      localStorage.setItem("ea_id",response.data.id)
      })
      .catch(function (error) {
        console.log(error);
      });

    const _this = this
    var ea_id=11
    this.$http.get('http://localhost:8085/company/profile/'+localStorage.getItem("ea_id"))
      .then(function (response) {
        console.log(response)
        const v = _this
           _this.company=response.data
           _this.$http.get('http://localhost:8085/hire/hire_profile/company/'+response.data.e_id)
           .then(function (response) {
             console.log(response)
                v.company.hrNum=response.data.hrNum;
                v.company.positionNum=response.data.positionNum;
                setTimeout(function () {
                  let dom_1 = document.getElementById("hrNum")
                  dom_1.textContent = v.company.hrNum
                  let dom_2 = document.getElementById("positionNum")
                  dom_2.textContent = v.company.positionNum
                }, 0)
      })
      .catch(function (error) {
        console.log(error)
      });


      })
      .catch(function (error) {
        console.log(error)
      });

  },
  methods: {
    turnToInfo() {
      this.$router.push({
        path: '/company/profile'
      })
    },
    turnToList() {
      this.$router.push({
        path: '/company/list'
      })
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
    // echarts
    drawLine(){
      const that = this
      setTimeout(function () {
        let myChart = that.$echarts.init(document.getElementById('myChart'), "style")
        myChart.setOption({
          xAxis: {
            type: 'category',
            data: that.dates,
            axisLine: {
              lineStyle: {
                type: 'solid',
                color: '#d8d8d8',
                width:'1'
              }
            },
            axisLabel: {
              textStyle: {
                color: '#d8d8d8',
              }
            },
          },
          yAxis: {
            type: 'value',
            axisLine: {//线
              show: false
            },
            axisLabel: {
              textStyle: {
                color: '#b9c8f1',
              }
            },
            splitLine: {
              lineStyle: {
                color: ['#b9c8f1'],
              }
            }
          },
          series: [{
            data: [20, 12, 24, 50, 12, 3, 1, 10, 14, 30],
            type: 'bar',
            itemStyle: {
              normal: {
                color: '#518ee8',
                label: {
                  show: true,
                  position: 'top',
                  textStyle: {
                    color: '#518ee8'
                  }
                }
              }
            },
            barWidth: 10
          }]
        });
      }, 200)
    }
  },
  mounted() {
    for (let i = -9; i <= 0; i ++) {
      this.dates.push(this.getDay(i))
    }
    this.drawLine();
    this.greetJudge();
  }
}
</script>

<style scoped>
@import "../../assets/css/company/home.css";
</style>
