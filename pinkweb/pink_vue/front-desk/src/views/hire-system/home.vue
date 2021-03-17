<template>
  <div class="home-body">
    <page-title>
      总览 Dashboard
    </page-title>
    <div class="home-overview">
      <view-item v-for="overview in overviews" :key="overview.index" :overview="overview"></view-item>
    </div>
    <div class="info-body">
      <el-row>
        <el-col :span="12">
          <div class="info-left-content">
            <div class="info-title">
              讯息列表 Community List
              <span class="more-span" @click="turnToChat">
                <el-badge is-dot class="more-item">more...</el-badge>
              </span>
            </div>
            <div class="community-list">
              <ul>
                <community-list v-for="community_item in community_list.slice(0, 5)" :key="community_item.index" :community_item="community_item"></community-list>
              </ul>
            </div>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-right-content">
            <div class="info-title">
              备忘录 Memo
            </div>
            <div class="memo-col" @click="dialogVisible = true">
              <span class="memo-big">{{memo_big}}</span>
              <span>{{memo_body}}</span>
            </div>
          </div>
        </el-col>
      </el-row>
      <el-dialog title="备忘录更改" :visible.sync="dialogVisible">
        <form action="">
          <el-input type="textarea" :autosize="{ minRows: 6, maxRows: 10}" placeholder="请输入内容" v-model="memo.memo"></el-input>
          <div class="dialogue">
            <el-button type="primary" class="memo-btn">修改</el-button>
          </div>
        </form>
      </el-dialog>
    </div>
  </div>
</template>

<script>
// 导入PageTitle模块
import PageTitle from "../../components/hire-system/title/PageTitle";
// 导入ViewItem模块
import ViewItem from "../../components/hire-system/overview/ViewItem";
// 导入CommunityList模块
import CommunityList from "../../components/hire-system/community/CommunityList";
export default {
  name: "home",
  components: {CommunityList, ViewItem, PageTitle},
  data() {
    return {
      dialogVisible: false,
      memo_big: '',
      memo_body: '',
      overviews: [
        {
          index: 0,
          icon: 'el-icon-s-custom',
          cn_name: '总招揽人数',
          en_name: 'Recruited-Num',
          num: '0',
          path: ''
        },
        {
          index: 1,
          icon: 'el-icon-s-claim',
          cn_name: '已发布岗位',
          en_name: 'Released-Position',
          num: '32',
          path: ''
        },
        {
          index: 2,
          icon: 'el-icon-s-comment',
          cn_name: '意向人数',
          en_name: 'Intentional-Num',
          num: '1242',
          path: ''
        },
        {
          index: 3,
          icon: 'el-icon-s-opportunity',
          cn_name: '收藏应聘者',
          en_name: 'Collected-Num',
          num: '0',
          path: ''
        },
      ],
      community_list: [
        {
          index: '0',
          name: '马小超',
          school: '华中科技大学',
          degree: '硕士',
          age: '23',
          chat: '您好，我能咨询一下吗?',
          avatar: require('../../assets/img/avatar/avatar02.png')
        },
        {
          index: '1',
          name: '王大宝',
          school: '贾利顿大学',
          degree: '博士',
          age: '34',
          chat: '这样行吗?',
          avatar: require('../../assets/img/avatar/avatar02.png')
        },
        {
          index: '2',
          name: 'Tom Cruise',
          school: '耶鲁大学',
          degree: '本科',
          age: '19',
          chat: 'Are you OK?',
          avatar: require('../../assets/img/avatar/avatar01.png')
        },
        {
          index: '3',
          name: '余三胖',
          school: '铁道口皇家男子学院',
          degree: '大专',
          age: '53',
          chat: '好哦',
          avatar: require('../../assets/img/avatar/avatar01.png')
        },
        {
          index: '4',
          name: '朱重八',
          school: '宝通寺',
          degree: '技校',
          age: '16',
          chat: '您好，我能咨询一下吗?',
          avatar: require('../../assets/img/avatar/avatar02.png')
        },
      ],
      memo: {
        uid: '',
        id: '',
        memo: '水陆草木之花，可爱者甚蕃。晋陶渊明独爱菊。自李唐来，世人甚爱牡丹。予独爱莲之出淤泥而不染，濯清涟而不妖，中通外直，不蔓不枝，香远益清，亭亭净植，可远观而不可亵玩焉。'
      }
    }
  },
  created(){
    const v = this
    this.$http.get('http://localhost:8085/premise/hr_login/'+localStorage.getItem('phone'))
      .then(function (response) {
        // console.log(response)
      localStorage.setItem("r_id",response.data.id)
      })
      .catch(function (error) {
        console.log(error);
      });

          const _this = this
    this.$http.get('http://localhost:8085/hire/hire_release/'+localStorage.getItem("r_id"),{params:{
              pageNow: 1,
              pageSize: 4
            }
          })
      .then(function (response) {
        _this.overviews[1].num = response.data.total
        // console.log(response)
      })
      .catch(function (error) {
        console.log(error);
      });

      
    //需要ehrid

    this.$http.get('http://localhost:8085/hire/hire_attract/'+localStorage.getItem("r_id"),{params:{
              pageNow: 1,
              pageSize: 6
            }
          })
      .then(function (response) {
        _this.overviews[2].num = response.data.total
          // console.log(response)
      })
      .catch(function (error) {
        console.log(error)
      });
  },
  methods: {
    turnToChat() {
      this.$router.push({
        path: '/hire/hire_chat'
      })
    }
  },
  mounted() {
    this.memo_big = this.memo.memo[0];
    this.memo_body = this.memo.memo.slice(1, this.memo.memo.length)
  }
}
</script>

<style scoped>
@import "../../assets/css/hire-system/home.css";
</style>
