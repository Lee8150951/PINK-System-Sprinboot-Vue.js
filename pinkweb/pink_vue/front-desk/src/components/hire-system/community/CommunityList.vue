<template>
  <li class="community-li" @mouseover="mouseOver(community_item)" @mouseout="mouseOut(community_item)" @click="turnTo(community_item)">
    <el-row>
      <el-col :span="3">
        <div class="avatar-content">
          <img :src="community_item.avatar" alt="" class="avatar">
        </div>
      </el-col>
      <el-col :span="21">
        <div class="community-info-content">
          <div class="c-info-col">
            <span class="c-name">{{community_item.name}}</span>
            <span class="c-info">
              {{community_item.school}} | {{community_item.degree}} | {{community_item.age}}岁
            </span>
          </div>
          <div class="community">
            {{community_item.chat}}
          </div>
        </div>
      </el-col>
    </el-row>
  </li>
</template>

<script>
export default {
  name: "CommunityList",
  props: {
    community_item: Object
  },
  methods: {
    mouseOver(community_item) {
      const dom = document.getElementsByClassName("community-li")
      dom[community_item.index].style.backgroundColor = "#e5e5e5"
    },
    mouseOut(community_item) {
      const dom = document.getElementsByClassName("community-li")
      dom[community_item.index].style.backgroundColor = "#ffffff"
    },
    turnTo(community_item) {
      this.$router.push({
        path: ''
      })
    }
  }
}
</script>

<style scoped>
@import "../../../assets/css/hire-system/components/communitylist.css";
</style>
