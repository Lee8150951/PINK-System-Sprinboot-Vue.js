<template>
  <div class="resume-body">
    <div class="resume-list">
      <div class="top-title">
        <el-row>
          <el-col :span="12">
            <div class="top-left">
              简历列表
            </div>
          </el-col>
          <el-col :span="12">
            <div class="top-right">
              <el-button type="text" class="top-btn">
                <i class="el-icon-upload"></i>导入简历
              </el-button>
              <el-button type="text" class="top-btn">
                <i class="el-icon-circle-plus"></i>新建简历
              </el-button>
            </div>
          </el-col>
        </el-row>
      </div>
      <div class="top-body">
        <resume-item v-for="resume in resumes" :key="resume.index" :resume="resume"></resume-item>
      </div>
    </div>
    <div class="style-list">
      <div class="bottom-title">
        从模板添加新简历
      </div>
      <div class="bottom-body">
        <el-row>
          <resume-box v-for="(list, index) in modelList" :key="list.m_id">
            <img :src="list.m_img" alt="Resume-Model" class="resume-img" slot="resume-img" @mouseover="mouseOver(index)" @mouseout="mouseOut(index)" @click="turnMake(list)">
            <span slot="resume-name">{{list.m_title}}</span>
          </resume-box>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
// 导入ResumeItem模块
import ResumeItem from "../../components/apply-system/resume/ResumeItem";
// 导入ResumeBox模块
import ResumeBox from "../../components/apply-system/resume/ResumeBox";
export default {
  name: "resume",
  components: {ResumeBox, ResumeItem},
  data() {
    return {
      resumes: [],
      modelList: [
        {
          m_id: 1,
          m_img: require('../../assets/img/resume/resume_view1.png'),
          m_title: '学生求职模板',
          m_url: ""
        },
        {
          m_id: 2,
          m_img: require('../../assets/img/resume/resume_view3.png'),
          m_title: '商务通用模板',
          m_url: ""
        },
        {
          m_id: 3,
          m_img: require('../../assets/img/resume/resume_view2.png'),
          m_title: '程序员/技术模板',
          m_url: ""
        }
      ]
    }
  },
  created() {
    const v = this
    this.$http.get('http://localhost:8085/index/apply_edit/getallresume/'+localStorage.getItem('c_id'))
      .then(function (response) {
      console.log(response)
      for(var len = 0;len<response.data.length;len++){
          response.data[len].flag = 0
          v.resumes.push(response.data[len])
        }
      
      })
      .catch(function (error) {
        console.log(error);
      });

  },
  methods: {
    mouseOver(index) {
      const dom = document.getElementsByClassName("resume-img")
      dom[index].style.opacity = 0.7
    },
    mouseOut(index) {
      const dom = document.getElementsByClassName("resume-img")
      dom[index].style.opacity = 1
    },
    turnMake(list) {
    this.$http.get('http://localhost:8085/index/apply_resume/'+list.m_id,{
      params:{
          phone : localStorage.getItem('phone')
        },
    })
    .then(function (response) {
            location.reload();
      })
    .catch(function (error) {
      console.log(error);
    });
      this.$router.push({
        path: '/index/apply_export',
        query: {
          m_id: list.m_id,
          m_title: list.m_title
        }
      })
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/apply-system/resume.css";
</style>
