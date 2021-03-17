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
      const dom_1 = document.getElementsByClassName("feature-li")
      dom_1[item.index].style.backgroundColor = "#233343"
      dom_1[item.index].style.color = "#ffffff"
      const dom_2 = document.getElementsByClassName("feature-i")
      dom_2[item.index].style.color = "#2dcc70"
    },
    mouseOut(item) {
      const dom_1 = document.getElementsByClassName("feature-li")
      dom_1[item.index].style.backgroundColor = ""
      dom_1[item.index].style.color = "#76899a"
      const dom_2 = document.getElementsByClassName("feature-i")
      dom_2[item.index].style.color = "#76899a"
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
@import "../../../assets/css/admin/components/asidebaritem.css";
</style>
