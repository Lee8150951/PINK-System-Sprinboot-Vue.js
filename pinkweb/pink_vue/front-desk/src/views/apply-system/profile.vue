<template>
  <div class="profile-body">
    <el-row></el-row>
    <el-row>
      <el-col :span="18" class="left-col">
        <div class="grid-content">
          <el-row>
            <el-col :span="24">
              <!-- <div>
                <el-input placeholder="搜索职位，公司" v-model="research" class="input-with-select">
                  <el-button slot="append" icon="el-icon-search"></el-button>
                </el-input>
              </div> -->
              <div class="recommend-content">
                <div class="recommend-title">
                  <span class="title_1">推荐职位</span>
                </div>
                <div class="recommend-list">
                  <post-item v-for="post in posts" :key="post.index" :post="post"></post-item>
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
      <el-col :span="6" class="right-col">
        <div class="grid-content">
          <div class="personal-info">
            <ul>
              <li class="relative">
                <img :src="avatarUrl" alt="" class="personal-img">
                <span class="occupy" @mouseout="mouseOut" @mouseover="mouseOver" @click="editAvatarDialog = true">修改</span>
                <el-button type="text" class="personal-btn" @click="editInfo">编辑</el-button>
              </li>
              <li>
                <span class="personal-name">{{user.name}}</span>
                <el-button @click="drawer=true" size="mini" type="warning" icon="el-icon-medal-1" circle class="vip-btn"></el-button>
              </li>
              <li class="personal-intro">
                <span class="span-left">{{user.age}}岁</span>
                <span>{{user.c_fschool}}</span>
                <span class="span-right">{{user.c_fdegree}}</span>
              </li>
              <li>
                <el-button type="warning" plain class="personal-type">{{user.c_excity}}</el-button>
              </li>
              <li>
                <el-row>
                  <el-col :span="6">
                    <div class="grid-content">
                      <div class="personal-num">6</div>
                      <div class="personal-title">沟通过</div>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content">
                      <div class="personal-num">10</div>
                      <div class="personal-title">已投递</div>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content">
                      <div class="personal-num">3</div>
                      <div class="personal-title">面试</div>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content">
                      <div class="personal-num">20</div>
                      <div class="personal-title">感兴趣</div>
                    </div>
                  </el-col>
                </el-row>
              </li>
            </ul>
          </div>
          <el-dialog title="修改头像" :visible.sync="editAvatarDialog" width="30%">
            <div class="upload-col">
              <el-upload
                action="http://localhost:8085/index/apply_profile/updateAvatar"
                :before-upload="beforeAvatarUpload"
                ref="upload"
                :data="uploadData"
                list-type="picture-card"
                :limit="1"
                :on-remove="handleRemove" style="text-align: center">
                <i class="el-icon-plus"></i>
              </el-upload>
            </div>
            <div slot="footer" class="dialog-footer">
              <el-button @click="editAvatarDialog = false">取 消</el-button>
              <el-button type="primary" @click="handleUpload()">确 定</el-button>
            </div>
          </el-dialog>
          <el-drawer title="开通会员" :visible.sync="drawer">
          </el-drawer>
          <annex-panel style="margin-top: 20px"></annex-panel>
          <div class="history-panel">
            <el-button plain class="history-btn">
              <li class="big-num">0</li>
              <li>对我感兴趣</li>
            </el-button>
            <el-button plain class="history-btn">
              <li class="big-num">0</li>
              <li>看过我</li>
            </el-button>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import PostItem from "../../components/apply-system/post/PostItem";
import AnnexPanel from "../../components/apply-system/profile/AnnexPanel";
export default {
  name: "profile",
  components: {AnnexPanel, PostItem},
  data() {
    return {
      uploadData:{
        c_id :localStorage.getItem("c_id")
      },
      research: '',
      editAvatarDialog: false,
      activeName: 'first',
      drawer: false,
      birthday_tmp : "",
      posts: [],
      user: {
        c_id: "",
        c_fdegree: '',
        c_excity: '',
        c_fschool: '',
        name: '',
        age: 0,
        avatar:""
      },
      avatarUrl:"",
    }
  },
  created(){
    const v = this
    this.$http.get('http://localhost:8085/index/apply_edit/'+localStorage.getItem('c_id'))
      .then(function (response) {
      v.user.c_id = response.data.c_id;
      v.user.c_fdegree = response.data.c_fdegree;
      v.user.c_excity = response.data.c_excity;
      v.user.c_fschool = response.data.c_fschool;
      v.user.name = response.data.user.name;
      v.user.sex = response.data.sex;
      v.birthday_tmp = response.data.user.birthday;
      v.avatarUrl = "http://localhost:8085/avatar/"+response.data.c_id+"/"+response.data.user.avatar;
      let birthdays = new Date(v.birthday_tmp.replace(/-/g, "/"));
      let d = new Date();
      let age =
        d.getFullYear() -
        birthdays.getFullYear() -
        (d.getMonth() < birthdays.getMonth() ||
        (d.getMonth() == birthdays.getMonth() &&
        d.getDate() < birthdays.getDate())
          ? 1
          : 0);
        v.user.age = age;
      })
      .catch(function (error) {
        console.log(error);
      });


      this.$http.get('http://localhost:8085/index/apply_home/hotRecruitment')
      .then(function (response) {
      for(var len = 0;len<response.data.length;len++){
        if(len>5){
          break;
        }
        v.posts.push(response.data[len])
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  },
  methods: {
    editInfo() {
      this.$router.push({
        path: '/index/apply_edit',
        query: {
          c_id: this.user.c_id,
          name: this.user.name
        }
      })
    },
        mouseOver() {
      const dom = document.getElementsByClassName("occupy");
      dom[0].style.opacity = 1;
    },
    mouseOut() {
      const dom = document.getElementsByClassName("occupy");
      dom[0].style.opacity = 0;
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    submitUpload() {
			this.$refs.upload.submit();
      location.reload();
		},
    handleUpload () {
			this.submitUpload()
			this.editAvatarDialog = false
		},
    beforeAvatarUpload(file) {
      // 判断大小
      const isLt2M = file.size / 1024 / 1024 < 1;
      // 判断文件类型
      const fileSuffix = file.name.substring(file.name.lastIndexOf(".") + 1);
      const whiteList = ["png", "jpg", "jpeg", "PNG", "JPG", "JPEG"];
      // 判断大小
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过1MB!');
      }
      // 判断文件类型
      if (whiteList.indexOf(fileSuffix) === -1) {
        this.$message.error("上传文件只能是pdf,最多上传三份简历");
        return false;
      }
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/apply-system/profile.css";
</style>
