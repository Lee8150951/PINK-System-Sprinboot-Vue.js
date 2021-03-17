<template>
  <div class="main-body">
    <div class="header-title">
      <i class="el-icon-document-copy"></i>
      编辑公司 Company
    </div>
    <el-row>
      <el-col :span="24">
        <div class="grid-content">
          <div class="left-content">
            <img src="../../assets/img/avatar/avatar01.png" alt="" class="avatar">
            <h2>Admin</h2>
            <div class="info">
              <el-col :span="10">
                <div class="left-info">
                  <ul>
                    <li>工号：</li>
                    <li>部门：</li>
                    <li>职位：</li>
                  </ul>
                </div>
              </el-col>
              <el-col :span="14">
                <div class="right-info">
                  <ul>
                    <li>{{form.admin_id}}</li>
                    <li>{{form.apartment}}</li>
                    <li>{{form.position}}</li>
                  </ul>
                </div>
              </el-col>
            </div>
          </div>
          <div class="right-content">
            <el-row class="tips">
              <el-col :span="7">
                <div class="tip-content bg-red">
                  <el-row>
                    <el-col :span="3"><div class="tip-content"></div></el-col>
                    <el-col :span="4">
                      <i class="el-icon-s-shop tips_icon"></i>
                    </el-col>
                    <el-col :span="3"><div class="tip-content"></div></el-col>
                    <el-col :span="14" class="tips_right">
                      <h3>75</h3>
                      <p>已处理</p>
                    </el-col>
                  </el-row>
                </div>
              </el-col>
              <el-col :span="7">
                <div class="tip-content bg-blue">
                  <el-row>
                    <el-col :span="3"><div class="tip-content"></div></el-col>
                    <el-col :span="4">
                      <i class="el-icon-s-finance tips_icon"></i>
                    </el-col>
                    <el-col :span="3"><div class="tip-content"></div></el-col>
                    <el-col :span="14" class="tips_right">
                      <h3>24</h3>
                      <p>待办</p>
                    </el-col>
                  </el-row>
                </div>
              </el-col>
              <el-col :span="7">
                <div class="tip-content bg-orange">
                  <el-row>
                    <el-col :span="3"><div class="tip-content"></div></el-col>
                    <el-col :span="4">
                      <i class="el-icon-s-custom tips_icon"></i>
                    </el-col>
                    <el-col :span="3"><div class="tip-content"></div></el-col>
                    <el-col :span="14" class="tips_right">
                      <h3>1</h3>
                      <p>信息</p>
                    </el-col>
                  </el-row>
                </div>
              </el-col>
            </el-row>
            <el-row class="form">
              <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="昵称：">
                  <el-input v-model="form.admin_name"></el-input>
                </el-form-item>
                <el-form-item label="密码：">
                  <el-input v-model="form.admin_pwd"></el-input>
                </el-form-item>
                <el-form-item label="权限：">
                  <el-input v-model="form.admin_power" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="warning" class="edit-btn" @click="editAdmin()">修改</el-button>
                </el-form-item>
              </el-form>
            </el-row>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "admin_profile",
    data() {
      return {
      //每页显示的条数
      size: 6,
      //总条数
      total: 0,
      pageNow: 1,
        form: {
          admin_id: '',
          apartment: '',
          position: '',
          admin_name: '',
          admin_pwd: '',
          admin_power: ''
        }
      }
    },
    created(){
        var that = this
          this.$http.post('http://localhost:8085/admin/manage_admin/getAllAdmin?key=getAdminByAccount&flag='+localStorage.getItem("admin_account"),{
            params:{
              pageNow: this.pageNow,
              pageSize: this.size
            }
          })
       .then(function (response) {
        console.log(response)
        that.form.admin_id = response.data.list[0].admin_id
        that.form.admin_account = response.data.list[0].admin_account
        that.form.admin_pwd = response.data.list[0].admin_pwd
        that.form.admin_name = response.data.list[0].admin_name
        that.form.admin_power = response.data.list[0].admin_power
        that.form.apartment = response.data.list[0].apartment
        that.form.position = response.data.list[0].position
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    methods:{
      editAdmin(){
        console.log(this.form.admin_id)
      this.$http.post('http://localhost:8085/admin/manage_admin/edit',{
        params:{
          admin_name: this.form.admin_name,     
          admin_pwd: this.form.admin_pwd,
          admin_id: this.form.admin_id,     
          admin_power: this.form.admin_power,
        }
      })
      .then(function (response) {
        console.log(response)
        alert("修改成功")
      })
      .catch(function (error) {
        console.log(error);
      });
      console.log(index, row);
    },
    }
  }
</script>

<style scoped>
@import "../../assets/css/admin/admin_public.css";
@import "../../assets/css/admin/profile.css";
</style>
