<template>
  <div class="export-body">
    <el-row class="export-title">
      <el-col :span="24">
        <div class="top-left-content">
          我的简历
          <el-button type="text" class="top-left-btn" @click="renameVisible = true">
            <i class="el-icon-copy-document"></i>
          </el-button>
        </div>
      </el-col>
    </el-row>
    <el-dialog title="重新命名简历名称" :visible.sync="renameVisible">
      <el-form :model="rename_form">
        <!-- <el-form-item>
          <el-input v-model="rename_form.name" placeholder="重命名"></el-input>
        </el-form-item> -->
        <el-form-item>
          <el-button class="rename-btn" type="primary" @click="renameVisible = false">确 定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-row class="pdf-col">
      <el-col :span="18">
        <div class="pdf-content">
          <pdf ref="pdf" :src="resume.url" class="pdf"></pdf>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="btn-content">
          <ul>
            <li>
              <el-button type="primary" plain class="right-btn_1" @click="downloadResume()">下载</el-button>
            </li>
            <li>
              <el-button type="primary" class="right-btn_2" @click="turnEdit">修改</el-button>
            </li>
          </ul>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// 导入pdf组件
import pdf from 'vue-pdf'
export default {
  name: "export",
  components: {pdf},
  data() {
    return {
      renameVisible: false,
      rename_form: {
        uid: '',
        name: ''
      },
      resume: {
        uid: '',
        resume_id: '',
        name: '马超超简历',
        url: 'http://localhost:8085/out/'+localStorage.getItem("phone")+'/'+ this.$route.query.m_id+'.pdf'
      }
    }
  },
  methods: {
    turnEdit() {
      this.$router.push({
        path: '/index/apply_edit',
        query:{
          c_id: localStorage.getItem("c_id")
        }
      })
    },
    downloadResume() {
      const v = this
      this.$http.get('http://localhost:8085/index/apply_resume/downloadtmp',{
        params:{
          m_id : this.$route.query.m_id,
          phone : localStorage.getItem("phone")
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
        console.log('文件下载失败');
      });
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/apply-system/export.css";
</style>
