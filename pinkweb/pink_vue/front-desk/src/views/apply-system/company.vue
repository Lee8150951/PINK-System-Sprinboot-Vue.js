<template>
  <div class="company-body">
    <el-row></el-row>
    <el-row class="search">
      <el-col :span="24">
        <div class="search-content">
          <el-row>
            <el-col :span="3"><div class="grid-content"></div></el-col>
            <el-col :span="13">
              <div class="grid-content">
                <el-input placeholder="搜索公司" v-model="search" class="input-with-select">
                  <el-button slot="append" @click="searchHandler" type="button" icon="el-icon-search"></el-button>
                </el-input>
              </div>
            </el-col>
          </el-row>
          <el-row class="province-search">
            <el-col :span="3"><div class="grid-content"></div></el-col>
            <el-col :span="18">
              <div class="screen-content">
                <span>公司地点：</span>
                <span v-for="city in cities" class="city">
                  <el-button type="text" class="screen-btn" @click="citySearch(city)">{{city}}</el-button>
                </span>
              </div>
            </el-col>
          </el-row>
          <el-row class="province-search">
            <el-col :span="3"><div class="grid-content"></div></el-col>
            <el-col :span="18">
              <div class="screen-content">
                <span>行业类型：</span>
                <span v-for="type in types" class="screen">
                  <el-button type="text" class="screen-btn" @click="typeSearch(type)">{{type}}</el-button>
                </span>
              </div>
            </el-col>
          </el-row>
          <el-row class="province-search">
            <el-col :span="3"><div class="grid-content"></div></el-col>
            <el-col :span="18">
              <div class="screen-content">
                <span>公司规模：</span>
                <span v-for="scale in scales" class="screen">
                  <el-button type="text" class="screen-btn" @click="scaleSearch(scale)">{{scale}}</el-button>
                </span>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
    <el-row class="position-main">
      <el-col :span="3"><div class="grid-content"></div></el-col>
      <el-col :span="17">
        <div class="grid-content">
          <div class="post-li">
            <el-row>
              <company-item-box v-for="company in companies" :key="company.index" :company="company"></company-item-box>
            </el-row>
          </div>
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
          <!-- <div class="post-pagination">
            <el-pagination background :page-size="20" :pager-count="11" layout="prev, pager, next" :total="1000"></el-pagination>
          </div> -->
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24"><div class="occupied-content"></div></el-col>
    </el-row>
  </div>
</template>

<script>
// 导入CompanyItemBox组件
import CompanyItemBox from "../../components/apply-system/company/CompanyItemBox";
// 导入UsedItem组件
import UsedItem from "../../components/apply-system/used/UsedItem";
export default {
  name: "company",
  components: {CompanyItemBox, UsedItem},
  data() {
    return {
      //每页显示的条数
      size: 20,
      //总条数
      total: 0,
      pageNow: 1,
      search: '',
      cities: [
        '不限','上海', '北京', '广州', '深圳', '武汉', '南京', '杭州',
        '成都', '重庆', '青岛', '厦门', '天津', '宁波', '郑州',
        '长沙', '南昌', '昆明'
      ],
      types: [
        '不限', '电子', '互联网', '计算机', '媒体', '医疗',
        '生活服务', '地产', '教育', '广告', '农牧业',
        '物流', '建筑', '汽车', '贸易', '其他行业'
      ],
      scales: [
        '不限', '20', '100', '200',
        '500', '1000', '1000以上'
      ],
      companies: [
        {
          index: 1,
          logo: require('../../assets/img/company/huawei.jpg'),
          name: '华为技术有限公司',
          state: '已上市',
          type: '通讯',
          num: '5632',
          salary: '10-15K'
        }
      ]
    }
  },
  created() {
          var v = this
          this.$http.post('http://localhost:8085/index/apply_company/query?key='+this.$route.query.key+"&flag="+this.$route.query.flag,{
            params:{
              pageNow: 1,
              pageSize: 6
            }
          })
       .then(function (response) {
          v.tableData = []
          for(var len = (v.pageNow-1)*v.size ;len<=v.pageNow*v.size-1;len++){
            if(len>=response.data.list.length){
              break;
            }
              v.companies.push(response.data.list[len])
            }
            //that.total=response.data.total


        v.total=response.data.total
      })
      .catch(function (error) {
        console.log(error);
      });
  },
  methods: {
    searchHandler(){
       this.$router.push({
        path: '/index/apply_company',
        query: {
          key: "搜索",
          flag: this.search
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

          that.companies = []
          for(var len = (that.pageNow-1)*that.size ;len<=that.pageNow*that.size-1;len++){
            if(len>=response.data.list.length){
              break;
            }
              that.companies.push(response.data.list[len])
              console.log(that.posts)
            }
            //that.total=response.data.total


        that.total=response.data.total
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    citySearch(city) {
      this.$router.push({
        path: '/index/apply_company',
        query: {
          key: "公司地点",
          flag: city
        }
      })
      location.reload();
    },
    typeSearch(type) {
      this.$router.push({
        path: '/index/apply_company',
        query: {
          key: "行业类型",
          flag: type
        }
      })
      location.reload();
    },
    scaleSearch(scale) {
      this.$router.push({
        path: '/index/apply_company',
        query: {
          key: "公司规模",
          flag: scale
        }
      })
      location.reload();
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/apply-system/company.css";
</style>
