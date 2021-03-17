<template>
  <div class="main-body">
    <div class="header-title">
      <i class="el-icon-document-copy"></i>
      公司管理 Company
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
            <el-table-column prop="e_id" label="编号" width="100"></el-table-column>
            <el-table-column prop="avatarUrl" label="企业logo" width="120">
              <template slot-scope="scope">
                <img  :src="scope.row.avatarUrl" alt="" style="width: 45px;border-radius: 50%">
              </template>
            </el-table-column>
            <el-table-column prop="e_name" label="单位名称" width="340"></el-table-column>
            <el-table-column prop="e_address" label="总部地址" width="150"></el-table-column>
            <el-table-column prop="e_type" label="企业类型" width="140"></el-table-column>

            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="mini" type="text" @click="handleEdit(scope.$index, scope.row)">详情</el-button>
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
      </div>
    </el-main>
  </div>
</template>

<script>
export default {
  name: "manage_company",
  data() {
    return {
      //每页显示的条数
      size: 6,
      //总条数
      total: 0,
      pageNow: 1,
      search: '',
      tableData: [],
      multipleSelection: [],

    }
  },
  created(){
    const v = this
    this.$http.post('http://localhost:8085/index/apply_company/query?key='+this.$route.query.key+"&flag="+this.$route.query.flag,{
      params:{
        pageNow: 1,
        pageSize: 6
      }
    })
    .then(function (response) {
      console.log()
          v.tableData = []
          for(var len = (v.pageNow-1)*v.size ;len<=v.pageNow*v.size-1;len++){
            if(len>=response.data.list.length){
              break;
            }
              v.tableData.push(response.data.list[len])
              v.tableData[len].avatarUrl = "http://localhost:8085/companyAvatar/"+response.data.list[len].e_logo;
            }
            //that.total=response.data.total
            console.log(v.tableData)

        v.total=response.data.total
    })
    .catch(function (error) {
      console.log(error);
    });
  },
  methods: {
        searchHandler(){
       this.$router.push({
        path: '/admin/manage_company',
        query: {
          key: "搜索",
          flag: this.search,
          search :this.search
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
          this.$http.post('http://localhost:8085/index/apply_company/query?key='+this.$route.query.key+"&flag="+this.$route.query.flag,{
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
            //that.total=response.data.total


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
      this.$router.push({
        path: '/admin/edit_company',
        query: {
          e_id: row.e_id,
        }
      })
    },
    handleDelete(index, row) {
        this.$http.get('http://localhost:8085/admin/manage_company/delete/'+row.e_id)
        .then(function (response) {
          console.log(response)
          alert("删除成功")
          location.reload();
      })
      .catch(function (error) {
        console.log(error);
      });
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
