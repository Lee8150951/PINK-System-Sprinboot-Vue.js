<template>
  <div class="annex-panel">
    <ul>
      <li class="annex-title">附件管理</li>
      <li class="annex-buttons">
        <el-button type="warning" class="annex-btn" @click="dialogVisible = true">上传简历</el-button>
        <el-button type="warning" plain class="annex-btn" @click="turnResume">在线简历</el-button>
      </li>
    </ul>
    <el-dialog title="简历" :visible.sync="dialogVisible">
      <el-row>
        <el-col :span="12">
          <div class="left-content">
            <ul class="left-up">
              <li>
               <el-upload class="upload-col"
                ref="upload"
                drag
                action="http://localhost:8085/index/apply_edit/uploadResumePDF"
                :data="uploadData"
                accept=".pdf"
                multiple
                :auto-upload="false"
                :limit="1"
                :on-success="handleFilUploadSuccess"
                :on-remove="handleRemove"
                >
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              </el-upload>
              <span slot="footer" class="dialog-footer">
                <el-button type="primary"
                           @click="handleUpload"
                           class="upload-btn" plain
                           style="font-size: 13px; padding: 8px 60px; margin-top: 20px">
                  上传简历
                </el-button>
              </span>
              </li>
            </ul>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="right-content">
            <ul class="right-up">
              <li>
                <img src="../../../assets/img/background/profile_make.png" alt="" class="right-bg">
              </li>
            </ul>
          </div>
          <el-button size="small" type="primary" class="upload-btn" plain @click="turnResume" style="margin-left: 30px">在线快速制作</el-button>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "AnnexPanel",
  data() {

    return {
    uploadData:{
      c_id : localStorage.getItem("c_id")
    },
      dialogVisible: false
    }
  },
  methods: {
    	handleRemove(file,fileList) {
				console.log(file,fileList);
			},
			submitUpload() {
				this.$refs.upload.submit();
			},
			// 文件上传成功时的函数
			handleFilUploadSuccess (res,file,fileList) {
				console.log(res)
        if(res==="uploadResumePDF-success"){
				  this.$message.success("上传成功")
        }else{
          this.$message.success("上传失败，已经存在三份简历")
        }

			},
			handleUpdate () {
				this.dialogVisible = true;
			},
			// 处理文件上传的函数
			handleUpload () {

				this.submitUpload()
				this.dialogVisible = false
			},
      turnResume() {
      this.$router.push({
        path: '/index/apply_resume'
      })
    }
	},



}
</script>

<style scoped>
@import "../../../assets/css/apply-system/components/annexpanel.css";
</style>
