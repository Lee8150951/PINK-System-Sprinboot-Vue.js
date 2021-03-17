<template>
  <div class="attract-body">
    <page-title>
      招揽 Attract
    </page-title>
    <div class="main-body">
      <div class="info-table">
        <el-table ref="multipleTable" :data="table_data"
          tooltip-effect="dark" style="width: 100%"
          highlight-current-row :default-sort = "{prop: 'date', order: 'descending'}">
          <el-table-column type="selection" width="60"/>
          <el-table-column prop="apply_id" label="ID" width="100"/>
          <el-table-column prop="candidate.user.name" label="姓名" width="120" sortable/>
          <el-table-column prop="created_time" label="申请时间" width="150" sortable/>
          <el-table-column prop="candidate.highSchool" label="毕业院校" width="160" sortable/>
          <el-table-column prop="candidate.highDegree" label="学历" width="120" sortable/>
          <el-table-column prop="p_id" label="岗位代号" width="130" sortable/>
          <el-table-column prop="recruitment.r_name" label="申请岗位" width="130" sortable/>
          <el-table-column fixed="right" label="操作" width="130">
            <template slot-scope="scope">
              <el-button @click="detailClick(scope.row)" type="text" size="small">详情</el-button>
              <el-popconfirm title="确定删除？"
               @confirm="deleteClick(scope.row)">
                <el-button slot="reference"  type="text" size="small">删除</el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="table-foot">
        <el-pagination background
            @size-change="findSizeChange"
            @current-change="findPage"
             :current-page.sync="pageNow"
             :page-size="size"
             layout="total, prev, pager, next"
             :total="total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import PageTitle from "../../components/hire-system/title/PageTitle";
export default {
  name: "attract",
  components: {PageTitle},
  data() {
    return {
      table_data: [],
      size: 8,
      //总条数
      total: 0,
      pageNow: 1,
      apply_time:"",
      ehr_id:''
    }
  },
  created(){
    const _this = this
    //需要ehrid

    this.$http.get('http://localhost:8085/hire/hire_attract/'+localStorage.getItem("r_id"),{params:{
              pageNow: 1,
              pageSize: 8
            }
          })
      .then(function (response) {
       for(var len = 0;len<=7;len++){
        if(len>=response.data.list.length){
              break;
            }

        _this.table_data.push(response.data.list[len])
        _this.table_data[len].created_time=_this.table_data[len].created_time.substring(0,10)
        console.log(_this.table_data[len].created_time)

        }
        _this.total=response.data.total
      })
      .catch(function (error) {
        console.log(error)
      });

  },
  methods: {
    findSizeChange(size){
          console.log("当每页条数改变时"+size);
          this.size = size;
          this.findAll()
        },
        findPage(){
           console.log(this.pageNow)
           this.findAll(this.pageNow, this.size)
        },

        findAll(page, size){
          var that = this
          this.$http.get('http://localhost:8085/hire/hire_attract/'+localStorage.getItem("r_id"),{
            params:{
              pageNow: this.pageNow,
              pageSize: this.size
            }
          })
       .then(function (response) {
         that.table_data=[]
         console.log(response.data.list.length)
         for(var len = (that.pageNow-1)*that.size ;len<=that.pageNow*that.size-1;len++){
          if(len>=response.data.list.length){
              break;
            }

           that.table_data[len]=response.data.list[len];
            console.log(response.data.list[len])
           console.log(that.table_data[len])
           that.table_data[len].created_time=that.table_data[len].created_time.substring(0,10)
           console.log(that.table_data[len].created_time)
      }
            that.total=response.data.total

      })
      .catch(function (error) {
        console.log(error);
      });
        },
    detailClick(index) {
      this.$router.push({
        path: '/hire/attract_detail',
        query: {
          id: index.apply_id,
        }
      })
    },
    deleteClick(index) {
      console.log(index);
       this.$http.get('http://localhost:8085/hire/hire_attract/delete/',{
            params:{
              apply_id: index.apply_id,
            }
          })
      .then(function (response) {
      for(var len = 0;len<response.data.length;len++){

        _this.table_data.push(response.data[len])

        }
      })
      .catch(function (error) {
        console.log(error);
      })
       location.reload();
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/hire-system/table-common.css";
</style>
