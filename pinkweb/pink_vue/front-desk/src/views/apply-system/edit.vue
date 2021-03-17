<template>
  <div class="edit-body">
    <el-row>
      <el-col :span="18">
        <div class="left-content">
          <div class="left-header">
            <span class="left-header-title">我的在线简历</span>
            <span class="left-header-btn">
              <el-button type="text" class="header-btn">在线预览</el-button>
            </span>
          </div>
          <div class="base-info">
            <h4>编辑个人信息</h4>
            <el-form ref="form" :model="base_info">
              <el-row class="info-body">
                <el-col :span="12">
                  <div class="left-info">
                    <div class="info-panel">
                      <li class="info-title">姓名</li>
                      <li class="info-btn-panel">
                        <el-input v-model="base_info[1].user.name" placeholder="请输入内容" class="info-btn"></el-input>
                      </li>
                    </div>
                    <div class="info-panel">
                      <li class="info-title">性别</li>
                      <li class="info-btn-panel">
                        <el-radio v-model="base_info[1].user.sex" :label="0" border class="info-radio">男</el-radio>
                        <el-radio v-model="base_info[1].user.sex" :label="1" border class="info-radio">女</el-radio>
                      </li>
                    </div>
                    <div class="info-panel">
                      <li class="info-title">生日</li>
                      <li class="info-btn-panel">
                        <el-date-picker type="date" placeholder="选择日期" v-model="base_info[1].user.birthday" style="width: 100%;"></el-date-picker>
                      </li>
                    </div>
                    <div class="info-panel">
                      <li class="info-title">微信号(选填)</li>
                      <li class="info-btn-panel">
                        <el-input v-model="base_info[1].c_vx" placeholder="请输入内容" class="info-btn"></el-input>
                      </li>
                    </div>
                  </div>
                </el-col>
                <el-col :span="12">
                  <div class="right-info">
                    <div class="info-panel">
                      <li class="info-title">注册时间</li>
                      <li class="info-btn-panel">
                        <el-date-picker type="date" placeholder="选择日期" v-model="base_info[1].user.created_time" :disabled="true" style="width: 100%;"></el-date-picker>
                      </li>
                    </div>
                    <div class="info-panel">
                      <li class="info-title">身份</li>
                      <li class="info-btn-panel">
                        <!-- <el-input placeholder="请输入内容" v-model="base_info[1].c_type" class="info-btn"></el-input> -->
                        <el-radio v-model="base_info[1].c_type" :label="1" border class="info-radio2">实习</el-radio>
                        <el-radio v-model="base_info[1].c_type" :label="2" border class="info-radio2">应届</el-radio>
                        <el-radio v-model="base_info[1].c_type" :label="3" border class="info-radio2">社招</el-radio>
                   
                      </li>
                    </div>
                    <div class="info-panel">
                      <li class="info-title">电话</li>
                      <li class="info-btn-panel">
                        <el-input v-model="base_info[1].user.phone" placeholder="请输入内容" class="info-btn"></el-input>
                      </li>
                    </div>
                    <div class="info-panel">
                      <li class="info-title">邮箱</li>
                      <li class="info-btn-panel">
                        <el-input v-model="base_info[1].user.email" placeholder="请输入内容" class="info-btn"></el-input>
                      </li>
                    </div>
                    <div class="info-panel info-sub">
                      <el-button type="warning" class="info-btn-sub" @click="ModifyPersonalInformation()">完成</el-button>
                    </div>
                  </div>
                </el-col>
              </el-row>
            </el-form>
          </div>
          <div class="message-panel message-panel_1">
            <div class="message-title">
              个人简介
            </div>
            <div class="message-body" @click="messageVisible = true">
              <el-card shadow="hover" class="message-card">
                {{base_info[1].c_intro}}
              </el-card>
            </div>
          </div>
          <el-dialog title="个人简介修改" :visible.sync="messageVisible">
            <el-form :model="base_info[1].c_intro">
              <el-form-item>
                <el-input type="textarea" :autosize="{minRows: 6, maxRows: 6}" placeholder="请输入修改内容" v-model="base_info[1].c_intro"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="warning" class="info-btn-sub" style="float: right" @click="ModifyPersonalInformation(),messageVisible = false">修改</el-button>
              </el-form-item>
            </el-form>
          </el-dialog>
         <div class="message-panel">
            <div class="message-title">
              期望职位
            </div>
            <div class="message-body" @click="expectVisible = true">
              <el-card shadow="hover" class="message-card">
                <span class="message-span">
                  <i class="el-icon-suitcase"></i>{{base_info[1].c_exposition}}
                </span>
                <span class="message-span">
                  <i class="el-icon-bank-card"></i>{{base_info[1].c_exsalary}}
                </span>
                <span class="message-span">
                  <i class="el-icon-office-building"></i>{{base_info[1].c_exindustry}}
                </span>
                <span class="message-span_2">
                  <i class="el-icon-location-outline"></i>{{base_info[1].c_excity}}
                </span>
              </el-card>
            </div>
          </div>
          <el-dialog title="期望修改" :visible.sync="expectVisible">
            <el-form :model="demand">
              <div class="info-panel">
                <li class="info-title">期望岗位</li>
                <li class="info-btn-panel">
                  <el-input v-model="base_info[1].c_exposition" placeholder="请输入内容" class="info-btn"></el-input>
                </li>
              </div>
              <div class="info-panel">
                <li class="info-title">期望薪资</li>
                <li class="info-btn-panel">
                  <el-input v-model="base_info[1].c_exsalary" placeholder="请输入内容" class="info-btn"></el-input>
                </li>
              </div>
              <div class="info-panel">
                <li class="info-title">期望行业</li>
                <li class="info-btn-panel">
                  <el-input v-model="base_info[1].c_exindustry" placeholder="请输入内容" class="info-btn"></el-input>
                </li>
              </div>
              <div class="info-panel">
                <li class="info-title">期望城市</li>
                <li class="info-btn-panel">
                  <el-cascader v-model="base_info[1].c_excity" :options="cities" :props="{expandTrigger: 'hover'}"  @change="handleChange" placeholder="请选择期望城市" class="cascader-col"></el-cascader>
                </li>
              </div>
              <div class="info-panel info-sub">
                <el-button type="warning" class="info-btn-sub"  @click="ModifyPersonalInformation(),expectVisible = false">完成</el-button>
              </div>
            </el-form>
          </el-dialog>
          <div class="message-panel">
            <div class="message-title">
              实习经历
              <el-button type="text" class="add-btn" @click="practiceVisible = true">
                <i class="el-icon-circle-plus"></i> 修改
              </el-button>
            </div>
            <div class="message-body">
              <practice-panel v-for="practice in practices" :key="practice.c_index" :practice="practice"></practice-panel>
            </div>
          </div>
          <el-dialog title="修改实习经历" :visible.sync="practiceVisible">
            <el-form :model="practice_form">
              <div class="info-panel">
                <li class="info-title">第一实习单位</li>
                <li class="info-btn-panel">
                  <el-input v-model="base_info[1].c_finternship_enterprise" placeholder="请输入内容" class="info-btn"></el-input>
                </li>
              </div>
              <div class="info-panel">
                <li class="info-title">担任职务</li>
                <li class="info-btn-panel">
                  <el-input v-model="base_info[1].c_finternship_job" placeholder="请输入内容" class="info-btn"></el-input>
                </li>
              </div>
              <div class="info-panel">
                <li class="info-title">实习开始时间</li>
                <li class="info-btn-panel">
                  <el-date-picker type="date" placeholder="选择日期" v-model="base_info[1].c_finternship_stime" style="width: 100%;"></el-date-picker>
                </li>
              </div>
              <div class="info-panel">
                <li class="info-title">实习结束时间</li>
                <li class="info-btn-panel">
                  <el-date-picker type="date" placeholder="选择日期" v-model="base_info[1].c_finternship_etime" style="width: 100%;"></el-date-picker>
                </li>
              </div>
              <div class="line" />
              <div class="info-panel">
                <li class="info-title">第二实习单位</li>
                <li class="info-btn-panel">
                  <el-input v-model="base_info[1].c_sinternship_enterprise" placeholder="请输入内容" class="info-btn"></el-input>
                </li>
              </div>
              <div class="info-panel">
                <li class="info-title">担任职务</li>
                <li class="info-btn-panel">
                  <el-input v-model="base_info[1].c_sinternship_job" placeholder="请输入内容" class="info-btn"></el-input>
                </li>
              </div>
              <div class="info-panel">
                <li class="info-title">实习开始时间</li>
                <li class="info-btn-panel">
                  <el-date-picker type="date" placeholder="选择日期" v-model="base_info[1].c_sinternship_stime" style="width: 100%;"></el-date-picker>
                </li>
              </div>
              <div class="info-panel">
                <li class="info-title">实习结束时间</li>
                <li class="info-btn-panel">
                  <el-date-picker type="date" placeholder="选择日期" v-model="base_info[1].c_sinternship_etime" style="width: 100%;"></el-date-picker>
                </li>
              </div>
              <div class="info-panel info-sub">
                <el-button type="warning" class="info-btn-sub" @click="ModifyPersonalInformation(),practiceVisible = false">完成</el-button>
              </div>
            </el-form>
          </el-dialog>
          <div class="message-panel">
            <div class="message-title">
              教育经历
              <el-button type="text" class="add-btn" @click="eduVisible = true">
                <i class="el-icon-circle-plus"></i> 修改
              </el-button>
            </div>
            <div class="message-body">
              <educate-panel v-for="edu in edus" :key="edu.c_index" :edu="edu"></educate-panel>
            </div>
          </div>
        </div>
        <el-dialog title="修改教育经历" :visible.sync="eduVisible">
          <el-form :model="edu_form">
            <div class="info-panel">
              <li class="info-title">第一学历</li>
              <li class="info-btn-panel">
                <el-input v-model="base_info[1].c_fdegree" placeholder="请输入内容" class="info-btn"></el-input>
              </li>
            </div>
            <div class="info-panel">
              <li class="info-title">毕业院校</li>
              <li class="info-btn-panel">
                <el-input v-model="base_info[1].c_fschool" placeholder="请输入内容" class="info-btn"></el-input>
              </li>
            </div>
            <div class="info-panel">
              <li class="info-title">专业</li>
              <li class="info-btn-panel">
                <el-input v-model="base_info[1].c_fmajor" placeholder="请输入内容" class="info-btn"></el-input>
              </li>
            </div>

            <div class="info-panel">
              <li class="info-title">在校时间</li>
              <li class="info-btn-panel">
                <el-date-picker type="date" placeholder="选择日期" v-model="base_info[1].c_ftime" style="width: 100%;"></el-date-picker>
              </li>
            </div>
            <div class="line" />
            <div class="info-panel">
              <li class="info-title">第二学历</li>
              <li class="info-btn-panel">
                <el-input v-model="base_info[1].c_sdegree" placeholder="请输入内容" class="info-btn"></el-input>
              </li>
            </div>
            <div class="info-panel">
              <li class="info-title">毕业院校</li>
              <li class="info-btn-panel">
                <el-input v-model="base_info[1].c_sschool" placeholder="请输入内容" class="info-btn"></el-input>
              </li>
            </div>
            <div class="info-panel">
              <li class="info-title">专业</li>
              <li class="info-btn-panel">
                <el-input v-model="base_info[1].c_smajor" placeholder="请输入内容" class="info-btn"></el-input>
              </li>
            </div>
            <div class="info-panel">
              <li class="info-title">在校时间</li>
              <li class="info-btn-panel">
                <el-date-picker type="date" placeholder="选择日期" v-model="base_info[1].c_stime" style="width: 100%;"></el-date-picker>
              </li>
            </div>
            <div class="info-panel info-sub">
              <el-button type="warning" class="info-btn-sub"  @click="ModifyPersonalInformation(),eduVisible = false">完成</el-button>
            </div>
          </el-form>
        </el-dialog>
      </el-col>
      <el-col :span="6">
        <div class="grid-content">
          <annex-panel></annex-panel>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import PracticePanel from "../../components/apply-system/profile/PracticePanel";
  import ProjectPanel from "../../components/apply-system/profile/ProjectPanel";
  import EducatePanel from "../../components/apply-system/profile/EducatePanel";
  import AnnexPanel from "../../components/apply-system/profile/AnnexPanel";
  import {provinceAndCityDataPlus,CodeToText} from 'element-china-area-data';
  export default {
    name: "edit",
    components: {AnnexPanel, EducatePanel, ProjectPanel, PracticePanel},
    data() {
      return {
        cities: provinceAndCityDataPlus,
        practiceVisible: false,
        projectVisible: false,
        eduVisible: false,
        messageVisible:false,
        expectVisible:false,
        
        selectedOptions: [],
        // 基本信息
        base_info: [{
          // c_id: '1',
          // birthday: '1998.07.05',
          // created_time: '2020.12.04',
          // phone: '15169970596',
          // name: '马小超',
          // sex: '1',
          // email: '62738473@qq.com',
          // vx: 'mxc62738473',
          // type: '求职者',
          // c_intro: '个人开发能力较强; 待人热情，性格外向; 有较为丰富的项目开发经验; 自学能力强; 有上进心',
          // c_id: '1',
          // c_exindustry: '金融业',
          // c_exposition: 'Web前端',
          // c_excity: '上海',
          // c_exsalary: '10K-12K',

          // c_iscompany: '上海腾讯研发中心',
          // c_ispost: '前端开发',
          // c_istime: '2019.10.09',

          // c_iscompany: '杭州阿里研发中心',
          // c_ispost: '大数据开发',
          // c_istime: '2020.10.09',

          // c_school: '华中科技大学',
          // c_qualification: '硕士',
          // c_major: '电子信息',
          // c_schooling: '2020.09',

          // c_school: '华中科技大学',
          // c_qualification: '硕士',
          // c_major: '电子信息',
          // c_schooling: '2020.09'
        }],
        // 简历信息
        resume: {
          c_intro: '个人开发能力较强; 待人热情，性格外向; 有较为丰富的项目开发经验; 自学能力强; 有上进心'
        },
        // 期望职位
        demand: {
          c_id: '1',
          c_exindustry: '金融业',
          c_exposition: 'Web前端',
          c_excity: '上海',
          c_exsalary: '10K-12K'
        },
        // 实习信息
        practice_tmp :{},
        practices: [],
        practice_form: {
          c_id: '',
          c_index: '',
          c_iscompany: '',
          c_ispost: '',
          c_istime: ''
        },
        // 项目信息
        // project_form: {},
        // 教育信息
        edu_tmp : {},
        edus: [],
        edu_form: {
          c_id: '',
          c_school: '',
          c_qualification: '',
          c_major: '',
          c_schooling: ''
        }
      }
    },
    created() {
      this.c_id = this.$route.query.c_id;
      const _this = this
      this.$http.get('http://localhost:8085/index/apply_edit/'+this.c_id)
        .then(function (response) {

          _this.base_info.push(response.data)
          console.log(_this.base_info[1].user.sex);

          _this.practice_tmp.c_finternship_enterprise = _this.base_info[1].c_finternship_enterprise
          _this.practice_tmp.c_finternship_stime = _this.base_info[1].c_finternship_stime
          _this.practice_tmp.c_finternship_etime = _this.base_info[1].c_finternship_etime
          _this.practice_tmp.c_finternship_job = _this.base_info[1].c_finternship_job

          _this.practice_tmp.c_sinternship_enterprise = _this.base_info[1].c_sinternship_enterprise
          _this.practice_tmp.c_sinternship_stime = _this.base_info[1].c_sinternship_stime
          _this.practice_tmp.c_sinternship_etime = _this.base_info[1].c_sinternship_etime
          _this.practice_tmp.c_sinternship_job = _this.base_info[1].c_sinternship_job
          _this.practices.push(_this.practice_tmp)

          _this.edu_tmp.c_fdegree = _this.base_info[1].c_fdegree
          _this.edu_tmp.c_fschool = _this.base_info[1].c_fschool
          _this.edu_tmp.c_fmajor = _this.base_info[1].c_fmajor
          _this.edu_tmp.c_ftime = _this.base_info[1].c_ftime

          _this.edu_tmp.c_sdegree = _this.base_info[1].c_sdegree
          _this.edu_tmp.c_sschool = _this.base_info[1].c_sschool
          _this.edu_tmp.c_smajor = _this.base_info[1].c_smajor
          _this.edu_tmp.c_stime = _this.base_info[1].c_stime


          _this.edus.push(_this.edu_tmp)
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    methods:{
      ModifyPersonalInformation:function(){
          const _this = this
          this.$http.post('http://localhost:8085/index/apply_edit/modify_personal_info', {
          id :_this.base_info[1].user.id,
          name: _this.base_info[1].user.name,
          phone: _this.base_info[1].user.phone,
          sex: _this.base_info[1].user.sex,
          email: _this.base_info[1].user.email,
          birthday: _this.base_info[1].user.birthday,
        })
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });
        this.$http.post('http://localhost:8085/index/apply_edit/modify_personal_candidate_info', {
          c_id :_this.base_info[1].user.id,
          c_vx :_this.base_info[1].c_vx,
          c_type : _this.base_info[1].c_type,
          c_intro : _this.base_info[1].c_intro,
          c_finternship_enterprise : _this.base_info[1].c_finternship_enterprise,
          c_finternship_job : _this.base_info[1].c_finternship_job,
          c_finternship_stime : _this.base_info[1].c_finternship_stime,
          c_finternship_etime : _this.base_info[1].c_finternship_etime,
          c_sinternship_enterprise : _this.base_info[1].c_sinternship_enterprise,
          c_sinternship_job : _this.base_info[1].c_sinternship_job,
          c_sinternship_stime : _this.base_info[1].c_sinternship_stime,
          c_sinternship_etime : _this.base_info[1].c_sinternship_etime,

          c_fdegree : _this.base_info[1].c_fdegree,
          c_fschool : _this.base_info[1].c_fschool,
          c_fmajor : _this.base_info[1].c_fmajor,
          c_ftime : _this.base_info[1].c_ftime,
          c_sdegree : _this.base_info[1].c_sdegree,
          c_sschool : _this.base_info[1].c_sschool,
          c_smajor : _this.base_info[1].c_smajor,
          c_stime : _this.base_info[1].c_stime,

          c_excity : CodeToText[_this.base_info[1].c_excity[0]]+" "+CodeToText[_this.base_info[1].c_excity[1]],
          c_exsalary : _this.base_info[1].c_exsalary,
          c_exindustry : _this.base_info[1].c_exindustry,
          c_exposition : _this.base_info[1].c_exposition,
        })
        .then(function (response) {
          location.reload();
        })
        .catch(function (error) {
          console.log(error);
        });
      }
    },
    handleChange() {
     var self = this;
     var cityCode = self.base_info[1].c_excity[1];
     // CodeToText属性是区域码，属性值是汉字 CodeToText['110000']输出北京市
     self.base_info[1].c_excity = CodeToText[cityCode];

 }, 
  }
</script>

<style scoped>
@import "../../assets/css/apply-system/edit.css";
</style>
