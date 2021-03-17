<template>
  <li class="feature-li" @mouseover="mouseOver(item)" @mouseout="mouseOut(item)" @click="turnTo(item)">
    <i :class="item.icon" class="feature-icon"></i>
    {{item.title}}
  </li>
</template>

<script>
export default {
  name: "NavMenuItem",
  props: {
    item: Object
  },
  methods: {
    mouseOver(item) {
      const dom = document.getElementsByClassName("feature-li")
      dom[item.index].style.backgroundColor = "#646d7e"
      dom[item.index].style.color = "#e6eaf5"
    },
    mouseOut(item) {
      const dom = document.getElementsByClassName("feature-li")
      dom[item.index].style.backgroundColor = "#2a2d36"
      dom[item.index].style.color = "#959ba1"
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
@import "../../../assets/css/hire-system/components/navmenuitem.css";
</style>
