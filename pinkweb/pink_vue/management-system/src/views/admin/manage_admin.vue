<template>
  <div class="main-body">
    <div class="header-title">
      <i class="el-icon-document-copy"></i>
      员工管理 Admin
    </div>
    <el-main class="main">
      <div>
        <el-header>
          <div class="left-buttons">
            <button class="el-button el-button--success" @click="AddVisible = true" v-if="account1===account">
              <i class="el-icon-edit"></i>
              新增
            </button>
            <!-- <button class="el-button el-button--danger" @click="deleteClick($event)">
              <i class="el-icon-delete"></i>
              删除
            </button> -->
          </div>
          <el-dialog title="新增管理员" :visible.sync="AddVisible">
            <el-form :model="add_admin">
              <el-form-item label="账号：" :label-width="formLabelWidth">
                <el-input v-model="add_admin.admin_account" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="密码：" :label-width="formLabelWidth">
                <el-input type="password" v-model="add_admin.admin_pwd" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="姓名：" :label-width="formLabelWidth">
                <el-input v-model="add_admin.admin_name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="权限：" :label-width="formLabelWidth">
                <el-select v-model="add_admin.admin_power" placeholder="请选择权限">
                  <el-option label="超级管理员" value="0"></el-option>
                  <el-option label="普通管理员" value="1"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="部门：" :label-width="formLabelWidth">
                <el-input v-model="add_admin.apartment" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="职位：" :label-width="formLabelWidth">
                <el-input v-model="add_admin.position" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="生日：" :label-width="formLabelWidth">
                <el-date-picker type="date" placeholder="选择日期" v-model="add_admin.admin_time" style="width: 100%;"></el-date-picker>
              </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="AddVisible = false">取 消</el-button>
              <el-button type="primary" @click="AddVisible = false,addAdmin()">确 定</el-button>
            </div>
          </el-dialog>
          <div class="right-research">
            <el-input placeholder="请输入内容" v-model="search" class="input-with-select">
              <el-button slot="append" icon="el-icon-search" @click="searchHandler"></el-button>
            </el-input>
          </div>
        </el-header>
        <el-main>
          <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange" stripe>
            <el-table-column type="selection" width="70"></el-table-column>
            <el-table-column prop="admin_id" label="编号" width="120"></el-table-column>
            <el-table-column prop="admin_name" label="用户名" width="170"></el-table-column>
            <el-table-column label="上次登录时间" width="200">
              <template slot-scope="scope">{{ scope.row.admin_time }}</template>
            </el-table-column>
            <el-table-column prop="apartment" label="部门" width="170"></el-table-column>
            <el-table-column prop="position" label="职位" width="170"></el-table-column>
            <el-table-column label="操作" >
              <template slot-scope="scope" v-if="account1===account">
                <el-button size="mini" type="text" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button size="mini" type="text" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-main>
        <el-dialog title="编辑管理员" :visible.sync="EditVisible">
          <el-form :model="edit_admin">
            <el-form-item label="ID：" :label-width="formLabelWidth">
              <el-input v-model="edit_admin.admin_id" autocomplete="off" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="姓名：" :label-width="formLabelWidth">
              <el-input v-model="edit_admin.admin_name" autocomplete="off" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="账号：" :label-width="formLabelWidth">
              <el-input v-model="edit_admin.admin_account" autocomplete="off" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="密码：" :label-width="formLabelWidth">
              <el-input  v-model="edit_admin.admin_pwd" autocomplete="off" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="权限：" :label-width="formLabelWidth">
              <el-select v-model="edit_admin.admin_power" placeholder="请选择权限">
                <el-option label="超级管理员" value="0"></el-option>
                <el-option label="普通管理员" value="1"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="EditVisible = false">取 消</el-button>
            <el-button type="primary" @click="EditVisible = false,editAdmin()">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </el-main>
  </div>
</template>

<script>
export default {
  name: "manage_admin",
  data() {
    return {
      account1 : localStorage.getItem("admin_account"),
      account : "17786143697",
      //每页显示的条数
      size: 6,
      //总条数
      total: 0,
      pageNow: 1,
      search: '',
      tableData: [{
        num: 1001,
        date: '2016-05-03',
        name: '马逸超',
        apartment: '市场管理部',
        position: '普通管理员'
      },],
      multipleSelection: [],
      AddVisible: false,
      add_admin: {
        admin_account: '',
        admin_pwd: '',
        admin_name: '',
        admin_power: '',
        apartment: '',
        position: '',
        admin_time: ''
      },
      EditVisible: false,
      edit_admin: {
        admin_id: '1',
        admin_account: '1',
        admin_pwd: '1',
        admin_name: '1',
        admin_power: '1',
        apartment: '1',
        position: '1',
        admin_time: '1'
      },
      formLabelWidth: '120px'
    }
  },
  created(){
          var that = this
          this.$http.post('http://localhost:8085/admin/manage_admin/getAllAdmin?key='+this.$route.query.key+"&flag="+this.$route.query.flag,{
            params:{
              pageNow: this.pageNow,
              pageSize: this.size
            }
          })
       .then(function (response) {
          that.tableData = []
          for(var len = (that.pageNow-1)*that.size ;len<=that.pageNow*that.size-1;len++){
            if(len>=response.data.list.length){
              break;
            }
              that.tableData.push(response.data.list[len])
            }
            console.log(that.tableData)
            //that.total=response.data.total


        that.total=response.data.total
      })
      .catch(function (error) {
        console.log(error);
      });
  },
  methods: {
    addAdmin(){
      this.$http.post('http://localhost:8085/admin/manage_admin/add',{
        params:{
          admin_account: this.add_admin.admin_account,
          admin_pwd: this.add_admin.admin_pwd,
          admin_name: this.add_admin.admin_name,
          admin_power: this.add_admin.admin_power,
          apartment: this.add_admin.apartment,
          position: this.add_admin.position,
          admin_time: this.add_admin.admin_time,
        }
      })
      .then(function (response) {
        console.log(response)
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    editAdmin(){
      this.$http.post('http://localhost:8085/admin/manage_admin/edit',{
        params:{
          admin_name: this.edit_admin.admin_name,
          admin_pwd: this.edit_admin.admin_pwd,
          admin_id: this.edit_admin.admin_id,
          admin_power: this.edit_admin.admin_power,
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
         searchHandler(){
       this.$router.push({
        path: '/admin/manage_admin',
        query: {
          key: "搜索",
          flag: this.search,
        }
      })
      location.reload()
    },
        handleSizeChange(size){
          console.log("当每页条数改变时"+size);
          this.size = size;
          this.findAll(this.pageNow, this.size)

        },
        handleCurrentChange(){
          console.log("当前页面"+this.pageNow);

           this.findAll(this.pageNow, this.size)

        },

        findAll(page, size){
          var that = this
          this.$http.post('http://localhost:8085/admin/manage_admin/getAllAdmin?key='+this.$route.query.key+"&flag="+this.$route.query.flag,{
            params:{
              pageNow: this.pageNow,
              pageSize: this.size
            }
          })
       .then(function (response) {

          that.tableData = []
          for(var len = (that.pageNow-1)*that.size ;len<=that.pageNow*that.size-1;len++){
            if(len>=response.data.list.length){
              break;
            }
              that.tableData.push(response.data.list[len])
            }
        that.total=response.data.total
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleEdit(index, row) {
      this.EditVisible = true;
        var that = this
        this.$http.post('http://localhost:8085/admin/manage_admin/getAllAdmin?key=getAdminByID'+"&flag="+row.admin_id,{
            params:{
              pageNow: this.pageNow,
              pageSize: this.size
            }
          })
       .then(function (response) {
        console.log(response)
        that.edit_admin.admin_id = response.data.list[0].admin_id
        that.edit_admin.admin_account = response.data.list[0].admin_account
        that.edit_admin.admin_pwd = response.data.list[0].admin_pwd
        that.edit_admin.admin_name = response.data.list[0].admin_name
        that.edit_admin.admin_power = response.data.list[0].admin_power
        that.edit_admin.apartment = response.data.list[0].apartment
      })
      .catch(function (error) {
        console.log(error);
      });


    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    addClick(e) {
      let path = e.target.getAttribute("path")
      this.$router.push(path)
    },
    deleteClick(e) {
      console.log(e)
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/admin/admin_public.css";
@import "../../assets/css/admin/manage_admin.css";
</style>
