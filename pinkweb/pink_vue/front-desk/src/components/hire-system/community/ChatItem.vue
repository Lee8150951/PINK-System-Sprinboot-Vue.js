<template>
  <el-row class="chat-main">
    <el-col :span="2">
      <div class="left-chat-content">
        <el-tag size="small" type="success">已接收</el-tag>
      </div>
    </el-col>
    <el-col :span="20">
      <div class="middle-content">
        <div class="chat-box">
          {{community.m_content}}
        </div>
      </div>
    </el-col>
    <el-col :span="2">
      <div class="right-chat-content">
        <el-tag size="small" type="primary">已发送</el-tag>
      </div>
    </el-col>
  </el-row>
</template>

<script>
  export default {
    name: "ChatItem",
    props: {
      community: Object,
      index: Number
    },
    methods: {
      // 控制展示
      presentShow() {
        const flag = this.community.m_flag;
        // 聊天框
        const dom_1 = document.getElementsByClassName("chat-box");
        // 左状态框
        const dom_2 = document.getElementsByClassName("left-chat-content");
        // 右状态框
        const dom_3 = document.getElementsByClassName("right-chat-content");
        // user发送
        if (flag === 0) {
          dom_1[this.index].style.borderRadius = "0 8px 8px 8px";
          dom_1[this.index].style.float= "left";
          dom_3[this.index].style.visibility= "hidden";
        }
        // hr发送
        else if (flag === 1) {
          dom_1[this.index].style.borderRadius = "8px 0 8px 8px";
          dom_1[this.index].style.float= "right";
          dom_2[this.index].style.visibility= "hidden";
        }
      }
    },
    mounted() {
      this.presentShow()
    }
  }
</script>

<style scoped>
@import "../../../assets/css/hire-system/components/chatitem.css";
</style>
