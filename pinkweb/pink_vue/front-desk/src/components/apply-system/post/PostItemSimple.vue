<template>
  <el-col :span="24">
    <div class="item-content" @mouseout="mouseOut" @mouseover="mouseOver" @click="turnTo">
      <el-row>
        <el-col :span="12">
          <div class="left-item-content">
            <h4>{{post.r_name}}[{{post.r_address}}]</h4>
            <p>
              <span class="salary">{{post.r_salary}}</span>
              <span class="p-info-detail">{{post.r_education}}</span>
            </p>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="right-item-content">
            <span class="p-boss">{{post.name}}</span>
            <span class="p-job">{{post.ehr_position}}</span>
          </div>
        </el-col>
      </el-row>
    </div>
  </el-col>
</template>

<script>
export default {
  name: "PostItemSimple",
  props: {
    post: {
      type: Object
    },
    index: Number
  },
  methods: {
    mouseOver() {
      const dom = document.getElementsByClassName("item-content");
      dom[this.index].style.backgroundColor = "#e8e8e8"
    },
    mouseOut() {
      const dom = document.getElementsByClassName("item-content");
      dom[this.index].style.backgroundColor = "#ffffff"
    },
    turnTo() {
      this.$router.push({
        path: '/index/position_detail',
        query: {
            index: this.post.r_id,
            name: this.post.r_name,
        }
      })
    }
  }
}
</script>

<style scoped>
@import "../../../assets/css/apply-system/components/postitemsimple.css";
</style>
