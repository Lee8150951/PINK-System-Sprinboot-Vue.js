<template>
  <li>
    <el-row>
      <el-col :span="3">
        <div class="grid-content">
          <el-tag type="info" size="mini">{{chat.type}}</el-tag>
        </div>
      </el-col>
      <el-col :span="18">
        <div class="grid-content">
          <el-row>
            <el-col :span="24">
              <div class="chat-content">
                {{chat.content}}
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </li>
</template>

<script>
export default {
  name: "CommunicateLi",
  props: {
    chat: {
      type: Object
    }
  }
}
</script>

<style scoped>
@import "../../../assets/css/apply-system/components/communicateli.css";
</style>
