<template>
  <el-col :span="6">
    <div class="overview-content">
      <ul>
        <li class="top-overview">
          <el-row>
            <el-col :span="3">
              <div class="icon-content">
                <i :class="overview.icon"></i>
              </div>
            </el-col>
            <el-col :span="21" class="right-col">
              <div class="top-title-content">
                {{overview.cn_name}}
              </div>
              <div class="bottom-title-content">
                {{overview.en_name}}
              </div>
            </el-col>
          </el-row>
        </li>
        <li class="overview-num">
          {{overview.num}}
        </li>
      </ul>
    </div>
  </el-col>
</template>

<script>
  export default {
    name: "ViewItem",
    props: {
      overview: Object
    }
  }
</script>

<style scoped>
@import "../../../assets/css/hire-system/components/viewitem.css";
</style>
