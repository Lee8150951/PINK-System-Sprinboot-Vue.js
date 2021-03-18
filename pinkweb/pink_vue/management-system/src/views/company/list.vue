<template>
  <div class="list-main">
    <main-title>
      <i class="el-icon-document-copy"></i> 管理 Manage
      <div class="feature-bar">
        <el-button type="success" class="feature-btn" @click="addVisible = true">
          <i class="el-icon-plus"></i>新增
        </el-button>
      </div>
    </main-title>
    <div class="list-col">
      <el-table :data="tableData" style="width: 100%" :default-sort = "{prop: 'date', order: 'descending'}">
        <el-table-column prop="ehr_id" label="ID" width="100"></el-table-column>
        <el-table-column prop="user.name" label="姓名" width="110"></el-table-column>
        <el-table-column prop="ehr_position" label="职位" width="140"></el-table-column>
        <el-table-column prop="user.phone" label="电话" width="180"></el-table-column>
        <el-table-column prop="ehr_applyaccount" label="可发布岗位" sortable width="130"></el-table-column>
        <el-table-column prop="ehr_currentaccount" label="已发布岗位" sortable width="130"></el-table-column>
        <el-table-column prop="user.created_time" :formatter="dateFormat" label="创建日期" sortable width="180"></el-table-column>
        <el-table-column fixed="right" label="操作" width="100">
          <template slot-scope="scope">
            <el-button @click="detailClick(scope.row)" type="text" size="small">详情</el-button>
            <el-popconfirm title="确定删除？"
               @confirm="deleteClick(scope.row)">
                <el-button slot="reference"  type="text" size="small">删除</el-button>
              </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <div class="page-peddle">
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
    </div>
    <el-dialog title="新增HR" :visible.sync="addVisible">
      <el-form :model="form">
        <el-row>
          <el-col :span="12" class="left-form">
            <div class="input-li">
              <el-row>
                <el-col :span="4" class="input-name">姓名：</el-col>
                <el-col :span="20" class="input-col">
                  <el-input v-model="form.name" placeholder="请输入内容"></el-input>
                </el-col>
              </el-row>
            </div>
            <div class="input-li">
              <el-row>
                <el-col :span="4" class="input-name">电话：</el-col>
                <el-col :span="20" class="input-col">
                  <el-input v-model="form.phone" placeholder="请输入内容"></el-input>
                </el-col>
              </el-row>
            </div>
            <div class="input-li">
              <el-row>
                <el-col :span="4" class="input-name">密码：</el-col>
                <el-col :span="20" class="input-col">
                  <el-input v-model="form.password" placeholder="请输入内容" show-password></el-input>
                </el-col>
              </el-row>
            </div>
          </el-col>
          <el-col :span="12" class="right-form">
            <div class="input-li">
              <el-row>
                <el-col :span="5" class="input-name">性别：</el-col>
                <el-col :span="19" class="input-col">
                  <el-select v-model="form.sex" placeholder="请选择" class="select-input">
                    <el-option label="男" value="0"></el-option>
                    <el-option label="女" value="1"></el-option>
                  </el-select>
                </el-col>
              </el-row>
            </div>
            <div class="input-li">
              <el-row>
                <el-col :span="5" class="input-name">岗位：</el-col>
                <el-col :span="19" class="input-col">
                  <el-input v-model="form.ehr_position" placeholder="请输入内容" ></el-input>
                </el-col>
              </el-row>
            </div>
            <div class="input-li">
              <el-row>
                <el-col :span="5" class="input-name">工号：</el-col>
                <el-col :span="19" class="input-col">
                  <el-input v-model="form.ehr_jobnum" placeholder="H20201111" ></el-input>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>
        <div class="sub-col">
          <el-button type="primary" @click="submit"><i class="el-icon-plus"></i>新建</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import MainTitle from "../../components/company/body/MainTitle";
import * as fecha from 'element-ui/lib/utils/date';
export default {
  name: "list",
  components: {MainTitle},
  data() {
    return {
      //每页显示的条数
      size: 8,
      //总条数
      total: 0,
      pageNow: 1,
      addVisible: false,
      form: {
        id:'',
        name: '',
        phone: '',
        password: '',
        sex: '',
        ehr_position: '',
        ehr_jobnum: ''

      },
      ea_id:'',
      //  name: '',
      //  phone: '',
      //  password: '',
      //  sex: '',
       enterprise_id:'',
      tableData: []
    }
  },
   created(){
    const _this = this
    this.$http.post('http://localhost:8085/company/list/'+localStorage.getItem("ea_id"), {
      params: {
        pageNow: 1,
        pageSize: 10
      }
    })
      .then(function (response) {
        _this.tableData = []
        for(var len = (_this.pageNow-1)*_this.size ;len<=_this.pageNow*_this.size-1;len++){
          if(len>=response.data.list.length){
            break;
          }
          _this.tableData.push(response.data.list[len])
          _this.tableData[len].user.created_time=_this.tableData[len].user.created_time.substring(0,10);
        }
        _this.total=response.data.total

      })
      .catch(function (error) {
        console.log(error)
      });

  },
  methods: {
    dateFormat(row, column, cellValue) {
      return cellValue ? fecha.format(new Date(cellValue), 'yyyy-MM-dd') : ''
    },
    handleSizeChange(size){
      console.log("当每页条数改变时"+size);
      this.size = size;
      this.findAll(this.pageNow, this.size)

    },
    handleCurrentChange(){
      this.findAll(this.pageNow, this.size)

    },

    findAll(page, size){
      const _this = this
      this.$http.post('http://localhost:8085/company/list/'+localStorage.getItem("ea_id"), {
        params: {
          pageNow: 1,
          pageSize: 10
        }
      })
        .then(function (response) {
          _this.tableData = []
          for(var len = (_this.pageNow-1)*_this.size ;len<=_this.pageNow*_this.size-1;len++){
            if(len>=response.data.list.length){
              break;
            }
            _this.tableData.push(response.data.list[len])

            //_this.tableData[len].user.created_time=_this.tableData[len].user.created_time.substring(0,10);
          }
          console.log(_this.tableData)
          _this.total=response.data.total

        })
        .catch(function (error) {
          console.log(error)
        });
    },
    detailClick(row) {
      this.$router.push({
        path: '/company/hr_detail',
        query: {
          id: row.ehr_id,
        }
      })
    },
    deleteClick(row) {
      console.log(row.ehr_id);
      this.$http.get('http://localhost:8085/company/list/delete/'+row.ehr_id)
      .then(function(response) {

      })
      .catch(function (error) {
        console.log(error)
      });
      location.reload();

    },
    submit() {

      const that=this;
      this.$http.post('http://localhost:8085/company/list/insertUser',{
        name:this.form.name,
        phone:this.form.phone,
        password:this.form.password,
        sex:this.form.sex,

      })
        .then(function(response) {
          const v = that
          that.form.id=response.data.id;

          that.$http.post('http://localhost:8085/company/profile/'+localStorage.getItem("ea_id"))
            .then(function(response) {
              console.log("获取公司id");
              v.enterprise_id=response.data.e_id

              that.$http.post('http://localhost:8085/company/list/insertCompanyHr',{
                enterprise_id:v.enterprise_id,
                ehr_id:v.form.id,
                ehr_position:v.form.ehr_position,
                ehr_jobnum:v.form.ehr_jobnum

              })
                .then(function(response) {
                  console.log("新建HR");
                })
                .catch(function (error) {
                  console.log(error)
                });
            });


        })
        .catch(function (error) {
          console.log(error)
        });
      this.addVisible = false
      //location.reload()
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/company/list.css";
</style>
