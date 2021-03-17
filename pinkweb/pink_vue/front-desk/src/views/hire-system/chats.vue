<template>
  <div class="release-body">
    <page-title>
      讯息 Chats
    </page-title>
    <el-row class="main-body">
      <el-col :span="6" class="left-content">
        <div class="search-col">
          <el-row>
            <el-col :span="20">
              <el-input placeholder="搜索" v-model="search" class="search-input">
                <i slot="prefix" class="el-input__icon el-icon-search"></i>
              </el-input>
            </el-col>
            <el-col :span="4" class="new-col">
              <span id="new_btn" class="new-btn" @mouseout="mouseOut" @mouseover="mouseOver">
                <i class="el-icon-plus"></i>
              </span>
            </el-col>
          </el-row>
        </div>
        <div class="target-li">
          <ul>
            <chat-box v-for="(box, index) in chat_boxes" :key="index" :box="box" :index="index" @func="getFromChild"></chat-box>
          </ul>
        </div>
      </el-col>
      <el-col :span="18">
        <div class="right-content">
          <chat-content v-for="(box, index) in chat_boxes" :key="index" :box="box" :index="index" :show="connect_index"></chat-content>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// 导入PageTitle模块
import PageTitle from "../../components/hire-system/title/PageTitle";
// 导入ChatBox模块
import ChatBox from "../../components/hire-system/community/ChatBox";
// 导入ChatContent模块
import ChatContent from "../../components/hire-system/community/ChatContent";
export default {
  name: "chats",
  components: {ChatContent, ChatBox, PageTitle},
  data() {
    return {
      search: '',
      show_chat: '1',
      connect_index: 0,
      chat_boxes: [
        {
          index: 0,
          id: '10001',
          avatar: require('../../assets/img/avatar/avatar02.png'),
          name: '马小超',
          // 聊天内容
          communities: [
            {
              // 信息id号
              m_id: '01',
              // 发送方或接收方判定(0是用户发送过来的，1是hr发送过去的)
              m_flag: 1,
              // 接收方id
              m_s_id: "10001",
              // 发送方id
              m_r_id: "10001",
              // 信息时间
              m_time: '2021-07-25 15:17:07',
              // 内容
              m_content: '山不在高，有仙则名。水不在深，有龙则灵。'
            },
            {
              m_id: '02',
              m_flag: 0,
              m_s_id: "10001",
              m_r_id: "10001",
              m_time: '2008-06-25 15:18:08',
              m_content: '斯是陋室，惟吾德馨。苔痕上阶绿，草色入帘青。'
            },
            {
              m_id: '03',
              m_flag: 0,
              m_s_id: "10001",
              m_r_id: "10001",
              m_time: '2020-08-25 15:18:08',
              m_content: '谈笑有鸿儒，往来无白丁。可以调素琴，阅金经。'
            },
            {
              m_id: '04',
              m_flag: 1,
              m_s_id: "10001",
              m_r_id: "10001",
              m_time: '2019-08-25 15:18:08',
              m_content: '无丝竹之乱耳，无案牍之劳形。南阳诸葛庐，西蜀子云亭。孔子云：何陋之有？'
            }
          ]
        },
        {
          index: 1,
          id: '10002',
          avatar: require('../../assets/img/avatar/avatar01.png'),
          name: '余三胖',
          // 聊天内容
          communities: [
            {
              m_id: '01',
              m_flag: 0,
              m_s_id: "10001",
              m_r_id: "10001",
              m_time: '2021-03-05 15:10:07',
              m_content: '寒蝉凄切，对长亭晚，骤雨初歇。'
            },
            {
              m_id: '02',
              m_flag: 1,
              m_s_id: "10001",
              m_r_id: "10001",
              m_time: '2021-03-05 15:10:08',
              m_content: '都门帐饮无绪，留恋处，兰舟催发。'
            },
            {
              m_id: '03',
              m_flag: 0,
              m_s_id: "10001",
              m_r_id: "10001",
              m_time: '2021-03-05 15:10:09',
              m_content: '执手相看泪眼，竟无语凝噎。'
            },
            {
              m_id: '04',
              m_flag: 1,
              m_s_id: "10001",
              m_r_id: "10001",
              m_time: '2021-03-05 15:10:10',
              m_content: '念去去，千里烟波，暮霭沉沉楚天阔。'
            },
            {
              m_id: '05',
              m_flag: 0,
              m_s_id: "10001",
              m_r_id: "10001",
              m_time: '2021-03-05 15:11:10',
              m_content: '多情自古伤离别，更那堪，冷落清秋节！'
            },
            {
              m_id: '06',
              m_flag: 1,
              m_s_id: "10001",
              m_r_id: "10001",
              m_time: '2021-03-05 15:11:11',
              m_content: '今宵酒醒何处？杨柳岸，晓风残月。'
            },
            {
              m_id: '07',
              m_flag: 0,
              m_s_id: "10001",
              m_r_id: "10001",
              m_time: '2021-03-05 15:13:21',
              m_content: '此去经年，应是良辰好景虚设。'
            },
            {
              m_id: '08',
              m_flag: 1,
              m_s_id: "10001",
              m_r_id: "10001",
              m_time: '2021-03-05 15:13:22',
              m_content: '便纵有千种风情，更与何人说？'
            }
          ]
        },
      ]
    }
  },
  methods: {
    mouseOver() {
      const dom = document.getElementById("new_btn");
      dom.style.backgroundColor = "#f3f3f3";
    },
    mouseOut() {
      const dom = document.getElementById("new_btn");
      dom.style.backgroundColor = "#ffffff";
    },
    // 获取子组件传来的值
    getFromChild(index) {
      this.connect_index = index
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/hire-system/chats.css";
</style>
