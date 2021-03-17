<template>
  <div class="resume-item">
    <el-row class="resume-body">
      <el-col :span="3">
        <div class="img-content">
          <pdf ref="pdf" :src="pdfUrl" class="pdf"></pdf>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="title-content">
          <ul>
            <li class="resume-name">
              {{resume.name.slice(13)}}
            </li>
            <li class="resume-operate">
              <span v-if="resume.flag===0"> 
                <el-button type="text" class="resume-btn" @click="downloadResume()">
                  <i class="el-icon-circle-check"></i>下载
                </el-button>
              </span>
              <!-- <span v-if="resume.flag===0">
                <el-button type="text" class="resume-btn">
                  <i class="el-icon-refresh"></i>重命名
                </el-button>
              </span> -->
              <span v-if="resume.flag===0">
                <el-popconfirm title="确定删除该简历吗？" @confirm="deleteResume()">
                  <el-button type="text" class="resume-btn" slot="reference" >
                    <i class="el-icon-delete"></i>删除
                  </el-button>
                </el-popconfirm>
              </span>
              <span v-if="resume.flag===1">
                <el-button type="text" class="resume-btn" @click="choseResume(),determineVisible=true">
                  <i class="el-icon-delete"></i>投递
                </el-button>
              </span>
            </li>
          </ul>
        </div>
      </el-col>
      <el-col :span="13">
        <div class="time-content">
          {{$moment(resume.created_time).format('YYYY-MM-DD')}}
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
// 导入pdf组件
import pdf from 'vue-pdf'
export default {
  name: "ResumeItem",
  components: {pdf},
  props: {
    resume: {
      type: Object
    }
  },
  data(){
    return {
      deleteVisible:false,
      determineVisible:false,
      pdfUrl:"http://localhost:8085/userpdf/"+localStorage.getItem("c_id")+"/"+this.resume.name
    }
  },
  created(){
    this.src = pdf.createLoadingTask(this.src)
  },
  methods:{
    choseResume(){
      const v = this
      this.$http.post('http://localhost:8085/index/apply_resume/apply',{
        data:{
          u_id : localStorage.getItem('c_id'),
          p_id : localStorage.getItem('r_id'),
          r_id : this.resume.resume_id,
        }
      })
      .then(function (response) {
      console.log(response)
      for(var len = 0;len<response.data.length;len++){
          v.resumes.push(response.data[len])
        }
      })
      .catch(function (error) {
        console.log(error);
      });
      alert("投递成功！")
      location.reload();
    },
    deleteResume() {
      const v = this
      this.$http.post('http://localhost:8085/index/apply_resume/delete',{
        data:{
          r_id : this.resume.resume_id,
        }
      })
      .then(function (response) {
      console.log(response)
            location.reload();
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    downloadResume() {
      const v = this
      this.$http.get('http://localhost:8085/index/apply_resume/download',{
        params:{
          r_id : this.resume.resume_id,
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
@import "../../../assets/css/apply-system/components/resumeitem.css";
</style>
