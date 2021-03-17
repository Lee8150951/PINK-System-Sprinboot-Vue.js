<template>
  <li class="chat-target" @mouseover="mouseOver" @mouseout="mouseOut" @click="passIndex">
    <el-row>
      <el-col :span="6" class="chat-avatar">
        <img :src="box.avatar" alt="" class="avatar-img">
      </el-col>
      <el-col :span="14" class="chat-info">
        <div class="chat-name">{{box.name}}</div>
        <div class="chat-content">{{show_content}}</div>
      </el-col>
      <el-col :span="4" class="chat-delete">
        <div class="delete">
          <span class="delete-span"><i class="el-icon-delete"></i></span>
        </div>
      </el-col>
    </el-row>
  </li>
</template>

<script>
export default {
  name: "ChatBox",
  data() {
    return {
      show_content: '',
    }
  },
  props: {
    box: Object,
    index: Number
  },
  methods: {
    mouseOver() {
      const dom_1 = document.getElementsByClassName("chat-target")
      dom_1[this.index].style.backgroundColor = "#f3f3f3"
      const dom_2 = document.getElementsByClassName("delete")
      dom_2[this.index].style.opacity = 1
    },
    mouseOut() {
      const dom_1 = document.getElementsByClassName("chat-target")
      dom_1[this.index].style.backgroundColor = "#ffffff"
      const dom_2 = document.getElementsByClassName("delete")
      dom_2[this.index].style.opacity = 0
    },
    // 子传父
    passIndex() {
      this.$emit("func", this.index)
    },
    // 获取最新聊天记录
    getNew(communities) {
      let sortList = [];
      for (let i = 0; i < communities.length; i++) {
        sortList.push(Date.parse(communities[i].m_time))
      }
      sortList.sort();
      for (let i = 0; i < communities.length; i++) {
        if (Date.parse(communities[i].m_time) === sortList[communities.length - 1]) {
          return communities[i].m_content
        }
      }
    },
    // 自动切除长字符串
    cutLong(longString) {
      let show_content;
      if (longString.length > 10) {
        show_content = longString.slice(0, 10) + "..."
      } else {
        show_content = longString
      }
      return show_content
    }
  },
  mounted() {
    const communities =  this.box.communities;
    const longString = this.getNew(communities);
    const get_content = this.cutLong(longString);
    this.show_content = get_content;
  }
}
</script>

<style scoped>
@import "../../../assets/css/hire-system/components/chatbox.css";
</style>
