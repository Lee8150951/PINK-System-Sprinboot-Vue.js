<template>
  <div class="edit-body">
    <page-title>
      对象详情 Attract Detail
    </page-title>
    <el-row class="main-row">
      <el-col :span="8">
        <div class="left-content">
          <div class="avatar-col">
            <ul class="profile-bg">
              <li class="relative">
                <img src="../../../assets/img/avatar/avatar02.png" alt="" class="avatar">
              </li>
              <li class="user-name">{{user.candidate.user.name}}</li>
              <li class="school-name">{{user.candidate.highSchool}} | {{user.candidate.highDegree}}</li>
            </ul>
          </div>
          <div class="base-info">
            <div class="base-info-li">
              <ul>
                <li class="info-li">
                  <span class="info-title">性别：</span>
                  <span class="info-body">{{sex_show}}</span>
                </li>
                <li class="info-li">
                  <span class="info-title">邮箱：</span>
                  <span class="info-body">{{user.candidate.user.email}}</span>
                </li>
                <li class="info-li">
                  <span class="info-title">电话：</span>
                  <span class="info-body">{{user.candidate.user.phone}}</span>
                </li>
                <li class="info-li">
                  <span class="info-title">期望城市：</span>
                  <span class="info-body">{{user.candidate.c_excity}}</span>
                </li>
                <li class="info-li">
                  <span class="info-title">期望薪资：</span>
                  <span class="info-body">{{user.candidate.c_exsalary}}</span>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span="16">
        <div class="right-content">
          <div class="right-top">
            <div class="rt-title">学历信息 Education-Information</div>
            <el-row class="rt-body">
              <el-col :span="12" class="ei-left">
                <div class="ei-title">第一学历</div>
                <div>
                  <el-row>
                    <el-col :span="5">
                      <div class="ei-content">
                        <ul class="ei-ul">
                          <li class="ei-li">学校：</li>
                          <li class="ei-li">学历：</li>
                          <li class="ei-li">专业：</li>
                          <li class="ei-li">时间：</li>
                        </ul>
                      </div>
                    </el-col>
                    <el-col :span="19">
                      <div class="ei-content">
                        <ul class="ei-ul">
                          <li class="ei-li">{{user.candidate.c_fschool}}</li>
                          <li class="ei-li">{{user.candidate.c_fdegree}}</li>
                          <li class="ei-li">{{user.candidate.c_fmajor}}</li>
                          <li class="ei-li">{{c_ftime}}</li>
                        </ul>
                      </div>
                    </el-col>
                  </el-row>
                </div>
              </el-col>
              <el-col :span="12" class="ei-right">
                <div class="ei-title">第二学历</div>
                <div>
                  <el-row>
                    <el-col :span="5">
                      <div class="ei-content">
                        <ul class="ei-ul">
                          <li class="ei-li">学校：</li>
                          <li class="ei-li">学历：</li>
                          <li class="ei-li">专业：</li>
                          <li class="ei-li">时间：</li>
                        </ul>
                      </div>
                    </el-col>
                    <el-col :span="19">
                      <div class="ei-content">
                        <ul class="ei-ul">
                          <li class="ei-li">{{user.candidate.c_sschool}}</li>
                          <li class="ei-li">{{user.candidate.c_sdegree}}</li>
                          <li class="ei-li">{{user.candidate.c_smajor}}</li>
                          <li class="ei-li">{{c_stime}}</li>
                        </ul>
                      </div>
                    </el-col>
                  </el-row>
                </div>
              </el-col>
            </el-row>
          </div>
          <div class="right-bottom">
            <div class="rt-title rb-special">实习经历 Internship-Experience</div>
            <el-row class="rb-body">
              <el-col :span="12" class="ie-left">
                <div class="ei-title">第一实习经历</div>
                <div>
                  <el-row>
                    <el-col :span="5">
                      <div class="ie-content">
                        <ul class="ie-ul">
                          <li class="ie-li">实习企业：</li>
                          <li class="ie-li">担任职务：</li>
                          <li class="ie-li">实习时间：</li>
                        </ul>
                      </div>
                    </el-col>
                    <el-col :span="19">
                      <div class="ie-content">
                        <ul class="ie-ul">
                          <li class="ie-li">{{user.candidate.c_finternship_enterprise}}</li>
                          <li class="ie-li">{{user.candidate.c_finternship_job}}</li>
                          <li class="ie-li">{{c_finternship_stime}}--{{c_finternship_etime}}</li>
                        </ul>
                      </div>
                    </el-col>
                  </el-row>
                </div>
              </el-col>
              <el-col :span="12" class="ie-right">
                <div class="ei-title">第二实习经历</div>
                <div>
                  <el-row>
                    <el-col :span="5">
                      <div class="ie-content">
                        <ul class="ie-ul">
                          <li class="ie-li">实习企业：</li>
                          <li class="ie-li">担任职务：</li>
                          <li class="ie-li">实习时间：</li>
                        </ul>
                      </div>
                    </el-col>
                    <el-col :span="19">
                      <div class="ie-content">
                        <ul class="ie-ul">
                          <li class="ie-li">{{user.candidate.c_sinternship_enterprise}}</li>
                          <li class="ie-li">{{user.candidate.c_sinternship_job}}</li>
                          <li class="ie-li">{{c_sinternship_stime}}--{{c_sinternship_etime}}</li>
                        </ul>
                      </div>
                    </el-col>
                  </el-row>
                </div>
              </el-col>
            </el-row>
          </div>
          <div class="annex-col">
            <el-button type="danger" class="annex-btn" @click="downloadResume()">简历下载</el-button>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// console.log( this.$route.query.id);
  import PageTitle from "../../../components/hire-system/title/PageTitle";
  export default {
    name: "attract_detail",
    components: {PageTitle},
    data() {
      return {
        sex_show: '',
        user: {},
        c_ftime:'',
        c_stime:'',
        c_finternship_stime:'',
        c_finternship_stime:'',
        c_finternship_stime:'',
        c_finternship_stime:'',
        r_id:''
      }
    },
    created() {
      console.log(this.$route.query.id)
      const _this = this
    
      this.$http.get('http://localhost:8085/hire/attract_detail/'+this.$route.query.id)
      .then(function (response) {
        console.log(response)
        _this.user=response.data;
        if(_this.user.candidate.user.sex === 0) {
          _this.sex_show = '男'
        } else {
          _this.sex_show = '女'
        }
        _this.c_ftime=_this.user.candidate.c_ftime.substring(0,10);
        _this.c_stime=_this.user.candidate.c_stime.substring(0,10);
        _this.c_finternship_stime=_this.user.candidate.c_finternship_stime.substring(0,10);
        _this.c_finternship_etime=_this.user.candidate.c_finternship_etime.substring(0,10);
        _this.c_sinternship_stime=_this.user.candidate.c_sinternship_stime.substring(0,10);
        _this.c_sinternship_etime=_this.user.candidate.c_sinternship_etime.substring(0,10);
        _this.r_id = response.data.r_id
        
      })
      .catch(function (error) {
        console.log(error);
      })
    },
    
    methods: {
      downloadResume() {
      const v = this
      this.$http.get('http://localhost:8085/index/apply_resume/download',{
        params:{
          r_id : this.r_id,
        },
        responseType: 'blob' 
      })
      .then(function (res) {

                 console.log('文件下载成功');

                    const blob = new Blob([res.data]);
                    // 获取文件名称
                    const fileName = res.headers['content-disposition'].split(";")[1].split("filename=")[1];
                    //对于<a>标签，只有 Firefox 和 Chrome（内核） 支持 download 属性
                    //IE10以上支持blob，但是依然不支持download
                    if ('download' in document.createElement('a')) {
                        //支持a标签download的浏览器
                        const link = document.createElement('a');//创建a标签
                        link.download = fileName;//a标签添加属性
                        link.style.display = 'none';
                        link.href = URL.createObjectURL(blob);
                        document.body.appendChild(link);
                        link.click();//执行下载
                        URL.revokeObjectURL(link.href); //释放url
                        document.body.removeChild(link);//释放标签
                    } else {
                        navigator.msSaveBlob(blob, fileName);
                    }

      })
      .catch(function (error) {
        console.log(error);
      });
    }
    }
  }
</script>

<style scoped>
@import "../../../assets/css/hire-system/attract-detail.css";
</style>
