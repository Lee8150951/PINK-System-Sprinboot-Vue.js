<template>
  <span>
    <el-popover placement="top-start" trigger="hover" class="popover-col">
      <ul>
        <li id="profile" class="popover" @mouseout="mouseOut_1" @mouseover="mouseOver_1" @click="turnTo">设置</li>
        <li id="exit" class="popover" @mouseout="mouseOut_2" @mouseover="mouseOver_2" @click="Logout">退出</li>
      </ul>
      <img slot="reference" src="../../../assets/img/avatar/avatar02.png" class="avatar">
    </el-popover>
  </span>
</template>

<script>
export default {
  name: "AvatarItem",
  data() {
    return {
      visible: false
    }
  },
  methods: {
    mouseOver_1() {
      const dom = document.getElementById("profile")
      dom.style.backgroundColor = "#f7f7f7"
    },
    mouseOver_2() {
      const dom = document.getElementById("exit")
      dom.style.backgroundColor = "#f7f7f7"
    },
    mouseOut_1() {
      const dom = document.getElementById("profile")
      dom.style.backgroundColor = "#ffffff"
    },
    mouseOut_2() {
      const dom = document.getElementById("exit")
      dom.style.backgroundColor = "#ffffff"
    },
    Logout() {
      this.$router.push({
        path: '/UserShunt'
      })
    },
    turnTo() {
      this.$router.push({
        path: '/admin/admin_profile'
      })
    }
  }
}
</script>

<style scoped>
@import "../../../assets/css/company/components/avataritem.css";
</style>
