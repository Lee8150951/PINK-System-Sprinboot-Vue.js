<template>
  <footer class="footer">
    <el-col :span="8">
      <div class="footer-content footer-left">
        <div class="copyright footer-title">Copyright © 2021 Pink</div>
      </div>
    </el-col>
    <el-col :span="8">
      <div class="footer-content footer-left">
        <div class="bold footer-title">Contract</div>
        <div class="middle">Huazhong University of Science and Technology</div>
        <div class="middle">M202076625@hust.edu.cn</div>
      </div>
    </el-col>
    <el-col :span="8">
      <div class="footer-content">
        <div class="footer-icons">
          <el-button type="primary" icon="el-icon-phone" circle></el-button>
          <el-button type="success" icon="el-icon-service" circle></el-button>
          <el-button type="info" icon="el-icon-lollipop" circle></el-button>
          <el-button type="danger" icon="el-icon-chat-dot-round" circle></el-button>
        </div>
      </div>
    </el-col>
  </footer>
</template>

<script>
  export default {
    name: "Footer"
  }
</script>

<style scoped>
@import "../../../assets/css/apply-system/components/footer.css";
</style>
