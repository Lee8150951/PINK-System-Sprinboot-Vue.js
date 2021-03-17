<template>
  <li class="user-col" @mouseover="mouseOver" @mouseout="mouseOut">
    <el-row class="user-content">
      <el-col :span="3">
        <img :src="user.avatar" alt="" class="cc-logo">
      </el-col>
      <el-col :span="16">
        <div class="cc-panel">
          <span class="cc-name">{{user.id}}</span>
          <span class="cc-boss">{{user.name}}</span>
        </div>
      </el-col>
      <el-col :span="5">
        <div class="cc-time">
          <i class="el-icon-reading"></i>
          {{user.c_qualification}}
        </div>
      </el-col>
    </el-row>
  </li>
</template>

<script>
export default {
  name: "UserLi",
  props: {
    user: Object,
    index: Number
  },
  methods: {
    mouseOver() {
      const dom = document.getElementsByClassName("user-col")
      dom[this.index].style.backgroundColor = "#f7f8fc"
      dom[this.index].style.borderLeft = "5px solid #67C23A"
    },
    mouseOut() {
      const dom = document.getElementsByClassName("user-col")
      dom[this.index].style.backgroundColor = "#ffffff"
      dom[this.index].style.borderLeft = ""
    }
  }
}
</script>

<style scoped>
@import "../../../assets/css/admin/components/userli.css";
</style>
