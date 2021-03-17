<template>
  <div class="back-body">
    <el-row class="main">
      <el-col :span="9">
        <div class="left-content-register">
          <img src="../../../assets/img/background/premise/apply/apply_register.png" alt="bg" class="left-bg">
        </div>
      </el-col>
      <el-col :span="15">
        <div class="right-content">
          <div class="form1" v-if="pageActive === 1">
            <div class="title">
              <span class="left-title">Sign up</span>
              <span class="right-title green">'PINK'</span>
            </div>
            <div class="form">
              <el-form ref="form" :model="signup" label-width="80px">
                <div class="form-item">
                  <el-input v-model="signup.phone" placeholder="请输入手机号"></el-input>
                </div>
                <div class="form-item">
                  <el-input v-model="signup.password" placeholder="请输入密码" show-password></el-input>
                </div>
                <div class="form-item">
                  <el-input v-model="signup.re_password" placeholder="确认密码" show-password></el-input>
                </div>
                <div class="form-item">
                  <el-button type="success" @click="formSubmit_1" class="sub-btn">注册</el-button>
                </div>
              </el-form>
            </div>
            <div class="another-log">
              <span class="right-span">
                <el-button type="text" class="another-btn" @click="turnLogin">已有帐号登录...</el-button>
              </span>
            </div>
          </div>
          <div class="form2" v-if="pageActive === 2">
            <div class="title">
              <span class="left-title">基本</span>
              <span class="right-title green">信息</span>
            </div>
            <div class="form">
              <el-form ref="form" :model="base" label-width="80px">
                <div class="form-item">
                  <el-input v-model="base.name" placeholder="请输入姓名"></el-input>
                </div>
                <div class="form-item">
                  <el-row>
                    <el-col :span="12" class="left-btn-col">
                      <el-radio v-model="base.sex" :label="0" border class="form-radio">男</el-radio>
                    </el-col>
                    <el-col :span="12" class="right-btn-col">
                      <el-radio v-model="base.sex" :label="1" border class="form-radio">女</el-radio>
                    </el-col>
                  </el-row>
                </div>
                <div class="form-item">
                  <el-date-picker v-model="base.birthday" type="date" placeholder="选择出生日期" class="form-time"></el-date-picker>
                </div>
                <div class="form-item">
                  <el-button type="success" @click="formSubmit_2" class="sub-btn r-btn">完成</el-button>
                </div>
              </el-form>
            </div>
          </div>
          <!-- <div class="form3" v-if="pageActive === 3">
            <div class="title">
              <span class="left-title">实习</span>
              <span class="right-title green">经历</span>
            </div>
            <div class="form">
              <el-form ref="form" :model="practice" label-width="80px">
                <div class="form-item">
                  <el-input v-model="practice.c_finternship_enterprise" placeholder="请输入实习单位"></el-input>
                </div>
                <div class="form-item">
                  <el-input v-model="practice.c_finternship_job" placeholder="请输入实习担任职位"></el-input>
                </div>
                <div class="form-item">
                  <el-date-picker v-model="practice.c_finternship_stime" type="date" placeholder="实习开始时间" class="form-time"></el-date-picker>
                </div>
                <div class="form-item">
                  <el-date-picker v-model="practice.c_finternship_etime" type="date" placeholder="实习结束时间" class="form-time"></el-date-picker>
                </div>
                <div class="form-item">
                  <el-row>
                    <el-col :span="12" class="left-btn-col">
                      <el-button type="success" @click="step_up" class="sub-btn" plain>上一步</el-button>
                    </el-col>
                    <el-col :span="12" class="right-btn-col">
                      <el-button type="success" @click="formSubmit_3" class="sub-btn">下一步</el-button>
                    </el-col>
                  </el-row>
                </div>
              </el-form>
            </div>
          </div>
          <div class="form4" v-if="pageActive === 4">
            <div class="title">
              <span class="left-title">学历</span>
              <span class="right-title green">信息</span>
            </div>
            <div class="form">
              <el-form ref="form" :model="edu" label-width="80px">
                <div class="form-item">
                  <el-input v-model="edu.c_fschool" placeholder="请输入学校名称"></el-input>
                </div>
                <div class="form-item">
                  <el-select v-model="edu.c_fdegree" placeholder="请选择学历" class="select-col">
                    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
                  </el-select>
                </div>
                <div class="form-item">
                  <el-input v-model="edu.c_fmajor" placeholder="请输入专业"></el-input>
                </div>
                <div class="form-item">
                  <el-date-picker v-model="edu.c_ftime" type="date" placeholder="选择入学时间" class="form-time"></el-date-picker>
                </div>
                <div class="form-item">
                  <el-row>
                    <el-col :span="12" class="left-btn-col">
                      <el-button type="success" @click="step_up" class="sub-btn" plain>上一步</el-button>
                    </el-col>
                    <el-col :span="12" class="right-btn-col">
                      <el-button type="success" @click="formSubmit_3" class="sub-btn">下一步</el-button>
                    </el-col>
                  </el-row>
                </div>
              </el-form>
            </div>
          </div>
          <div class="form5" v-if="pageActive === 5">
            <div class="title">
              <span class="left-title">期望</span>
              <span class="right-title green">岗位</span>
            </div>
            <div class="form">
              <el-form ref="form" :model="post" label-width="80px">
                <div class="form-item">
                  <el-input v-model="post.c_exindustry" placeholder="请输入期望行业"></el-input>
                </div>
                <div class="form-item">
                  <el-cascader v-model="post.c_excity" :options="cities" :props="{expandTrigger: 'hover'}" placeholder="请选择期望城市" class="cascader-col"></el-cascader>
                </div>
                <div class="form-item">
                  <el-input v-model="post.c_exposition" placeholder="请输入期望职位"></el-input>
                </div>
                <div class="form-item">
                  <el-input v-model="post.c_exsalary" placeholder="请输入期望薪资"></el-input>
                </div>
                <div class="form-item">
                  <el-row>
                    <el-col :span="12" class="left-btn-col">
                      <el-button type="success" @click="step_up" class="sub-btn" plain>上一步</el-button>
                    </el-col>
                    <el-col :span="12" class="right-btn-col">
                      <el-button type="success" @click="formSubmit_4" class="sub-btn">完成</el-button>
                    </el-col>
                  </el-row>
                </div>
              </el-form>
            </div>
          </div> -->
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// 导入城市信息
import {provinceAndCityDataPlus} from 'element-china-area-data'
export default {
  name: "apply_register",
  data() {
    return {
      pageActive: 1,
      // 中国城市推荐List
      cities: provinceAndCityDataPlus,
      c_id:1,
      // 下拉菜单
      options: [
        {
          value: '初中及以下',
          label: '初中及以下'
        },
        {
          value: '中专及中技',
          label: '中专及中技'
        },
        {
          value: '高中',
          label: '高中'
        },
        {
          value: '大专',
          label: '大专'
        },
        {
          value: '本科',
          label: '本科'
        },
        {
          value: '硕士',
          label: '硕士'
        },
        {
          value: '博士',
          label: '博士'
        },
      ],
      // 登录信息
      signup: {
        phone: '',
        password: '',
        re_password: ''
      },
      // 基本信息
      base: {
        name: '',
        sex: '',
        birthday: '',
      },
      // 实习信息
      practice: {
        c_finternship_enterprise: '',
        c_finternship_job: '',
        c_finternship_stime: '',
        c_finternship_etime:'',
      },
      // 学历信息
      edu: {
        c_fschool: '',
        c_fdegree: '',
        c_fmajor: '',
        c_ftime: ''
      },
      // 期望岗位
      post: {
        c_exindustry: '',
        c_exposition: '',
        c_excity: '',
        c_exsalary: '',
        c_type:"",
      },
    }
  },
  methods: {
    formSubmit_1() {
      const _this = this
      this.$http.post('http://localhost:8085/premise/candidate_register/step_1', {
        data:{
        phone: this.signup.phone,
        password: this.signup.password
        }
      })
      .then(function (response) {
      })
      .catch(function (error) {
        console.log(error);
      });

      // 表单验证略
      this.pageActive = this.pageActive + 1
      // 信息上传
      // console.log(this.signup)
    },
    formSubmit_2() {
        const v = this
        this.$http.post('http://localhost:8085/premise/candidate_register/step_2', {
        data:{
        phone: this.signup.phone,
        name: this.base.name,
        sex: this.base.sex,
        birthday :this.base.birthday
        }
      })
      .then(function (response) {
      v.$router.push({
        path: '/premise/apply_login'
      })
      })
      .catch(function (error) {
        console.log(error);
      });
      // 表单验证略
      // this.pageActive = this.pageActive + 1
      // 信息上传

    },
    // formSubmit_3() {

    //   // 表单验证略
    //   this.pageActive = this.pageActive + 1
    //   // 信息上传
    //   console.log(this.practice)
    // },
    // formSubmit_4() {
    //   // 表单验证略
    //   this.$router.push({
    //     path: '/index/apply_home',
    //     query: {
    //       phone: this.signup.phone
    //     }
    //   })
    //   console.log(this.post)
    // },
    step_up() {
      this.pageActive = this.pageActive - 1
    },
    turnLogin() {
      this.$router.push({
        path: '/premise/apply_login'
      })
    }
  }
}
</script>

<style scoped>
@import "../../../assets/css/premise/premise-main.css";
@import "../../../assets/css/premise/premise-register.css";
</style>
