<template>
  <div class="position-body">
    <el-row></el-row>
    <el-row class="search">
      <el-col :span="24">
        <div class="search-content">
          <el-row>
            <el-col :span="3"><div class="grid-content"></div></el-col>
            <el-col :span="13">
              <div class="grid-content">
                <el-input placeholder="搜索职位" v-model="search" class="input-with-select">
                  <el-button slot="append" @click="searchHandler" type="button" icon="el-icon-search"></el-button>
                </el-input>
              </div>
            </el-col>
          </el-row>
          <el-row class="province-search">
            <el-col :span="3"><div class="grid-content"></div></el-col>
            <el-col :span="18">
              <div class="province-content">
                <span>热门城市：</span>
                <span v-for="city in cities" class="city">
                  <el-button type="text" class="city-btn" @click="citySearch(city)">{{city}}</el-button>
                </span>
              </div>
            </el-col>
          </el-row>
          <el-row class="sort-search">
            <el-col :span="3"><div class="grid-content"></div></el-col>
            <el-col :span="18">
              <div class="sort-content">
                <el-dropdown>
                  <span class="el-dropdown-link drop-li">
                    工作经验<i class="el-icon-arrow-down el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item v-for="(exp, index) in exp_interval" :key="index">
                      <div @click="expSearch(exp)">{{exp.exp}}</div>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
                <el-dropdown class="dropdown">
                  <span class="el-dropdown-link drop-li">
                    学历要求<i class="el-icon-arrow-down el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item v-for="(degree, index) in degree_interval" :key="index">
                      <div @click="degreeSearch(degree)">{{degree.degree}}</div>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
                <el-dropdown class="dropdown">
                  <span class="el-dropdown-link drop-li">
                    薪资要求<i class="el-icon-arrow-down el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item v-for="(salary, index) in salary_interval" :key="index">
                      <div @click="salarySearch(salary)">{{salary.salary}}</div>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
                <el-dropdown class="dropdown">
                  <span class="el-dropdown-link drop-li">
                    发布时间<i class="el-icon-arrow-down el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item v-for="(time, index) in time_interval" :key="index">
                      <div @click="timeSearch(time)">{{time.time}}</div>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
                <router-view></router-view>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
    <el-row class="position-main">
      <el-col :span="3"><div class="grid-content"></div></el-col>
      <el-col :span="13">
        <div class="grid-content">
          <div class="post-li">
            <post-item v-for="post in posts" :key="post.index" :post="post"></post-item>
          </div>
          <div class="post-pagination">
            <el-pagination background
            @current-change="handleCurrentChange"
             :current-page.sync="pageNow"
             :page-size="size"
             layout="total, prev, pager, next"
             :total="total">
             </el-pagination>
          </div>
        </div>
      </el-col>
      <el-col :span="5">
        <div class="right-content">
          <el-row>
            <el-col :span="24">
              <div class="btn-content">
                <el-button type="warning" class="upload-btn" @click="turnTo">上传简历</el-button>
              </div>
            </el-col>
          </el-row>
          <el-row class="used-li">
            <el-col :span="24">
              <div class="used-header">
                看过的职位
              </div>
              <div class="used-body">
                <ul>
                  <used-item></used-item>
                  <used-item></used-item>
                  <used-item></used-item>
                  <used-item></used-item>
                  <used-item></used-item>
                </ul>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24"><div class="occupied-content"></div></el-col>
    </el-row>
  </div>
</template>

<script>

import PostItem from "../../components/apply-system/post/PostItem";
// 导入UsedItem组件
import UsedItem from "../../components/apply-system/used/UsedItem";
export default {
  name: "position",
  components: {UsedItem, PostItem},
  data() {
    return {
      //每页显示的条数
      size: 10,
      //总条数
      total: 0,
      pageNow: 1,
      search: '',
      exp_interval: [
        {
          key: 'exp',
          exp: '不限'
        },
        {
          key: 'exp',
          exp: '在校生'
        },
        {
          key: 'exp',
          exp: '应届生'
        },
        {
          key: 'exp',
          exp: '1年以内'
        },
        {
          key: 'exp',
          exp: '1-3年'
        },
        {
          key: 'exp',
          exp: '3-5年'
        },
        {
          key: 'exp',
          exp: '5-10年'
        },
        {
          key: 'exp',
          exp: '10年以上'
        }
      ],
      degree_interval: [
        {
          key: 'degree',
          degree: '不限'
        },
        {
          key: 'degree',
          degree: '初中及以下'
        },
        {
          key: 'degree',
          degree: '中专'
        },
        {
          key: 'degree',
          degree: '高中'
        },
        {
          key: 'degree',
          degree: '大专'
        },
        {
          key: 'degree',
          degree: '本科'
        },
        {
          key: 'degree',
          degree: '硕士'
        },
        {
          key: 'degree',
          degree: '博士'
        }
      ],
      salary_interval: [
        {
          key: 'salary',
          salary: '不限'
        },
        {
          key: 'salary',
          salary: '3K以下'
        },
        {
          key: 'salary',
          salary: '3-5K'
        },
        {
          key: 'salary',
          salary: '5-10K'
        },
        {
          key: 'salary',
          salary: '10-15K'
        },
        {
          key: 'salary',
          salary: '15-20K'
        },
        {
          key: 'salary',
          salary: '20-30K'
        },
        {
          key: 'salary',
          salary: '30-50K'
        },
        {
          key: 'salary',
          salary: '50K以上'
        }
      ],
      time_interval: [
        {
          key: 'time_0',
          time: '不限'
        },
        {
          key: 'time_1',
          time: '一天以内'
        },
        {
          key: 'time_3',
          time: '三天以内'
        },
        {
          key: 'time_7',
          time: '七天以内'
        },
        {
          key: 'time_15',
          time: '十五天以内'
        },
        {
          key: 'time_30',
          time: '一个月以内'
        }
      ],
      cities: [
        '全部城市','上海', '北京', '广州', '深圳', '武汉', '南京', '杭州',
        '成都', '重庆', '青岛', '厦门', '天津', '宁波', '郑州',
        '长沙', '南昌', '昆明'
      ],
      posts: []
    }
  },
  created() {
          var that = this
          this.$http.post('http://localhost:8085/index/apply_position/query?key='+this.$route.query.key+"&flag="+this.$route.query.flag,{
            params:{
              pageNow: 1,
              pageSize: 6
            }
          })
       .then(function (response) {
          that.posts = []
          for(var len = (that.pageNow-1)*that.size ;len<=that.pageNow*that.size-1;len++){
            if(len>=response.data.list.length){
              break;
            }
              that.posts.push(response.data.list[len])
              console.log(that.posts)
            }
            //that.total=response.data.total
        that.total=response.data.total
      })
      .catch(function (error) {
        console.log(error);
      });
  },
  methods: {
    turnTo() {
      this.$router.push({
        path: '/index/apply_profile'
      })
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
          this.$http.post('http://localhost:8085/index/apply_position/query?key='+this.$route.query.key+"&flag="+this.$route.query.flag,{
            params:{
              pageNow: this.pageNow,
              pageSize: this.size
            }
          })
       .then(function (response) {
          that.posts = []
          for(var len = (that.pageNow-1)*that.size ;len<=that.pageNow*that.size-1;len++){
            if(len>=response.data.list.length){
              break;
            }
              that.posts.push(response.data.list[len])
              console.log(that.posts)
            }
            //that.total=response.data.total
        that.total=response.data.total
      })
      .catch(function (error) {
        console.log(error);
      });
        },

    searchHandler(){
       this.$router.push({
        path: '/index/apply_position',
        query: {
          key: "搜索",
          flag: this.search
        }
      })
      location.reload()
    },

    expSearch(exp) {
      this.$router.push({
        path: '/index/apply_position',
        query: {
          key: exp.key,
          flag: exp.exp
        }
      })
      location.reload();
    },

    degreeSearch(degree) {

      this.$router.push({
        path: '/index/apply_position',
        query: {
          key: degree.key,
          flag: degree.degree
        }
      })
      location.reload();
    },

    salarySearch(salary) {
      this.$router.push({
        path: '/index/apply_position',
        query: {
          key: salary.key,
          flag: salary.salary
        }
      })
      location.reload();

    },
    timeSearch(time) {
      this.$router.push({
        path: '/index/apply_position',
        query: {
          key: time.key,
          flag: time.time
        }
      })
      location.reload();
    },
    citySearch(city) {
      this.$router.push({
        path: '/index/apply_position',
        query: {
          key: "城市",
          flag: city
        }
      })
      location.reload();
    }
  }
}


</script>

<style scoped>
@import "../../assets/css/apply-system/position.css";
</style>
