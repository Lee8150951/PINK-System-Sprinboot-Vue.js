<template>
  <div class="main-body">
    <div class="header-title">
      <i class="el-icon-document-copy"></i>
      用户管理 User
      <div class="right-research">
        <el-input placeholder="请输入内容" v-model="search" class="input-with-select">
          <el-button slot="append" icon="el-icon-search" @click="searchHandler"></el-button>
        </el-input>
      </div>
    </div>
    <el-main class="main">
      <div>
        <el-main>
          <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange" stripe>
            <el-table-column type="selection" width="70"></el-table-column>
            <el-table-column prop="id" label="编号" width="120"></el-table-column>
            <el-table-column prop="name" label="姓名" width="200"></el-table-column>
            <el-table-column prop="sex" label="性别" width="200"></el-table-column>
            <el-table-column prop="phone" label="电话" width="200"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="mini" type="text" @click="handleDetail(scope.$index, scope.row)">详情</el-button>
                <el-button size="mini" type="text" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="margin-top: 20px">
            <el-button @click="toggleSelection()" class="foot-button">取消选择</el-button>
            <div class="post-pagination">
              <el-pagination background
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page.sync="pageNow"
              :page-size="size"
              layout="total, prev, pager, next"
              :total="total">
              </el-pagination>
            </div>
          </div>
        </el-main>
        <el-dialog title="用户详情" :visible.sync="detailVisible">
          <el-form :model="detail">
            <el-form-item label="ID：" :label-width="formLabelWidth">
              <el-input v-model="detail[0].user.id" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="姓名：" :label-width="formLabelWidth">
              <el-input v-model="detail[0].user.name" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="性别：" :label-width="formLabelWidth">
              <el-input v-model="detail[0].user.sex" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="年龄：" :label-width="formLabelWidth">
              <el-input v-model="detail[0].user.birthday" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="电话：" :label-width="formLabelWidth">
              <el-input v-model="detail[0].user.phone" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="邮箱：" :label-width="formLabelWidth">
              <el-input v-model="detail[0].user.email" :disabled="true"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="detailVisible = false">关 闭</el-button>
          </div>
        </el-dialog>
      </div>
    </el-main>
  </div>
</template>

<script>
export default {
  name: "manage_user",
  data() {
    return {
      //每页显示的条数
      size: 8,
      //总条数
      total: 0,
      pageNow: 1,
      search: '',
      tableData: [{
        id: 1001,
        name: '马逸超',
        sex: '男',
        type: '实习'
      }],
      multipleSelection: [],
      detailVisible: false,
      detail: [],
      formLabelWidth: '120px'
    }
  },
  created(){
    var that = this
    this.$http.post('http://localhost:8085/admin/manage_user/getAllUser?key='+this.$route.query.key+"&flag="+this.$route.query.flag,{
      params:{
        pageNow: 1,
        pageSize: 6
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
            //that.total=response.data.total


        that.total=response.data.total
      console.log(response)
    })
    .catch(function (error) {
      console.log(error);
    });
    var that = this
    this.$http.get('http://localhost:8085/index/apply_edit/'+this.tableData[0].id)
      .then(function (response) {

        that.detail.push(response.data)
        console.log(that.tableData[0].id)


      })
      .catch(function (error) {

        console.log(error);
      })
  },
  methods: {
      searchHandler(){
       this.$router.push({
        path: '/admin/manage_user',
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
          this.$http.post('http://localhost:8085/admin/manage_user/getAllUser?key='+this.$route.query.key+"&flag="+this.$route.query.flag,{
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
    handleDetail(index, row) {
      this.detailVisible = true;
      this.detail=[]
      var that = this
        this.$http.get('http://localhost:8085/index/apply_edit/'+row.id)
       .then(function (response) {
        that.detail.push(response.data)
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    handleDelete(index, row) {
      console.log(index, row);
      var that = this
        this.$http.get('http://localhost:8085/admin/manage_user/delete/'+row.id)
       .then(function (response) {
          alert("删除成功")
          location.reload();
      })
      .catch(function (error) {
        console.log(error);
      });
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
