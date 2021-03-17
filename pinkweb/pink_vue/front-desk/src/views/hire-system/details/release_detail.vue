<template>
  <div class="edit-body">
    <page-title>
      岗位详情 Release Detail
    </page-title>
    <el-row class="main-row">
      <el-form ref="form" :model="position" label-width="80px">
        <el-col :span="8">
          <div class="left-content">
            <div class="left-title">基本信息 Basic-Information</div>
            <div class="form">
              <el-form-item label="岗位名称">
                <el-input v-model="position.r_name"></el-input>
              </el-form-item>
              <el-form-item label="薪资范围">
                <el-select v-model="position.r_salary" placeholder="请选择薪资范围" style="width: 100%">
                  <el-option label="3K以下" value="3K以下"></el-option>
                  <el-option label="3-5K" value="3-5K"></el-option>
                  <el-option label="5-10K" value="5-10K"></el-option>
                  <el-option label="10-15K" value="10-15K"></el-option>
                  <el-option label="15-20K" value="15-20K"></el-option>
                  <el-option label="20-30K" value="20-30K"></el-option>
                  <el-option label="30-50K" value="30-50K"></el-option>
                  <el-option label="50K以上" value="50K以上"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="岗位地址">
                <el-cascader v-model="position.r_address" :options="cities" :props="{expandTrigger: 'hover'}" :placeholder="position.r_address" class="cascader-col" @change="handleChange"></el-cascader>
              </el-form-item>
              <el-form-item label="工作经验">
                <el-select v-model="position.r_experience" placeholder="请选择工作经验" style="width: 100%">
                  <el-option label="在校生" value="在校生"></el-option>
                  <el-option label="应届生" value="应届生"></el-option>
                  <el-option label="1年以内" value="1年以内"></el-option>
                  <el-option label="1-3年" value="1-3年"></el-option>
                  <el-option label="3-5年" value="3-5年"></el-option>
                  <el-option label="5-10年" value="5-10年"></el-option>
                  <el-option label="10年以上" value="10年以上"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="学历要求">
                <el-select v-model="position.r_education" placeholder="请选择学历要求" style="width: 100%">
                  <el-option label="初中及以下" value="初中及以下"></el-option>
                  <el-option label="中专/技校" value="中专/技校"></el-option>
                  <el-option label="高中" value="高中"></el-option>
                  <el-option label="大专" value="大专"></el-option>
                  <el-option label="本科" value="本科"></el-option>
                  <el-option label="硕士" value="硕士"></el-option>
                  <el-option label="博士" value="博士"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="创建时间">
                <el-date-picker type="date" placeholder="选择日期" v-model="position.created_time" style="width: 100%;" disabled></el-date-picker>
              </el-form-item>
              <el-form-item label="其他介绍">
                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 4}" placeholder="请输入内容" v-model="position.r_introduction"></el-input>
              </el-form-item>
              <div class="submit-col">
                <el-button type="primary" @click="editForm">修改</el-button>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :span="16">
          <div class="right-content">
            <div class="right-top">
              <el-row>
                <el-col :span="14">
                  <div class="rt-left-content">
                    <div class="rt-title">福利 Welfare</div>
                    <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 4}" placeholder="请输入内容" v-model="position.r_tag"></el-input>
                  </div>
                </el-col>
                <el-col :span="10">
                  <div class="rt-right-content">
                    <div class="rt-title">求职者 Job-Seeker</div>
                    <el-row class="js-row">
                      <el-col :span="12">
                        <div class="js-left-content">
                          <ul @click="deliveriesList">
                            <li class="num">{{position.applyNum}}</li>
                            <li class="tips">投递量</li>
                          </ul>
                        </div>
                      </el-col>
                      <el-col :span="12">
                        <div class="js-right-content" @click="collectionList">
                          <ul>
                            <li class="num">0</li>
                            <li class="tips">收藏人数</li>
                          </ul>
                        </div>
                      </el-col>
                    </el-row>
                  </div>
                </el-col>
              </el-row>
            </div>
            <div class="right-bottom">
              <div class="rt-title rb-special">投递曲线 Deliveries-Line</div>
              <div id="myChart" :style="{width: '780px', height: '370px'}"></div>
            </div>
          </div>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>

<script>
import qs from 'qs';
  // 导入城市信息
  import {provinceAndCityDataPlus,CodeToText} from 'element-china-area-data'
  // 导入PageTitle模块
  import PageTitle from "../../../components/hire-system/title/PageTitle";
  export default {
    name: "release_detail",
    components: {PageTitle},
    data() {
      return {
        // 中国城市推荐List
        cities: provinceAndCityDataPlus,
        // 近10天日期
        dates: [],
        //保存处理过的10填日期
        newdates:[],
        // 近10天招聘人数
        nums: [],
        // 岗位信息
        position: {},
        // 求职者数量
        num: {
          deliveries: 27,
          collection: 12
        },
        r_id: '',
        r_name: '',
        r_salary: '',
        r_address: '',
        r_experience: '',
        r_education: '',
        r_introduction: '',
        r_tag: '',

        province:'',
        city:'',


      }
    },
    methods: {

      handleChange(val) {
        this.province=CodeToText[val[0]];
        this.city=CodeToText[val[1]];
       console.log(this.province);
       console.log(this.city);
       if(this.city==null||this.city=="直辖市"||this.city=="全部"){
            this.position.r_address=this.province
       }else{
           this.position.r_address=this.province+"/"+this.city


       }
      },
      editForm() {
        console.log(this.position)
        switch(this.position.r_salary){
           case"3K以下":
              this.position.r_salary=3000;
               break;
           case"3K-5K":
              this.position.r_salary=4000;break;
           case"5-10K":
               this.position.r_salary=7500;break;
           case"10-15K":
               this.position.r_salary=12500;break;
           case"15-20K":
               this.position.r_salary=17500;break;
           case"20-30K":
               this.position.r_salary=25000;break;
           case"30-50K":
               this.position.r_salary=40000;break;
           case"50K以上":
               this.position.r_salary=50000;break;
           default:
              break;
       };

       console.log(this.position)
        this.$http.post('http://localhost:8085/hire/release_detail/update',
        {

            r_id:this.position.r_id,
            r_name:this.position.r_name,
            r_salary:this.position.r_salary,
            r_address:this.position.r_address,
            r_experience:this.position.r_experience,
            r_education:this.position.r_education,
            r_introduction:this.position.r_introduction,
            r_tag:this.position.r_tag,

        }).then((response)=>{
           console.log(response);
       });
       location.reload();

      },
      deliveriesList() {
        this.$router.push({
          path: '/hire/hire_attract',
          query: {
            r_id: this.position.r_id,
            r_name: this.position.r_name,
          }
        })
      },
      collectionList() {
        this.$router.push({
          path: '/hire/hire_attract',
          query: {
            r_id: this.position.r_id,
            r_name: this.position.r_name,
          }
        })
      },
      drawLine() {
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
    },
    created() {
    const _this = this
    this.$http.get('http://localhost:8085/hire/release_detail/'+this.$route.query.r_id)
      .then(function (response) {
          _this.position=response.data
      })
      .catch(function (error) {
        console.log(error);
      });

      for (let i = -9; i <= 0; i ++) {
        this.dates.push(this.getDay(i))
      }
      this.newdates=qs.stringify(this.dates, { arrayFormat: 'repeat' })
      this.$http.get('http://localhost:8085/hire/attract_detail/applyChange/'+this.$route.query.r_id)
      .then(function (response) {

        for(var len = 0;len<response.data.length;len++){
          _this.nums.push(response.data[len])
        }
                console.log("_this.nums = "+_this.nums)
        _this.drawLine();
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    mounted() {

    }
  }
</script>

<style scoped>
@import "../../../assets/css/hire-system/release-detail.css";
</style>
