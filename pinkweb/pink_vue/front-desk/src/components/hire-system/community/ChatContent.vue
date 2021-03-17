<template>
  <div class="chat-main" v-if="this.box.index === this.show">
    <div class="right-header">{{box.name}}</div>
    <div class="right-body">
      <el-row>
        <el-col :span="18" class="left-body">
          <div class="content-body">
            <chat-item v-for="(community, index) in this.sortList" :key="index" :community="community" :index="index"></chat-item>
          </div>
          <div class="input-body">
            <el-form ref="form" :model="communication" label-width="80px">
              <div class="enter-text">
                <el-input :rows="3" type="textarea" v-model="communication.text"></el-input>
              </div>
              <el-button @click="onSubmit" size="mini" type="warning" class="communicate-sub">发送</el-button>
            </el-form>
          </div>
        </el-col>
        <el-col :span="6" class="right-chat-body">
          <div class="rc-title_1">使用APP聊天更方便</div>
          <img src="../../../assets/img/QR/code.jpg" alt="qr-code" class="qr-code">
          <div class="rc-title_2">扫描使用微信小程序</div>
          <div class="rc-title_2">了解更多</div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
  import ChatItem from "./ChatItem";
  export default {
    name: "ChatContent",
    components: {ChatItem},
    props: {
      box: Object,
      index: Number,
      show: Number
    },
    data() {
      return{
        communication: {
          text: '',
        },
        sortList: []
      }
    },
    methods: {
      onSubmit() {
        alert(this.communication.text)
      },
      // 重新按时间对聊天内容排序
      getNew(communities) {
        let sortTimeList = [];
        let sortList = [];
        for (let i = 0; i < communities.length; i++) {
          sortTimeList.push(Date.parse(communities[i].m_time))
        }
        sortTimeList.sort();
        for (let j = 0; j < sortTimeList.length; j++) {
          for (let i = 0; i < communities.length; i++) {
            if (Date.parse(communities[i].m_time) === sortTimeList[j]) {
              sortList.push(communities[i])
            }
          }
        }
        this.sortList = sortList
      }
    },
    mounted() {
      this.getNew(this.box.communities)
    }
  }
</script>

<style scoped>
@import "../../../assets/css/hire-system/components/chatcontent.css";
</style>
