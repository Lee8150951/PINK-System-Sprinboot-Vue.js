<template>
  <li class="feature-li" @mouseover="mouseOver(item)" @mouseout="mouseOut(item)" @click="turnTo(item)">
    <i class="feature-i" :class="item.icon"></i>
    <span class="right-feature">{{item.title}}</span>
  </li>
</template>

<script>
export default {
  name: "AsideBarItem",
  props: {
    item: Object
  },
  methods: {
    mouseOver(item) {
      const dom = document.getElementsByClassName("feature-li")
      dom[item.index].style.backgroundColor = "#0d51fa"
      dom[item.index].style.color = "#ffffff"
    },
    mouseOut(item) {
      const dom = document.getElementsByClassName("feature-li")
      dom[item.index].style.backgroundColor = ""
      dom[item.index].style.color = "#6c6c6c"
    },
    turnTo(item) {
      this.$router.push({
        path: item.path
      })
    }
  }
}
</script>

<style scoped>
@import "../../../assets/css/company/components/asidebaritem.css";
</style>
