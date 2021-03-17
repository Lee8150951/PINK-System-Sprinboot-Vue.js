<template>
  <div class="position-detail-body">
    <el-row></el-row>
    <el-row class="header">
      <el-col :span="16">
        <div class="grid-content">
          <div class="p-basic">
            <span class="p-name">{{position.r_name}}</span>
            <span class="p-salary">{{position.r_salary}}</span>
          </div>
          <div class="p-info">{{position.r_address}} · {{position.r_experience}} · {{position.r_education}}</div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content">
          <el-button type="warning" class="communicate-btn" @click="dialogFormVisible = true">立即沟通</el-button>
          <el-button type="success" class="communicate-btn" @click="submitApplication()">投递申请</el-button>
        </div>
      </el-col>
      <el-dialog title="选择简历" :visible.sync="resumeVisible">
        <div class="top-body">
          <resume-item v-for="resume in resumes" :key="resume.index" :resume="resume"></resume-item>
        </div>
      </el-dialog>
      <el-dialog :title="position.contact" :visible.sync="dialogFormVisible">
        <el-row>
          <el-col :span="16">
            <div class="left-content">
              <el-row>
                <el-col :span="24">
                  <ul class="dialogue-ul">
                    <communicate-li v-for="chat in chats" :key="chat.index" :chat="chat"></communicate-li>
                  </ul>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <div class="enter-box">
                    <el-form ref="form" :model="communication" label-width="80px">
                      <div class="enter-text">
                        <el-input :rows="3" type="textarea" v-model="communication.text"></el-input>
                      </div>
                      <el-button @click="onSubmit" size="mini" type="warning" class="communicate-sub">发送</el-button>
                    </el-form>
                  </div>
                </el-col>
              </el-row>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="right-content">
              <div class="top-tip">使用微信APP</div>
              <div class="bottom-tip">沟通更便利!</div>
              <img src="../../../assets/img/QR/code.jpg" alt="qr-code" class="qr-code">
              <div class="bottom-tip">微信扫码使用</div>
            </div>
          </el-col>
        </el-row>
      </el-dialog>
    </el-row>
    <el-row class="p-main">
      <el-col :span="16">
        <div class="left-main-content">
          <el-row>
            <el-col :span="24">
              <div class="grid-content">
                <el-row>
                  <el-col :span="2">
                    <div class="grid-content">
                      <img src="../../../assets/img/avatar/avatar01.png" alt="avatar" class="p-avatar">
                    </div>
                  </el-col>
                  <el-col :span="15">
                    <div class="hr-content">
                      <li class="hr-name">{{position.companyHr.company.e_excecutive}}</li>
                      <li class="hr-info">HR·刚刚在线</li>
                    </div>
                  </el-col>
                  <el-col :span="7">
                    <div class="btn-content">
                      <el-popover placement="bottom" width="160" v-model="visible">
                        <div>
                          <img src="../../../assets/img/QR/code.jpg" alt="code">
                        </div>
                        <el-button slot="reference" type="text">
                          <i class="el-icon-ice-cream-round"></i>微信一下
                        </el-button>
                      </el-popover>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </el-col>
            <el-col :span="24">
              <div class="intro-content">
                <div class="p-intro">
                  <h3>技能需求</h3>
                  <p class="p-intro-main">
                    {{position.r_experience}}
                  </p>
                </div>
              </div>
              <div class="intro-content">
                <div class="p-intro">
                  <h3>福利待遇</h3>
                  <p class="p-intro-main">
                    {{position.r_tag}}
                  </p>
                </div>
              </div>
              <div class="intro-content">
                <div class="p-intro">
                  <h3>公司简介</h3>
                  <p class="p-intro-main">
                    {{position.companyHr.company.e_introduction}}
                  </p>
<!--                  <el-button type="text" class="p-more" @click="jumpCompany">了解更多</el-button>-->
                </div>
              </div>
              <div class="business-content">
                <div class="p-business">
                  <h3>工商信息</h3>
                  <el-collapse accordion class="collapse">
                    <el-collapse-item title="法人 Corporate" name="1">
                      <div>{{position.companyHr.company.e_executive}}</div>
                    </el-collapse-item>
                    <el-collapse-item title="企业类型 Type" name="2">
                      <div>{{position.companyHr.company.e_type}}</div>
                    </el-collapse-item>
                    <el-collapse-item title="经营状态 State" name="3">
                      <div>{{position.companyHr.company.e_operationstatus}}</div>
                    </el-collapse-item>
                    <el-collapse-item title="注册地址 Address" name="4">
                      <div>{{position.companyHr.company.e_registeredaddress}}</div>
                    </el-collapse-item>
                    <el-collapse-item title="经营范围 Range" name="5">
                      <div>{{position.companyHr.company.e_businessscope}}</div>
                    </el-collapse-item>
                  </el-collapse>
                </div>
              </div>
              <div class="intro-content">
                <div class="p-intro">
                  <h3>更多推荐</h3>
                  <post-app v-for="post in posts" :key="post.index" :post="post"></post-app>
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="right-main-content">
          <div class="intro-content">
            <div class="p-intro">
              <h3>公司基本信息</h3>
              <ul class="r-company">
                <li>
                  <span>
                    <img :src="'http://localhost:8085/companyAvatar/'+position.companyHr.company.e_logo" alt="" class="r-company-logo">
                  </span>
                  <span class="r-company-name">
                    {{position.companyHr.company.e_name}}
                  </span>
                </li>
                <li class="r-company-info">
                  <span>
                    <i class="el-icon-s-data"></i>
                  </span>
                  <span class="r-company-intro">
                    {{position.companyHr.company.e_operationstatus}}
                  </span>
                </li>
                <li class="r-company-info">
                  <span>
                    <i class="el-icon-s-custom"></i>
                  </span>
                  <span class="r-company-intro">
                    {{position.companyHr.company.e_size}}人
                  </span>
                </li>
                <li class="r-company-info">
                  <span>
                    <i class="el-icon-menu"></i>
                  </span>
                  <span class="r-company-intro">
                    {{position.companyHr.company.e_businessscope}}
                  </span>
                </li>
              </ul>
            </div>
          </div>
          <div class="intro-content">
            <div class="p-intro">
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
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>



// 导入CommunicateLi组件
import CommunicateLi from "../../../components/apply-system/communicate/CommunicateLi";
// 导入PostApp组件
import PostApp from "../../../components/apply-system/post/PostApp";
import ResumeItem from "../../../components/apply-system/resume/ResumeItem";
export default {
  name: "position-detail",
  components: {PostApp, CommunicateLi,ResumeItem},
  data() {
    return {
      resumes: [],
      dialogFormVisible: false,
      resumeVisible: false,
      communication: {
        text: '',
      },
      visible: false,
      formLabelWidth: '120px',
      chats: [
        {
          index: 1,
          type: '已发送',
          content: '您好，我对java软件开发实习生很感兴趣，希望和您聊聊，谢谢！'
        },
        {
          index: 2,
          type: '已接受',
          content: '不可能，你太菜了'
        }
      ],
      position: {},
      company: {},
      posts: []
    }
  },
  created() {
    this.r_id = this.$route.query.index;
    localStorage.setItem('r_id',this.r_id)
    const _this = this
    this.$http.get('http://localhost:8085/index/position_detail/'+this.r_id)
      .then(function (response) {
       _this.position = response.data
       console.log(_this.position);
      })
      .catch(function (error) {
        console.log(error);
      });
    this.$http.get('http://localhost:8085/index/apply_home/hotRecruitment')
      .then(function (response) {
      for(var len = 0;len<response.data.length;len++){
        if(len>4){
          break;
        }
        _this.posts.push(response.data[len])
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  },


  methods: {

    onSubmit() {
      alert(this.communication.text)
    },
    jumpCompany() {
      // 携带索引跳转公司
      this.$router.push({
        path: "/index/company_detail",
        query: {
          index: this.company.e_id
        }
      })
    },
    submitApplication() {

      const v = this
      this.$http.post('http://localhost:8085/index/apply_resume/judge',{
        data:{
          u_id : localStorage.getItem('c_id'),
          p_id : localStorage.getItem('r_id'),
        }
      })
      .then(function (response) {
      console.log(response)
      if(response.data+''==="已经投递该岗位"){
        alert("已经投递了该岗位，请等待消息")
      }else{
        const y = v
        v.resumeVisible=true
        v.resumes = []
        v.$http.get('http://localhost:8085/index/apply_edit/getallresume/'+localStorage.getItem('c_id'))
        .then(function (response) {
        console.log(response)
        for(var len = 0;len<response.data.length;len++){
            response.data[len].flag = 1
            y.resumes.push(response.data[len])
          }
        })
        .catch(function (error) {
          console.log(error);
        });
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
@import "../../../assets/css/apply-system/position-detail.css";
</style>
